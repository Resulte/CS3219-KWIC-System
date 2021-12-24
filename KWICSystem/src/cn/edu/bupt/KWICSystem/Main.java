package cn.edu.bupt.KWICSystem;

import cn.edu.bupt.KWICSystem.kwic.KWIC;
import cn.edu.bupt.KWICSystem.kwic.impl.KWICImpl;

public class Main {

    public static void main(String[] args) {

        KWIC kwic = new KWICImpl();
        kwic.execute();

    }
}
