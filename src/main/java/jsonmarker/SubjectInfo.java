package jsonmarker;

import java.util.Date;

public class SubjectInfo {
    public String name;
    public String partiesType;
    public boolean isNatual;
    public Gender gender;
    public String birthPlace;
    public Date birthDate;
    public String ethnicity;

    /**
     * @param name        当事人名称，可能是自然人姓名或者公司名
     * @param partiesType 当事人类型
     * @param isNatual    是否是自然人
     */
    public SubjectInfo(String name, String partiesType, boolean isNatual) {
        assert !isNatual;
        this.name = name;
        this.partiesType = partiesType;
        this.isNatual = false;
    }

    /**
     * @param name        当事人名称，可能是自然人姓名或者公司名
     * @param partiesType 当事人类型
     * @param isNatual    是否是自然人
     * @param gender      性别
     * @param birthPlace  出生地
     * @param birthDate   出生日期
     * @param ethnicity   民族
     */
    public SubjectInfo(String name, String partiesType, boolean isNatual, Gender gender, String birthPlace, Date birthDate, String ethnicity) {
        assert isNatual;
        this.name = name;
        this.partiesType = partiesType;
        this.isNatual = true;
        this.gender = gender;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.ethnicity = ethnicity;
    }
}
