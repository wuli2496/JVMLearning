package chapter05_loadSystem;

import java.util.Random;

public class ConstClass {
    static {
        System.out.println("ConstClass Init");
    }

    public static final int V = new Random().nextInt();
}
