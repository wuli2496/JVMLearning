package chapter09_memoryAndThread;

public class VolatileTest
{
    public static volatile int race = 0;

    public static void increase()
    {
        race++;
    }

    public static final int THRAEDS_COUNT = 20;

    public static void main(String[] args)
    {
        Thread[] threads = new Thread[THRAEDS_COUNT];
        for (int i = 0; i < THRAEDS_COUNT; i++)
        {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10; i++)
                    {
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount() > 1) {
            System.out.println(Thread.activeCount());
        }

        System.out.println(race);
    }
}
