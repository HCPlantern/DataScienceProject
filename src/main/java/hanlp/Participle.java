package hanlp;

import com.hankcs.hanlp.HanLP;

public class Participle {

    public void go() {
        System.out.println(HanLP.segment("你好，欢迎使用HanLP汉语处理包！"));
    }

    public static void main(String[] args) {
        Participle participle = new Participle();
        participle.go();
    }
}
