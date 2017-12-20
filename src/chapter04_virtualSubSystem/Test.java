package chapter04_virtualSubSystem;

import javassist.*;

public class Test {
    public int f(int i) {
        return i + 1;
    }

    public static void main(String[] args) throws Exception {
        ClassPool pool = ClassPool.getDefault();

        CtClass cc = pool.get("chapter04_virtualSubSystem.Test");
        try {
            cc.getDeclaredMethod("g");
            System.out.println("g() is already defined in sample.Test.");
        }
        catch (NotFoundException e) {
            /* getDeclaredMethod() throws an exception if g()
             * is not defined in sample.Test.
             */
            CtMethod fMethod = cc.getDeclaredMethod("f");
            CtMethod gMethod = CtNewMethod.copy(fMethod, "g", cc, null);
            cc.addMethod(gMethod);
            cc.writeFile();	// update the class file
            System.out.println("g() was added.");
        }
    }
}
