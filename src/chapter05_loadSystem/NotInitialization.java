package chapter05_loadSystem;

public class NotInitialization {
    static {
        System.out.println("NotInitialization init");
    }

    public static void main(String[] args)
    {
        System.out.println(ConstClass.V);
    }
}
