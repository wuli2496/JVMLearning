package chapter06_bytecodeExcutionSystem;

public class StaticDispatch
{
    static abstract class Human {}

    static class Man extends Human {}

    static class Woman extends Human {}

    public void sayHello(Human guy)
    {
        System.out.println("hello, guy!");
    }

    public void sayHello(Woman guy)
    {
        System.out.println("hello, lady!");
    }

    public void sayHello(Man man)
    {
        System.out.println("hello, gentleman");
    }

    public static void main(String[] args)
    {
        Human man = new Man();
        Human woman = new Woman();

        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}
