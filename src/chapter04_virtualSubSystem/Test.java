package chapter04_virtualSubSystem;

import javassist.*;

public class Test {
    public int f(int i) {
        return i + 1;
    }

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();

        CtClass cc = pool.get("chapter04_virtualSubSystem.Hello");
        CtMethod m = cc.getDeclaredMethod("say");
        m.insertBefore("{System.out.println(\"Hello.say():\");}");
        Class c = cc.toClass();
        Hello h = (Hello)c.newInstance();
        h.say();
    }
}
