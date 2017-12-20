package chapter02_memoryAreaAndMemoryOverflowException;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class DirectMemoryOOM
{
    private static final int _1M = 1024 * 1024;

    public static void main(String[] args) throws Exception
    {
        Field unsafeFiled = Unsafe.class.getDeclaredFields()[0];
        unsafeFiled.setAccessible(true);
        Unsafe unsafe = (Unsafe)unsafeFiled.get(null);
        while (true)
        {
            unsafe.allocateMemory(_1M);
        }
    }
}
