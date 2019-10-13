package com.cy.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //拦截com.cy.blog这个包下的所有类的任意参数的所有方法
    @Pointcut("execution(* com.cy.blog.web.*.*(..))")
    public void log(){}

    //在切点之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = attributes.getRequest();
        //获得请求的url地址
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //.getDeclaringTypeName()获取方法所在的类名，.getName()获得方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("RequestLog: {}", requestLog);
    }


    @After("log()")
    public void doAfter(){

//        logger.info("------do after------");
    }


    //可以获得方法的返回值
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){

        //将result的值填充到{}中，显示在控制台日志当中
        logger.info("Result: {}", result);
    }

    //内部类，记录日志内容
    private class RequestLog{

        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}
