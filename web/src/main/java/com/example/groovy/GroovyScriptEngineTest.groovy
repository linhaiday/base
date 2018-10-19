package com.example.groovy


class GroovyScriptEngineTest {
    static void main(String[] args) throws Exception {
        Binding binding = new Binding();
        binding.setVariable("javaFunction", new JavaFunction());
        GroovyShell gs = new GroovyShell(binding);
        Object value = gs.evaluate(
                //可以引入java类
                "import com.example.groovy.JavaFunction;"
                        + "println 'Groovy可以直接import当前classpath里的类！';"
                        //可以写Groovy语法的代码。Groovy是动态语言，比java适合写脚本
                        + "println '运行了Groovy代码！';"
                        //可以使用java风格的Groovy，其实就是java代码，因为Groovy支持java
                        + "String javaCode = \"运行了Java风格的Groovy代码！\";"
                        + "System.out.println(javaCode);"
                        //可以直接创建java自定义的对象
                        + "JavaFunction newJavaFunction = new JavaFunction();"
                        + "println \"在Groovy引擎里创建java对象，并\" + newJavaFunction.run();"
                        //可以调用脚本预设的java对象
                        + "return javaFunction.run();");
        System.out.println("为GroovyShell预设java对象，并在Groovy里" + value + "，同时证明Groovy可以返回变量给java。");
    }
}
