package xin;

import javassist.*;
import org.junit.Test;

import java.io.IOException;

public class Demo1 {
    @Test
    public void test1() throws CannotCompileException, IOException, NotFoundException {
        ClassPool classPool = ClassPool.getDefault();
        /*创建类*/
        CtClass ctClass = classPool.makeClass("xin.Emp");

        /*添加属性*/
        CtField ctField1 = CtField.make("private int empno;", ctClass);
        ctClass.addField(ctField1);

        CtField ctField2 = new CtField(classPool.get("java.lang.String"), "ename", ctClass);
        ctField2.setModifiers(Modifier.PRIVATE);
        ctClass.addField(ctField2);

        /*添加方法*/
        CtMethod ctMethod1 = CtMethod.make("public int getEmpno(){return this.empno;}", ctClass);
        ctClass.addMethod(ctMethod1);

        /*CtMethod(CtClass returnType, String mname,
                    CtClass[] parameters, CtClass declaring)*/
        CtMethod ctMethod2 = new CtMethod(CtClass.voidType, "setEmpno", new CtClass[]{CtClass.intType}, ctClass);
        ctMethod2.setBody("this.empno = $1;");

        /*添加构造方法*/
        /*CtConstructor(CtClass[] parameters, CtClass declaring)*/
        CtConstructor ctConstructor = new CtConstructor(new CtClass[]{CtClass.intType}, ctClass);
        ctConstructor.setBody("this.empno = $1;");
        ctClass.addConstructor(ctConstructor);

        ctClass.addMethod(ctMethod2);

        ctClass.writeFile("src/test");
    }
}