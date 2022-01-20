package com.workhard.wenshu.service;
import com.hankcs.hanlp.dictionary.CustomDictionary;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 * 将罪名添加到自定义词典
 *
 */
public class addCustomDic {

    public static void addAccusationToDic() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("src/main/resources/Dictionary/accusationName"));
            String str;
            while ((str = in.readLine()) != null) {
                CustomDictionary.insert(str, "accusation 1000000");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}