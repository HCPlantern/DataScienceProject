package com.workhard.wenshu.service;
import com.hankcs.hanlp.dictionary.CustomDictionary;

/**
 * 将罪名添加到自定义词典
 *
 */
public class addCustomDic {

    public static void addAccusationToDic() {
        String[] accusationList = accusations.split("\n");
        String str = "";
        for (int i = 0; i < accusationList.length; i++) {
            str = accusationList[i];
            CustomDictionary.insert(str, "accusation 1000000");
        }
    }

    private static final String accusations = "背叛国家\n" +
            "分裂国家\n" +
            "煽动分裂国家\n" +
            "武装叛乱、暴乱\n" +
            "颠覆国家政权\n" +
            "煽动颠覆国家政权\n" +
            "资助危害国家安全犯罪活动\n" +
            "投敌叛变\n" +
            "叛逃\n" +
            "间谍\n" +
            "为境外窃取、剌探、收买、非法提供国家秘密、情报\n" +
            "资敌\n" +
            "放火\n" +
            "决水\n" +
            "爆炸\n" +
            "投放危险物质\n" +
            "以危险方法危害公共安全\n" +
            "失火\n" +
            "过失决水\n" +
            "过失爆炸\n" +
            "过失投放危险物质\n" +
            "过失以危险方法危害公共安全\n" +
            "破坏交通工具\n" +
            "破坏交通设施\n" +
            "破坏电力设备\n" +
            "破坏易燃易爆设备\n" +
            "过失损坏交通工具\n" +
            "过失损坏交通设施\n" +
            "过失损坏电力设备\n" +
            "过失损坏易燃易爆设备\n" +
            "组织、领导、参加恐怖组织\n" +
            "帮助恐怖活动\n" +
            "准备实施恐怖活动\n" +
            "宣扬恐怖主义、极端主义、煽动实施恐怖活动\n" +
            "利用极端主义破坏法律实施\n" +
            "强制穿戴宣扬恐怖主义、极端主义服饰、标志\n" +
            "非法持有宣扬恐怖主义、极端主义物品\n" +
            "劫持航空器\n" +
            "劫持船只、汽车\n" +
            "暴力危及飞行安全\n" +
            "破坏广播电视设施、公用电信设施\n" +
            "过失损坏广播电视设施、公用电信设施\n" +
            "非法制造、买卖、运输、邮寄、储存枪支、弹药、爆炸物\n" +
            "非法制造、买卖、运输、储存危险物质\n" +
            "违规制造、销售枪支\n" +
            "盗窃、抢夺枪支、弹药、爆炸物、危险物质\n" +
            "抢劫枪支、弹药、爆炸物、危险物质\n" +
            "非法持有、私藏枪支、弹药\n" +
            "非法出租、出借枪支\n" +
            "丢失枪支不报\n" +
            "非法携带枪支、弹药、管制刀具、危险物品危及公共安全\n" +
            "重大飞行事故\n" +
            "铁路运营安全事故\n" +
            "交通肇事\n" +
            "危险驾驶\n" +
            "妨害安全驾驶\n" +
            "重大责任事故\n" +
            "危险作业\n" +
            "强令、组织违章冒险作业\n" +
            "重大劳动安全事故\n" +
            "大型群众性活动重大安全事故\n" +
            "危险物品肇事\n" +
            "工程重大安全事故\n" +
            "教育设施重大安全事故\n" +
            "消防责任事故\n" +
            "不报、谎报安全事故\n" +
            "生产、销售伪劣产品\n" +
            "生产、销售、提供假药\n" +
            "生产、销售、提供劣药\n" +
            "妨害药品管理\n" +
            "生产、销售不符合安全标准的食品\n" +
            "生产、销售有毒、有害食品\n" +
            "生产、销售不符合标准的医用器材\n" +
            "生产、销售不符合安全标准的产品\n" +
            "生产、销售伪劣农药、兽药、化肥、种子\n" +
            "生产、销售不符合卫生标准的化妆品\n" +
            "走私武器、弹药\n" +
            "走私核材料\n" +
            "走私假币\n" +
            "走私文物\n" +
            "走私贵重金属\n" +
            "走私珍贵动物、珍贵动物制品\n" +
            "走私国家禁止进出口的货物、物品\n" +
            "走私淫秽物品\n" +
            "走私普通货物、物品\n" +
            "走私废物\n" +
            "虚报注册资本\n" +
            "虚假出资、抽逃出资\n" +
            "欺诈发行证券\n" +
            "违规披露、不披露重要信息\n" +
            "妨害清算\n" +
            "隐匿、故意销毁会计凭证、会计帐簿、财务会计报告\n" +
            "虚假破产\n" +
            "非国家工作人员受贿\n" +
            "对非国家工作人员行贿\n" +
            "对外国公职人员、国际公共组织官员行贿\n" +
            "非法经营同类营业\n" +
            "为亲友非法牟利\n" +
            "签订、履行合同失职被骗\n" +
            "国有公司、企业、事业单位人员失职\n" +
            "国有公司、企业、事业单位人员滥用职权\n" +
            "徇私舞弊低价折股、出售国有资产\n" +
            "背信损害上市公司利益\n" +
            "伪造货币\n" +
            "出售、购买、运输假币\n" +
            "金融工作人员购买假币、以假币换取货币\n" +
            "持有、使用假币\n" +
            "变造货币\n" +
            "擅自设立金融机构\n" +
            "伪造、变造、转让金融机构经营许可证、批准文件\n" +
            "高利转贷\n" +
            "骗取贷款、票据承兑、金融票证\n" +
            "非法吸收公众存款\n" +
            "伪造、变造金融票证\n" +
            "妨害信用卡管理\n" +
            "窃取、收买、非法提供信用卡信息\n" +
            "伪造、变造国家有价证券\n" +
            "伪造、变造股票、公司、企业债券\n" +
            "擅自发行股票、公司、企业债券\n" +
            "内幕交易、泄露内幕信息\n" +
            "利用未公开信息交易\n" +
            "编造并传播证券、期货交易虚假信息\n" +
            "诱骗投资者买卖证券、期货合约\n" +
            "操纵证券、期货市场\n" +
            "背信运用受托财产\n" +
            "违法运用资金\n" +
            "违法发放贷款\n" +
            "吸收客户资金不入账\n" +
            "违规出具金融票证\n" +
            "对违法票据承兑、付款、保证\n" +
            "骗购外汇\n" +
            "逃汇\n" +
            "洗钱\n" +
            "集资诈骗\n" +
            "贷款诈骗\n" +
            "票据诈骗\n" +
            "金融凭证诈骗\n" +
            "信用证诈骗\n" +
            "信用卡诈骗\n" +
            "有价证券诈骗\n" +
            "保险诈骗\n" +
            "逃税\n" +
            "抗税\n" +
            "逃避追缴欠税\n" +
            "骗取出口退税\n" +
            "虚开增值税专用发票、用于骗取出口退税、抵扣税款发票\n" +
            "虚开发票\n" +
            "伪造、出售伪造的增值税专用发票\n" +
            "非法出售增值税专用发票\n" +
            "非法购买增值税专用发票、购买伪造的增值税专用发票\n" +
            "非法制造、出售非法制造的用于骗取出口退税、抵扣税款发票\n" +
            "非法制造、出售非法制造的发票\n" +
            "非法出售用于骗取出口退税、抵扣税款发票\n" +
            "非法出售发票\n" +
            "持有伪造的发票\n" +
            "假冒注册商标\n" +
            "销售假冒注册商标的商品\n" +
            "非法制造、销售非法制造的注册商标标识\n" +
            "假冒专利\n" +
            "侵犯著作权\n" +
            "销售侵权复制品\n" +
            "侵犯商业秘密\n" +
            "为境外窃取、刺探、收买、非法提供商业秘密\n" +
            "损害商业信誉、商品声誉\n" +
            "虚假广告\n" +
            "串通投标\n" +
            "合同诈骗\n" +
            "组织、领导传销活动\n" +
            "非法经营\n" +
            "强迫交易\n" +
            "伪造、倒卖伪造的有价票证\n" +
            "倒卖车票、船票\n" +
            "非法转让、倒卖土地使用权\n" +
            "提供虚假证明文件\n" +
            "出具证明文件重大失实\n" +
            "逃避商检\n" +
            "故意杀人\n" +
            "过失致人死亡\n" +
            "故意伤害\n" +
            "组织出卖人体器官\n" +
            "过失致人重伤\n" +
            "强奸\n" +
            "负有照护职责人员性侵\n" +
            "强制猥亵、侮辱\n" +
            "猥亵儿童\n" +
            "非法拘禁\n" +
            "绑架\n" +
            "拐卖妇女、儿童\n" +
            "收买被拐卖的妇女、儿童\n" +
            "聚众阻碍解救被收买的妇女、儿童\n" +
            "诬告陷害\n" +
            "强迫劳动\n" +
            "雇用童工从事危重劳动\n" +
            "非法搜查\n" +
            "非法侵入住宅\n" +
            "侮辱\n" +
            "诽谤\n" +
            "刑讯逼供\n" +
            "暴力取证\n" +
            "虐待被监管人\n" +
            "煽动民族仇恨、民族歧视\n" +
            "出版歧视、侮辱少数民族作品\n" +
            "非法剥夺公民宗教信仰自由\n" +
            "侵犯少数民族风俗习惯\n" +
            "侵犯通信自由\n" +
            "私自开拆、隐匿、毁弃邮件、电报\n" +
            "侵犯公民个人信息\n" +
            "报复陷害\n" +
            "打击报复会计、统计人员\n" +
            "破坏选举\n" +
            "暴力干涉婚姻自由\n" +
            "重婚\n" +
            "破坏军婚\n" +
            "虐待\n" +
            "虐待被监护、看护人\n" +
            "遗弃\n" +
            "拐骗儿童\n" +
            "组织残疾人、儿童乞讨\n" +
            "组织未成年人进行违反治安管理活动\n" +
            "抢劫\n" +
            "盗窃\n" +
            "诈骗\n" +
            "抢夺\n" +
            "聚众哄抢\n" +
            "侵占\n" +
            "职务侵占\n" +
            "挪用资金\n" +
            "挪用特定款物\n" +
            "敲诈勒索\n" +
            "故意毁坏财物\n" +
            "破坏生产经营\n" +
            "拒不支付劳动报酬\n" +
            "妨害公务\n" +
            "袭警\n" +
            "煽动暴力抗拒法律实施\n" +
            "招摇撞骗\n" +
            "伪造、变造、买卖国家机关公文、证件、印章\n" +
            "盗窃、抢夺、毁灭国家机关公文、证件、印章\n" +
            "伪造公司、企业、事业单位、人民团体印章\n" +
            "伪造、变造、买卖身份证件\n" +
            "使用虚假身份证件、盗用身份证件\n" +
            "冒名顶替\n" +
            "非法生产、买卖警用装备\n" +
            "非法获取国家秘密\n" +
            "非法持有国家绝密、机密文件、资料、物品\n" +
            "非法生产、销售专用间谍器材、窃听、窃照专用器材\n" +
            "非法使用窃听、窃照专用器材\n" +
            "组织考试作弊\n" +
            "非法出售、提供试题、答案\n" +
            "代替考试\n" +
            "非法侵入计算机信息系统\n" +
            "非法获取计算机信息系统数据、非法控制计算机信息系统\n" +
            "提供侵入、非法控制计算机信息系统程序、工具\n" +
            "破坏计算机信息系统\n" +
            "拒不履行信息网络安全管理义务\n" +
            "非法利用信息网络\n" +
            "帮助信息网络犯罪活动\n" +
            "扰乱无线电通讯管理秩序\n" +
            "聚众扰乱社会秩序\n" +
            "聚众冲击国家机关\n" +
            "扰乱国家机关工作秩序\n" +
            "组织、资助非法聚集\n" +
            "聚众扰乱公共场所秩序、交通秩序\n" +
            "投放虚假危险物质\n" +
            "编造、故意传播虚假恐怖信息\n" +
            "编造、故意传播虚假信息\n" +
            "高空抛物\n" +
            "聚众斗殴\n" +
            "寻衅滋事\n" +
            "催收非法债务\n" +
            "组织、领导、参加黑社会性质组织\n" +
            "入境发展黑社会组织\n" +
            "包庇、纵容黑社会性质组织\n" +
            "传授犯罪方法\n" +
            "非法集会、游行、示威\n" +
            "非法携带武器、管制刀具、爆炸物参加集会、游行、示威\n" +
            "破坏集会、游行、示威\n" +
            "侮辱国旗、国徽、国歌\n" +
            "侵害英雄烈士名誉、荣誉\n" +
            "组织、利用会道门、邪教组织、利用迷信破坏法律实施\n" +
            "组织、利用会道门、邪教组织、利用迷信致人重伤、死亡\n" +
            "聚众淫乱\n" +
            "引诱未成年人聚众淫乱\n" +
            "盗窃、侮辱、故意毁坏尸体、尸骨、骨灰\n" +
            "赌博\n" +
            "开设赌场\n" +
            "组织参与国（境）外赌博\n" +
            "故意延误投递邮件\n" +
            "伪证\n" +
            "辩护人、诉讼代理人毁灭证据、伪造证据、妨害作证\n" +
            "妨害作证\n" +
            "帮助毁灭、伪造证据\n" +
            "虚假诉讼\n" +
            "打击报复证人\n" +
            "泄露不应公开的案件信息\n" +
            "披露、报道不应公开的案件信息\n" +
            "扰乱法庭秩序\n" +
            "窝藏、包庇\n" +
            "拒绝提供间谍犯罪、恐怖主义犯罪、极端主义犯罪证据\n" +
            "掩饰、隐瞒犯罪所得、犯罪所得收益\n" +
            "拒不执行判决、裁定\n" +
            "非法处置查封、扣押、冻结的财产\n" +
            "破坏监管秩序\n" +
            "脱逃\n" +
            "劫夺被押解人员\n" +
            "组织越狱\n" +
            "暴动越狱\n" +
            "聚众持械劫狱\n" +
            "组织他人偷越国（边）境\n" +
            "骗取出境证件\n" +
            "提供伪造、变造的出入境证件\n" +
            "出售出入境证件\n" +
            "运送他人偷越国（边）境\n" +
            "偷越国（边）境\n" +
            "破坏界碑、界桩\n" +
            "破坏永久性测量标志\n" +
            "故意损毁文物\n" +
            "故意损毁名胜古迹\n" +
            "过失损毁文物\n" +
            "非法向外国人出售、赠送珍贵文物\n" +
            "倒卖文物\n" +
            "非法出售、私赠文物藏品\n" +
            "盗掘古文化遗址、古墓葬\n" +
            "盗掘古人类化石、古脊椎动物化石\n" +
            "抢夺、窃取国有档案\n" +
            "擅自出卖、转让国有档案\n" +
            "妨害传染病防治\n" +
            "传染病菌种、毒种扩散\n" +
            "妨害国境卫生检疫\n" +
            "非法组织卖血\n" +
            "强迫卖血\n" +
            "非法采集、供应血液、制作、供应血液制品\n" +
            "采集、供应血液、制作、供应血液制品事故\n" +
            "非法采集人类遗传资源、走私人类遗传资源材料\n" +
            "医疗事故\n" +
            "非法行医\n" +
            "非法进行节育手术\n" +
            "非法植入基因编辑、克隆胚胎\n" +
            "妨害动植物防疫、检疫\n" +
            "污染环境\n" +
            "非法处置进口的固体废物\n" +
            "擅自进口固体废物\n" +
            "非法捕捞水产品\n" +
            "危害珍贵、濒危野生动物\n" +
            "非法狩猎\n" +
            "非法猎捕、收购、运输、出售陆生野生动物\n" +
            "非法占用农用地\n" +
            "破坏自然保护地\n" +
            "非法采矿\n" +
            "破坏性采矿\n" +
            "危害国家重点保护植物\n" +
            "非法引进、释放、丢弃外来入侵物种\n" +
            "盗伐林木\n" +
            "滥伐林木\n" +
            "非法收购、运输盗伐、滥伐的林木\n" +
            "走私、贩卖、运输、制造毒品\n" +
            "非法持有毒品\n" +
            "包庇毒品犯罪分子\n" +
            "窝藏、转移、隐瞒毒品、毒赃\n" +
            "非法生产、买卖、运输制毒物品、走私制毒物品\n" +
            "非法种植毒品原植物\n" +
            "非法买卖、运输、携带、持有毒品原植物种子、幼苗\n" +
            "引诱、教唆、欺骗他人吸毒\n" +
            "强迫他人吸毒\n" +
            "容留他人吸毒\n" +
            "非法提供麻醉药品、精神药品\n" +
            "妨害兴奋剂管理\n" +
            "组织卖淫\n" +
            "强迫卖淫\n" +
            "协助组织卖淫\n" +
            "引诱、容留、介绍卖淫\n" +
            "引诱幼女卖淫\n" +
            "传播性病\n" +
            "制作、复制、出版、贩卖、传播淫秽物品牟利\n" +
            "为他人提供书号出版淫秽书刊\n" +
            "传播淫秽物品\n" +
            "组织播放淫秽音像制品\n" +
            "组织淫秽表演\n" +
            "阻碍军人执行职务\n" +
            "阻碍军事行动\n" +
            "破坏武器装备、军事设施、军事通信\n" +
            "过失损坏武器装备、军事设施、军事通信\n" +
            "故意提供不合格武器装备、军事设施\n" +
            "过失提供不合格武器装备、军事设施\n" +
            "聚众冲击军事禁区\n" +
            "聚众扰乱军事管理区秩序\n" +
            "冒充军人招摇撞骗\n" +
            "煽动军人逃离部队\n" +
            "雇用逃离部队军人\n" +
            "接送不合格兵员\n" +
            "伪造、变造、买卖武装部队公文、证件、印章\n" +
            "盗窃、抢夺武装部队公文、证件、印章\n" +
            "非法生产、买卖武装部队制式服装\n" +
            "伪造、盗窃、买卖、非法提供、非法使用武装部队专用标志\n" +
            "战时拒绝、逃避征召、军事训练\n" +
            "战时拒绝、逃避服役\n" +
            "战时故意提供虚假敌情\n" +
            "战时造谣扰乱军心\n" +
            "战时窝藏逃离部队军人\n" +
            "战时拒绝、故意延误军事订货\n" +
            "战时拒绝军事征收、征用\n" +
            "贪污\n" +
            "挪用公款\n" +
            "受贿\n" +
            "单位受贿\n" +
            "利用影响力受贿\n" +
            "行贿\n" +
            "对有影响力的人行贿\n" +
            "对单位行贿\n" +
            "介绍贿赂\n" +
            "单位行贿\n" +
            "巨额财产来源不明\n" +
            "隐瞒境外存款\n" +
            "私分国有资产\n" +
            "私分罚没财物\n" +
            "滥用职权\n" +
            "玩忽职守\n" +
            "故意泄露国家秘密\n" +
            "过失泄露国家秘密\n" +
            "徇私枉法\n" +
            "民事、行政枉法裁判\n" +
            "执行判决、裁定失职\n" +
            "执行判决、裁定滥用职权\n" +
            "枉法仲裁\n" +
            "私放在押人员\n" +
            "失职致使在押人员脱逃\n" +
            "徇私舞弊减刑、假释、暂予监外执行\n" +
            "徇私舞弊不移交刑事案件\n" +
            "滥用管理公司、证券职权\n" +
            "徇私舞弊不征、少征税款\n" +
            "徇私舞弊发售发票、抵扣税款、出口退税\n" +
            "违法提供出口退税凭证\n" +
            "国家机关工作人员签订、履行合同失职被骗\n" +
            "违法发放林木采伐许可证\n" +
            "环境监管失职\n" +
            "食品、药品监管渎职\n" +
            "传染病防治失职\n" +
            "非法批准征收、征用、占用土地\n" +
            "非法低价出让国有土地使用权\n" +
            "放纵走私\n" +
            "商检徇私舞弊\n" +
            "商检失职\n" +
            "动植物检疫徇私舞弊\n" +
            "动植物检疫失职\n" +
            "放纵制售伪劣商品犯罪行为\n" +
            "办理偷越国（边）境人员出入境证件\n" +
            "放行偷越国（边）境人员\n" +
            "不解救被拐卖、绑架妇女、儿童\n" +
            "阻碍解救被拐卖、绑架妇女、儿童\n" +
            "帮助犯罪分子逃避处罚\n" +
            "招收公务员、学生徇私舞弊\n" +
            "失职造成珍贵文物损毁、流失\n" +
            "战时违抗命令\n" +
            "隐瞒、谎报军情\n" +
            "拒传、假传军令\n" +
            "投降\n" +
            "战时临阵脱逃\n" +
            "擅离、玩忽军事职守\n" +
            "阻碍执行军事职务\n" +
            "指使部属违反职责\n" +
            "违令作战消极\n" +
            "拒不救援友邻部队\n" +
            "军人叛逃\n" +
            "非法获取军事秘密\n" +
            "为境外窃取、剌探、收买、非法提供军事秘密\n" +
            "故意泄露军事秘密\n" +
            "过失泄露军事秘密\n" +
            "战时造谣惑众\n" +
            "战时自伤\n" +
            "逃离部队\n" +
            "武器装备肇事\n" +
            "擅自改变武器装备编配用途\n" +
            "盗窃、抢夺武器装备、军用物资\n" +
            "非法出卖、转让武器装备\n" +
            "遗弃武器装备\n" +
            "遗失武器装备\n" +
            "擅自出卖、转让军队房地产\n" +
            "虐待部属\n" +
            "遗弃伤病军人\n" +
            "战时拒不救治伤病军人\n" +
            "战时残害居民、掠夺居民财物\n" +
            "私放俘虏\n" +
            "虐待俘虏\n";


}