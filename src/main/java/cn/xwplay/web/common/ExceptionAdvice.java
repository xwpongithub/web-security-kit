package cn.xwplay.web.common;

import cn.hutool.core.util.StrUtil;
import cn.xwplay.web.security.validate.ValidateCodeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestCookieException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionAdvice {

    @Value("${spring.profiles.active}")
    private String env;

    @ExceptionHandler(ValidateCodeException.class)
    public Response handleValidateCodeException(ValidateCodeException e) {
        printStackTrace(e);
        return Response.error(e.getMessage());
    }

    @ExceptionHandler({MissingRequestHeaderException.class})
    public Response handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        printStackTrace(e);
        String paramKey =StrUtil.subBetween(e.getMessage(),"'");
        return Response.error("header中"+paramKey+"参数不存在");
    }

    @ExceptionHandler({MissingRequestCookieException.class})
    public Response handleMissingRequestCookieException(MissingRequestCookieException e) {
        printStackTrace(e);
        String paramKey =StrUtil.subBetween(e.getMessage(),"'");
        return Response.error("cookie中"+paramKey+"参数不存在");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Response handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        printStackTrace(e);
        String paramKey =StrUtil.subBetween(e.getMessage(),"'");
        return Response.error(paramKey+"参数不存在");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Response handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        printStackTrace(e);
        String paramKey =StrUtil.subBetween(e.getMessage(),"'");
        return Response.error("不支持"+paramKey+"方式的请求");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Response handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        printStackTrace(e);
        return Response.error("请求体不能为空");
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Response handleConstraintViolationException(ConstraintViolationException e) throws NoSuchFieldException {
        printStackTrace(e);
        for (ConstraintViolation<?> constraint : e.getConstraintViolations()) {
            System.out.println(constraint.getPropertyPath());
            return Response.error(constraint.getMessage());
        }
        return Response.error(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        for (ObjectError error : bindingResult.getAllErrors()) {
            for (Object argument : error.getArguments()) {
                DefaultMessageSourceResolvable arg = (DefaultMessageSourceResolvable)argument;
                String fieldName = arg.getCode();
                return Response.error(400,error.getDefaultMessage());
            }
        }
        return Response.error();
    }

    private void printStackTrace(Exception e) {
        if (!StrUtil.equals(env, "prod")){
            e.printStackTrace();
        }
    }

}
