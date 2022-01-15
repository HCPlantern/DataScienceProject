package com.workhard.wenshu.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
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

    @JSONField(name = "org", ordinal = 3)
    private final HashSet<String> orgSet;
    @JSONField(name = "date", ordinal = 1)
    private final HashSet<String> dateSet;
    @JSONField(name = "loc", ordinal = 2)
    private final HashSet<String> locationSet;
    @JSONField(name = "court", ordinal = 4)
    private final HashSet<String> courtSet;
    @JSONField(name = "name", ordinal = 5)
    private final HashSet<String> personSet;
    @JSONField(name = "verb", ordinal = 6)
    private final HashSet<String> verbSet;
    @JSONField(name = "adj", ordinal = 7)
    private final HashSet<String> adjSet;

    public Split() {
        client = new HanLPClient("https://www.hanlp.com/api", "NjYyQGJicy5oYW5scC5jb206T0NMNmd3Yjl3QW93Z1RnQg==");
        orgSet = new LinkedHashSet<>();
        dateSet = new LinkedHashSet<>();
        locationSet = new LinkedHashSet<>();
        courtSet = new LinkedHashSet<>();
        personSet = new LinkedHashSet<>();
        verbSet = new LinkedHashSet<>();
        adjSet = new LinkedHashSet<>();
    }

    public Split(String text) throws IOException {
        this();
        this.process(text);
    }

    public void process(String text) throws IOException {
        Map<String, List> participleRes = client.parse(text, new String[]{"tok/fine", "pos/pku", "ner/msra"}, new String[]{});
        // 分别处理四种特殊情况
        addFourSet(participleRes.get("ner/msra"));
        addNounAdj(participleRes.get("tok/fine"), participleRes.get("pos/pku"));
    }

    /* 对地点集合进行特殊的合并操作 */
    private void addFourSet(List msra) {
        List everyLine;
        ArrayList<ArrayList> locationDetails = new ArrayList<>();
        for (int i = 0; i < msra.size(); i++) {
            // 每一句的分词结果
            everyLine = (List) msra.get(i);
            locationDetails.clear();
            // 第一个元素 : 符合条件的字符串; 第二个元素 ：四种条件中的一个
            ArrayList<String> itemOfLIne;
            for (Object o : everyLine) {
                itemOfLIne = (ArrayList<String>) o;
                switch (itemOfLIne.get(1)) {
                    case org:
                        if (itemOfLIne.get(0).contains("法院")) {
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
            // 对一句话中相邻地点进行合并
            locationSet.addAll(mergeLocations(locationDetails));
        }
    }

    /* 对相邻地点进行合并 */
    private ArrayList<String> mergeLocations(ArrayList<ArrayList> locationDetails) {
        if (locationDetails.size() == 0) {
            return new ArrayList<>();
        }
        ArrayList<String> res = new ArrayList<>();
        // 先把第一个元素加载进去
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

    public String toString() {
       return JSON.toJSONString(this);
    }

}

