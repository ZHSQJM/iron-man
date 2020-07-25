package com.zhs.mybatis.aop;

import com.google.gson.Gson;
import com.zhs.mybatis.annonation.WebLog;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 17:37
 * @package: com.zhs.mybatis.aop
 * @description:
 */
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    private static  final  String LINE_SEPARATOR = System.lineSeparator();


    @Pointcut("@annotation(com.zhs.mybatis.annonation.WebLog)")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        final HttpServletRequest request = attributes.getRequest();
        final String aspectLogDescription = getAspectLogDescription(joinPoint);
        log.info("===============================开始请求=========================");
        log.info("请求路径:{}",request.getRequestURL().toString());
        log.info("是否开启日志:{}",aspectLogDescription);
        log.info("方法类型:{}",request.getMethod());
        log.info("请求方法:{},{}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
        log.info("请求地址:{}",request.getRemoteAddr());
        log.info("入参:{}",new Gson().toJson(joinPoint.getArgs()));
    }

    @After("webLog()")
    public void doAfter()throws Throwable{
        log.info("===============================请求结束========================="+LINE_SEPARATOR);
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        final long startTime = System.currentTimeMillis();
        final Object result = point.proceed();
        final MethodSignature signature = (MethodSignature)point.getSignature();
        final Method method = signature.getMethod();
        if(method.isAnnotationPresent(WebLog.class)){
            WebLog webLog = method.getAnnotation(WebLog.class);
            if(webLog.resultRequired()){
                log.info("出参:{}",new Gson().toJson(result));
            }
        }
        log.info("响应时间:{}ms",System.currentTimeMillis()-startTime);
        return result;
    }
    public String getAspectLogDescription(JoinPoint joinpoint) throws ClassNotFoundException {
        final String targetName = joinpoint.getTarget().getClass().getName();
        final String methodName = joinpoint.getSignature().getName();
        final Object[] args = joinpoint.getArgs();
        final Class<?> aClass = Class.forName(targetName);
        final Method[] methods = aClass.getMethods();
        StringBuilder description =new StringBuilder();
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                final Class<?>[] parameterTypes = method.getParameterTypes();
                if(parameterTypes.length ==args.length){
                    description.append(method.getAnnotation(WebLog.class).resultRequired());
                    description.append(method.getAnnotation(WebLog.class).value());
                    break;
                }
            }
        }
        return description.toString();
    }
}
