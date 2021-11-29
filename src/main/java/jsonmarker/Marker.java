package jsonmarker;

import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 对文书内容进行标记, 输出 json 字符串
 * 标注内容:
 * ---当事人
 * ------当事人类型: 如 再审申请人, 法定代表人, 委托诉讼代理人 等等
 * ------名称
 * ------是否是自然人: 自然人 或 公司
 * ------如果是自然人:
 * ---------性别
 * ---------民族
 * ---------出生地
 * ---------出生日期
 * ---案由
 * ---法院
 */
public class Marker {

    private JSONObject jsonObject;
    private ArrayList<SubjectInfo> subjects;
    private String accusation;
    private ArrayList<String> courts;

    public Marker(ArrayList<SubjectInfo> subjects, String accusation, ArrayList<String> courts) {
        jsonObject = new JSONObject();
        this.subjects = subjects;
        this.accusation = accusation;
        this.courts = courts;
        addSubjects(subjects);
    }

    private void addSubjects(ArrayList<SubjectInfo> subjects) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < subjects.size(); i++) {
            jsonObject.put("id" + (i + 1) + " " + "名称", subjects.get(i).name);
            jsonObject.put("id" + (i + 1) + " " + "身份", subjects.get(i).partiesType);
            if (subjects.get(i).isNatual) {
                jsonObject.put("id" + (i + 1) + " " + "主体类型", "自然人");
                jsonObject.put("id" + (i + 1) + " " + "性别", subjects.get(i).gender);
                jsonObject.put("id" + (i + 1) + " " + "民族", subjects.get(i).ethnicity);
                jsonObject.put("id" + (i + 1) + " " + "出生地", subjects.get(i).birthPlace);
                jsonObject.put("id" + (i + 1) + " " + "出生日期", format.format(subjects.get(i).birthDate));
            } else {
                jsonObject.put("id" + (i + 1) + " " + "主体类型", "法人");
            }
        }
    }

    public String toJson() {
        return jsonObject.toJSONString();
    }

}
