package com.workhard.wenshu.service;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.AhoCorasick.AhoCorasickDoubleArrayTrie;
import com.hankcs.hanlp.dictionary.CoreDictionary;
import com.hankcs.hanlp.dictionary.CustomDictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 演示用户词典的动态增删
 *
 * @author hankcs
 */
public class addCustomDic {

    public static void addAccusationToDic() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/Dictionary/accusationName"));
            String str;
            while ((str = in.readLine()) != null) {
                String[] temp = str.split("\\s+");
                CustomDictionary.insert(temp[1].substring(0, temp[1].length() - 1), "accusation 1000000");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String text = "中华人民共和国最高人民法院\n" +
                "通 知 书\n" +
                "（2021）最高法刑申162号\n" +
                "宋文平：\n" +
                "你因被告人靳建平过失致人死亡一案，不服河北省任丘市人民法院（2009）任刑初字第140号刑事附带民事判决、河北省沧州市中级民法院（2009）沧刑终字第172号刑事附带民事判决及河北省高级人民法院（2011）冀刑监字第40号驳回申诉通知，向本院提出申诉。\n" +
                "经审查，你的申诉不符合《中华人民共和国刑事诉讼法》第二百五十三条规定的重新审判条件，本院决定对该案不予重新审判。\n" +
                "特此通知。";
        addCustomDic.addAccusationToDic();

        final char[] charArray = text.toCharArray();
        Set<String> temp = new HashSet();
        CustomDictionary.parseText(charArray, new AhoCorasickDoubleArrayTrie.IHit<CoreDictionary.Attribute>() {
            @Override
            public void hit(int begin, int end, CoreDictionary.Attribute value) {
                String accusation = value.toString().split(" ")[0];
                if (accusation.equals("accusation")) {
                    String e = new String(charArray, begin, end - begin);
                    temp.add(e);
                }
            }
        });
        System.out.println(temp.toString());
    }
}