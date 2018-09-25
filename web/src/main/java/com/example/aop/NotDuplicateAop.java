package com.example.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@Aspect
@Component
public class NotDuplicateAop {

    private static final Set<String> KEY = new ConcurrentSkipListSet<>();

    @Pointcut("@annotation(com.example.filter.NotDuplicate)")
    public void dulicate(){
    }

    @Around("dulicate()")
    public Object duplicate(ProceedingJoinPoint pjp) throws Exception{

        System.out.println("重复请求验证...");
        MethodSignature msig = (MethodSignature) pjp.getSignature();
        Method currentMethod = pjp.getTarget().getClass().getMethod(msig.getName(), msig.getParameterTypes());
        //拼接签名
        StringBuilder sb = new StringBuilder(currentMethod.toString());
        Object[] args = pjp.getArgs();
        for (Object object : args) {
            if(object != null){
                sb.append(object.getClass().toString());
                sb.append(object.toString());
            }
        }
        String sign = sb.toString();
        boolean success = KEY.add(sign);
        if(!success){

            throw new RuntimeException("该方法正在执行,不能重复请求");
        }
        try {
            return pjp.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            KEY.remove(sign);
        }

        return null;
    }
}
