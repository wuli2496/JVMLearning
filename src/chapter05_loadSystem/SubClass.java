package chapter05_loadSystem;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass Init");
    }

    public static int value2 = 10;
}
