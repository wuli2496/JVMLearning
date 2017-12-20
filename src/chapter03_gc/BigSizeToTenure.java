package chapter03_gc;

public class BigSizeToTenure {
    private static final int _1MB = 1024 * 1024;

    public static void testPretenureSizeThreshold()
    {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }

    public static void main(String[] args)
    {
        testPretenureSizeThreshold();
    }
}
