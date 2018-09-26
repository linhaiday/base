package com.example.groovy

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.alibaba.fastjson.JSONObject
import com.fasterxml.jackson.core.type.TypeReference



class evalScriptText {

    static void main(String[] args) {

        groovyShell();
        //test2();
        //test3();

        int z = add(123,321);
        //println("x+y="+z);

        println test("abc", "def", "ghi");
    }

    static groovyShell(){
        Binding binding = new Binding();

        binding.setVariable("var", 5);

        GroovyShell gs = new GroovyShell(binding);

        Object value = gs.evaluate("println 'Hello Groovy !';abc=123;return var*10");//执行groovyshell脚本

        println value;

        System.out.println(value.equals(50));

        println binding.getVariables();

        System.out.println(binding.getVariable("abc").equals(123));
    }

    static test2(){
        ClassLoader parent = ClassLoader.getSystemClassLoader();

        GroovyClassLoader loader = new GroovyClassLoader(parent);

        Class groovyClass = loader.parseClass(new File("D:\\IdeaProjects\\base\\web\\src\\main\\java\\com\\example\\groovy\\evalScriptText.groovy"));

        GroovyObject groovyObject = (GroovyObject)groovyClass.newInstance();

        Object[] param = [123,321];

        int res = (int) groovyObject.invokeMethod("add", param);

        System.out.println("res="+res);
    }

    static int add(int x,int y) {

        return x+y;

    }

    static test3(){
        String path = "D:\\IdeaProjects\\base\\web\\src\\main\\java\\com\\example\\groovy\\";

        GroovyScriptEngine gse = new GroovyScriptEngine(path);

        Binding binding = new Binding();

        binding.setVariable("input", "Groovy");

        gse.run("evalScriptText.groovy", binding);

        System.out.println(binding.getVariable("output"));

    }

    static JSONArray test(String str1, String str2, String str3) {
        println(str1 + "  " + str2 + "  " + str3);

        def clos = {println "Hello ${it}"};
        clos.call(str1);

        String jsonString = "[\"wei.hu\",\"mengna.shi\",\"fastJson\"]"

        return JSON.parseArray(jsonString);
    }


}