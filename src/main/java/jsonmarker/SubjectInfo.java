package jsonmarker;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class SubjectInfo {
    @JSONField(name = "名称", ordinal = 1)
    private String name;

    @JSONField(name = "身份", ordinal = 2)
    private String partiesType;

    @JSONField(name = "自然人", ordinal = 3)
    private boolean isNatual;

    @JSONField(name = "性别", ordinal = 4)
    private String gender;

    @JSONField(name = "出生地", ordinal = 5)
    private String birthPlace;

    @JSONField(name = "出生日期", ordinal = 6)
    private String birthDate;

    @JSONField(name = "民族", ordinal = 7)
    private String ethnicity;

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
    public SubjectInfo(String name, String partiesType, boolean isNatual, String gender, String birthPlace, String birthDate, String ethnicity) {
        assert isNatual;
        this.name = name;
        this.partiesType = partiesType;
        this.isNatual = true;
        this.gender = gender;
        this.birthPlace = birthPlace;
        this.birthDate = birthDate;
        this.ethnicity = ethnicity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartiesType() {
        return partiesType;
    }

    public void setPartiesType(String partiesType) {
        this.partiesType = partiesType;
    }

    public boolean isNatual() {
        return isNatual;
    }

    public void setNatual(boolean natual) {
        isNatual = natual;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }


}
