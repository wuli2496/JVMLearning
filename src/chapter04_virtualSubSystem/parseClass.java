package chapter04_virtualSubSystem;

import util.ClassUtil;

public class parseClass {
    public static void main(String[] args)
    {
        try {
            ClassUtil.parseFile(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
