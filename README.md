# DataScienceProject
数据科学大作业--文书爬取和分析

## crawler

chromedriver.exe 版本：96.0.4664.45

建议使用对应的 Chrome 版本

## jsonmaker

输入各个主体的信息, 返回 json 字符串

对于每一个主体, 创建对象 SubjectInfo 添加进一个 ArrayList subjectInfos

`Gender`是枚举类, 值为 `MEN` 或 `WOMEN`

`Date` java自带类, 传入前需要创建一个 `DateFormat` 并以此解析输入的日期

例如：

```java
DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
...
subjectInfo2 = new SubjectInfo("吴丽红",
                    "复议申请人（案外人）",
                    true,
                    Gender.WOMEN,
                    null,
                    // 传入日期字符串，使用 format 进行格式化
                    format.parse("1973年2月25日"),
                    "汉族");
```

SubjectInfo 拥有两个构造方法, 一个属于自然人, 一个属于法人

```java
    /**
     * 法人
     * @param name        当事人名称，可能是自然人姓名或者公司名
     * @param partiesType 当事人类型
     * @param isNatual    是否是自然人
     */
    public SubjectInfo(String name, String partiesType, boolean isNatual) {
    }

    /**
     * 自然人
     * @param name        当事人名称，可能是自然人姓名或者公司名
     * @param partiesType 当事人类型
     * @param isNatual    是否是自然人
     * @param gender      性别
     * @param birthPlace  出生地
     * @param birthDate   出生日期
     * @param ethnicity   民族
     */
    public SubjectInfo(String name, String partiesType, boolean isNatual, Gender gender, String birthPlace, Date birthDate, String ethnicity) {
    }
```

创建法庭名列表

将 subjectInfos, 案由, 法庭ArrayList 传入 Marker 构造函数，再次调用其类方法 toJson 便可得到 json 字符串

结果:

```json
{
  "id1名称": "大连红枫房地产发展有限公司",
  "id1身份": "被执行人",
  "id1主体类型": "法人",
  "id2名称": "吴丽红",
  "id2身份": "复议申请人（案外人）",
  "id2主体类型": "自然人",
  "id2性别": "WOMEN",
  "id2民族": "汉族",
  "id2出生日期": "1973-02-25"
}

```
