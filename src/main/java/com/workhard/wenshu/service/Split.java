package com.workhard.wenshu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.restful.HanLPClient;
import lombok.Data;

import java.io.IOException;
import java.util.*;

@Data
public class Split {

    private static final String org = "ORGANIZATION";
    private static final String date = "DATE";
    private static final String location = "LOCATION";
    private static final String person = "PERSON";
    private static HanLPClient client;
    private static final String url = "https://www.hanlp.com/api";
    private static final String auth = "NjYyQGJicy5oYW5scC5jb206T0NMNmd3Yjl3QW93Z1RnQg==";

    @JSONField(name = "org", ordinal = 3)
    private final HashSet<String> orgSet;
    @JSONField(name = "date", ordinal = 1)
    private final HashSet<String> dateSet;
    @JSONField(name = "loc", ordinal = 2)
    private final HashSet<String> locationSet;
    @JSONField(name = "court", ordinal = 4)
    private final HashSet<String> courtSet;
    @JSONField(name = "person", ordinal = 5)
    private final HashSet<String> personSet;
    @JSONField(name = "accusation", ordinal = 6)
    private final HashSet<String> accusationSet;
    @JSONField(name = "verb", ordinal = 7)
    private final HashSet<String> verbSet;
    @JSONField(name = "adj", ordinal = 8)
    private final HashSet<String> adjSet;

    public Split() {
        client = new HanLPClient(url, auth);
        orgSet = new LinkedHashSet<>();
        dateSet = new LinkedHashSet<>();
        locationSet = new LinkedHashSet<>();
        courtSet = new LinkedHashSet<>();
        personSet = new LinkedHashSet<>();
        accusationSet = new LinkedHashSet<>();
        verbSet = new LinkedHashSet<>();
        adjSet = new LinkedHashSet<>();
    }

    public Split(String text) throws IOException {
        this();
        this.process(text);
    }

    public void process(String text) throws IOException {
        Map<String, List> participleRes = client.parse(text, new String[]{"tok/fine", "pos/pku", "ner/msra"}, new String[]{});
        // ??????????????????
        addCustomDic.addAccusationToDic();
        // ??????????????????????????????
        addFourSet(participleRes.get("ner/msra"));
        addNounAdj(participleRes.get("tok/fine"), participleRes.get("pos/pku"));
        addAccusation(text);
    }


    /* ?????????????????????????????????????????? */
    private void addFourSet(List msra) {
        List everyLine;
        ArrayList<ArrayList> locationDetails = new ArrayList<>();
        for (int i = 0; i < msra.size(); i++) {
            // ????????????????????????
            everyLine = (List) msra.get(i);
            locationDetails.clear();
            // ??????????????? : ????????????????????????; ??????????????? ???????????????????????????
            ArrayList<String> itemOfLIne;
            for (Object o : everyLine) {
                itemOfLIne = (ArrayList<String>) o;
                switch (itemOfLIne.get(1)) {
                    case org:
                        if (itemOfLIne.get(0).contains("??????")) {
                            courtSet.add(itemOfLIne.get(0));
                            break;
                        } else {
                            orgSet.add(itemOfLIne.get(0));
                            break;
                        }
                    case date:
                        dateSet.add(itemOfLIne.get(0));
                        break;
                    case person:
                        personSet.add(itemOfLIne.get(0));
                        break;
                    case location:
                        locationDetails.add(itemOfLIne);
                        break;
                }
            }
            // ???????????????????????????????????????
            locationSet.addAll(mergeLocations(locationDetails));
        }
    }

    /* ??????????????????????????? */
    private ArrayList<String> mergeLocations(ArrayList<ArrayList> locationDetails) {
        if (locationDetails.size() == 0) {
            return new ArrayList<>();
        }
        ArrayList<String> res = new ArrayList<>();
        // ?????????????????????????????????
        int tag = (int) locationDetails.get(0).get(3);
        StringBuilder temp = new StringBuilder((String) locationDetails.get(0).get(0));
        for (int i = 1; i < locationDetails.size(); i++) {
            String tempLoc = (String) locationDetails.get(i).get(0);
            int begin = (int) locationDetails.get(i).get(2);
            int end = (int) locationDetails.get(i).get(3);
            if (begin == tag) {
                temp.append(tempLoc);
                tag = end;
            } else {
                res.add(temp.toString());
                temp = new StringBuilder(tempLoc);
                tag = end;
            }
        }
        res.add(temp.toString());
        return res;
    }

    private void addNounAdj(List tok, List pos) {
        ArrayList<String> tokLine;
        ArrayList<String> posLine;
        assert tok.size() == pos.size();
        for (int i = 0; i < tok.size(); i++) {
            tokLine = (ArrayList<String>) tok.get(i);
            posLine = (ArrayList<String>) pos.get(i);
            assert tokLine.size() == posLine.size();
            for (int j = 0; j < tokLine.size(); j++) {
                if ("v".equals(posLine.get(j))) {
                    this.verbSet.add(tokLine.get(j));
                } else if ("a".equals(posLine.get(j))) {
                    this.adjSet.add(tokLine.get(j));
                }
            }
        }
    }


    private void addAccusation(String text) {
        final char[] charArray = text.toCharArray();
        CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>() {
            @Override
            public void hit(int begin, int end, CoreDictionary.Attribute value) {
                String lexical = value.toString().split(" ")[0];
                if ("accusation".equals(lexical)) {
                    accusationSet.add(new String(charArray, begin, end - begin));
                }
            }
        });
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}

