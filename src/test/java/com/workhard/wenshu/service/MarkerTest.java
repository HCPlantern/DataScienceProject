package com.workhard.wenshu.service;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MarkerTest {

    @Test
    public void test() {
        ArrayList<SubjectInfo> subjectInfos = new ArrayList<>();
        ArrayList<String> courts = new ArrayList<>();

        SubjectInfo subjectInfo1 = new SubjectInfo("大连红枫房地产发展有限公司", "被执行人", false);
        subjectInfos.add(subjectInfo1);
        SubjectInfo subjectInfo2 = null;
        subjectInfo2 = new SubjectInfo("吴丽红",
                "复议申请人（案外人）",
                true,
                "女",
                null,
                "1973年2月25日",
                "汉族");
        subjectInfos.add(subjectInfo2);
        courts.add("辽宁省高级人民法院");
        Marker marker = new Marker(subjectInfos, "企业借贷纠纷", courts);
        System.out.println(marker.toJson());

    }

}