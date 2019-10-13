package com.cy.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//@ControllerAdvice这个注解会拦截所有标注@Controller的请求
@ControllerAdvice
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //异常处理方法,处理Exception级别的异常
    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {

        logger.error("Request URL: {}, Exception: {}", request.getRequestURL(), e);

        //如果拦截到一个抛出404状态码的异常，该类不进行处理，交给springboot处理
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){

            throw e;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url", request.getRequestURL());
        modelAndView.addObject("exception", e);
        modelAndView.setViewName("error/error");

        return modelAndView;
    }
}
