package com.chen.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*自定义拦截器，拦截所有异常，返回自定义error页面*/

@ControllerAdvice //会拦截所有标注controller注解的控制器
public class ControllerExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * @Author valkyreenv
     * @Description //TODO
     * @Date 11:03 2020/4/14
     * @Param [request, e]
     * @return org.springframework.web.servlet.ModelAndView--返回错误页面并且要附带信息
     **/

    @ExceptionHandler(Exception.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {

        logger.error("Request URL : {}, Exception : {}", request.getRequestURI(),e);

        //判断如果有@ResponseStatus(HttpStatus.NOT_FOUND)的则拦截器不处理，交给springboot处理
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null){
            throw e;
        }

        ModelAndView mv = new ModelAndView();
        mv.addObject("url",request.getRequestURI());
        mv.addObject("exception",e);
        mv.setViewName("error/error");
        return mv;
    }
}
