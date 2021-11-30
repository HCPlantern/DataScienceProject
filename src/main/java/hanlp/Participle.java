package hanlp;

import com.hankcs.hanlp.restful.HanLPClient;

import java.io.IOException;
import java.util.*;

public class Participle {

    private static final String org = "ORGANIZATION";
    private static final String date = "DATE";
    private static final String location = "LOCATION";
    private static final String person = "PERSON";
    public HanLPClient client;
    private HashSet<String> orgSet;
    private HashSet<String> dateSet;
    private HashSet<String> locationSet;
    private HashSet<String> personSet;
    private HashSet<String> verbSet;
    private HashSet<String> adjSet;

    public Participle() {
        client = new HanLPClient("https://www.hanlp.com/api", null);
        orgSet = new LinkedHashSet<>();
        dateSet = new LinkedHashSet<>();
        locationSet = new LinkedHashSet<>();
        personSet = new LinkedHashSet<>();
        verbSet = new LinkedHashSet<>();
        adjSet = new LinkedHashSet<>();
    }

    public Participle(String text) throws IOException {
        this.process(text);
    }

    public void process(String text) throws IOException {
        Map<String, List> participleRes = client.parse(text, new String[]{"tok/fine", "pos/pku", "ner/msra"}, new String[]{});
        // 分别处理四种特殊情况
        addFourSet(participleRes.get("ner/msra"));
        addNounAdj(participleRes.get("tok/fine"), participleRes.get("pos/pku"));
    }

    private void addFourSet(List msra) {
        List everyLine;
        for (int i = 0; i < msra.size(); i++) {
            // 每一行的分词结果
            everyLine = (List) msra.get(i);
            // 第一个元素 : 符合条件的字符串; 第二个元素 ：四种条件中的一个
            List itemOfLIne;
            for (Object o : everyLine) {
                itemOfLIne = (List) o;
                switch ((String) itemOfLIne.get(1)) {
                    case org:
                        orgSet.add((String) itemOfLIne.get(0));
                        break;
                    case date:
                        dateSet.add((String) itemOfLIne.get(0));
                        break;
                    case person:
                        personSet.add((String) itemOfLIne.get(0));
                        break;
                    case location:
                        locationSet.add((String) itemOfLIne.get(0));
                        break;
                }
            }
        }
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

    public HashSet<String> getOrgSet() {
        return orgSet;
    }

    public HashSet<String> getDateSet() {
        return dateSet;
    }

    public HashSet<String> getLocationSet() {
        return locationSet;
    }

    public HashSet<String> getPersonSet() {
        return personSet;
    }

    public HashSet<String> getVerbSet() {
        return verbSet;
    }

    public HashSet<String> getAdjSet() {
        return adjSet;
    }


}
