package chapter06_bytecodeExcutionSystem;

public class LocalVariable
{
    public static void main(String[] args)
    {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
}
