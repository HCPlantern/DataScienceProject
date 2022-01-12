package com.workhard.wenshu.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * 对文书内容进行标记, 输出 json 字符串
 * 标注内容:
 * ---当事人 (SubjectInfo 类)
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
 *
 * 两种初始化方式
 * 1. 创建对象时初始化
 * 2. 先 clear(), 使用 set 方法初始化
 */
public class Marker {

    private JSONObject jsonObject;
    @JSONField(name = "主体", ordinal = 1)
    private ArrayList<SubjectInfo> subjects;

    @JSONField(name = "案由", ordinal = 2)
    private String accusation;

    @JSONField(name = "法院", ordinal = 3)
    private ArrayList<String> courts;

    public Marker(ArrayList<SubjectInfo> subjects, String accusation, ArrayList<String> courts) {
        /* 使用 LinkedHashMap 使元素按照加入顺序排列 */
        jsonObject = new JSONObject(new LinkedHashMap<>());
        this.subjects = subjects;
        this.accusation = accusation;
        this.courts = courts;
    }

    private void addSubjects() {
        jsonObject.put("主体", subjects);
        jsonObject.put("案由", accusation);
        jsonObject.put("法院", courts);
    }

    public String toJson() {
        addSubjects();
        return jsonObject.toJSONString();
    }

    public void clear() {
        jsonObject.clear();
    }

    public ArrayList<SubjectInfo> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<SubjectInfo> subjects) {
        this.subjects = subjects;
    }

    public String getAccusation() {
        return accusation;
    }

    public void setAccusation(String accusation) {
        this.accusation = accusation;
    }

    public ArrayList<String> getCourts() {
        return courts;
    }

    public void setCourts(ArrayList<String> courts) {
        this.courts = courts;
    }

}
