package chapter05_loadSystem;

import java.io.IOException;
import java.io.InputStream;

public class ClassLoaderTest
{
    public static void main(String[] args) throws Exception
    {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    System.out.println("thread:" + Thread.currentThread() + " fileName1:" + fileName);
                    InputStream is = getClass().getResourceAsStream(fileName);

                    if (is == null)
                    {
                        System.out.println("is == null");
                        return super.loadClass(name);
                    }
                    System.out.println("thread:" + Thread.currentThread() + " fileName2:" + fileName);

                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name,  b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = classLoader.loadClass("chapter05_loadSystem.ClassLoaderTest");
        //System.out.println(obj.getClass());
        //System.out.println(obj instanceof chapter05_loadSystem.ClassLoaderTest);

        //obj = classLoader.loadClass("java.lang.String");
        //System.out.println(obj.getClass());
    }
}
