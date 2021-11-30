# DataScienceProject
数据科学大作业--文书爬取和分析

## crawler

chromedriver.exe 版本：96.0.4664.45

建议使用对应的 Chrome 版本

## jsonmaker

输入各个主体的信息, 返回 json 字符串

对于每一个主体, 创建对象 SubjectInfo 添加进一个 ArrayList subjectInfos


例如：

```java
SubjectInfo subjectInfo2 = new SubjectInfo("吴丽红",
                    "复议申请人（案外人）",
                    true,
                    "女",
                    null,
                    "1973年2月25日",
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

### Marker 构造方法

方法1. 将 `subjectInfos`, `案由`, `法庭List` 传入 `Marker` 构造函数，再次调用其类方法 `toJson` 便可得到 json 字符串

方法2. 先调用`clear()`清空 json 信息, 再使用`setter`函数，最后调用`toJson`

结果:

```json
{
  "主体": [
    {
      "名称": "大连红枫房地产发展有限公司",
      "身份": "被执行人",
      "自然人": false
    },
    {
      "名称": "吴丽红",
      "身份": "复议申请人（案外人）",
      "自然人": true,
      "性别": "女",
      "出生日期": "1973年2月25日",
      "民族": "汉族"
    }
  ],
  "案由": "企业借贷纠纷",
  "法院": [
    "辽宁省高级人民法院"
  ]
}

```


## SomePS

日期的格式是yyyy-mm-dd