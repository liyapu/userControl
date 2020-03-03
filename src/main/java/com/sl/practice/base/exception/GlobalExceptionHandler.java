package com.sl.practice.base.exception;


import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.sl.practice.base.constant.Const;
import com.sl.practice.base.enums.ResultStatusEnum;
import com.sl.practice.base.utils.IpUtil;
import com.sl.practice.base.web.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.ClassUtils;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * description: 全局异常参数格式化处理
 *
 * @ControllerAdvice + @ExceptionHandler 进行全局的 Controller 层异常处理，只要设计得当，就再也不用在 Controller 层进行 try-catch 了！
 * 而且，@Validated 校验器注解的异常，也可以一起处理，无需手动判断绑定校验结果 BindingResult/Errors 了！
 *
 * 1.注解@ControllerAdvice方式只能处理控制器抛出的异常。此时请求已经进入控制器中。
 * 2.类ErrorController方式可以处理所有的异常，包括未进入控制器的错误，比如404,401等错误
 * 3.如果应用中两者共同存在，则@ControllerAdvice方式处理控制器抛出的异常，类ErrorController方式未进入控制器的异常。
 * 4.@ControllerAdvice方式可以定义多个拦截方法，拦截不同的异常类，并且可以获取抛出的异常信息，自由度更大。
 *
 *
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    Logger logger = LoggerFactory.getLogger(getClass());

    //基础异常
    @ExceptionHandler(BaseException.class)
    public WebResult BaseExceptionHandler(BaseException be) {
        Integer code = be.getCode();
        if(code == null){
            code = ResultStatusEnum.BUSINESS_ERR.getCode();
            logger.error("base exception code is null.",be);
        }
        WebResult webResult = WebResult.failed(code,be.getMessage());
        printLog(webResult,be);
        return webResult;
    }


    /**
     * 参数错误
     * HttpMessageNotReadableException ：请求体缺失
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public WebResult httpMessageNotReadableExceptionHandler( HttpMessageNotReadableException ex){
        String detailErrMsg = Strings.lenientFormat("HttpMessageNotReadableException: %s",ex.getMessage());
        return commonHandler(ResultStatusEnum.BAD_REQUEST,ex,detailErrMsg);
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public WebResult httpMessageNotWritableExceptionHandler( HttpMessageNotWritableException ex) {
        String detailErrMsg = String.format("HttpMessageNotWritableException:  %s ", ex.getMessage());
        return commonHandler(ResultStatusEnum.BAD_REQUEST,ex,detailErrMsg);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResult methodArgumentNotValidExceptionHandler( MethodArgumentNotValidException ex) {
        String detailErrMsg = ex.getMessage();
        return commonHandler(ResultStatusEnum.BAD_REQUEST,ex,detailErrMsg);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public WebResult missingServletRequestPartExceptionHandler( MissingServletRequestPartException ex) {
        String detailErrMsg = Strings.lenientFormat("MissingServletRequestPartException:  request part %s 丢失", ex.getRequestPartName());
        return commonHandler(ResultStatusEnum.BAD_REQUEST,ex,detailErrMsg);
    }

    @ExceptionHandler({BindException.class})
    public WebResult bindExceptionHandler( BindException ex) {
        StringBuilder sb = new StringBuilder();
        if (ex.hasFieldErrors()) {
            ex.getFieldErrors().forEach(error ->
                    sb.append(Strings.lenientFormat("参数 %s(%s) 错误：%s ", error.getField(), error.getRejectedValue(), error.getDefaultMessage()))
            );
        } else if (ex.hasGlobalErrors()) {
            ex.getGlobalErrors().forEach(error ->
                    sb.append(Strings.lenientFormat("参数 %s 错误： %s ", error.getArguments(), error.getDefaultMessage()))
            );
        }
        String detailErrMsg = String.format("BindException: %s", sb.toString());
        return commonHandler(ResultStatusEnum.REQUEST_PARAM_ERR,ex,detailErrMsg);
    }

    /**
     * MissingServletRequestParameterException : 缺少请求参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public WebResult missingServletRequestParameterExceptionHandler( MissingServletRequestParameterException ex){
        String detailErrMsg = Strings.lenientFormat("MissingServletRequestParameterException: 缺失 [%s] 类型的 [%s] 参数",ex.getParameterType(),ex.getParameterName());
        return commonHandler(ResultStatusEnum.BAD_REQUEST,ex,detailErrMsg);
    }


    @ExceptionHandler(ServletRequestBindingException.class)
    public WebResult servletRequestBindingExceptionHandler( ServletRequestBindingException ex) {
        String detailErrMsg = Strings.lenientFormat("ServletRequestBindingException: 请求绑定异常 %s",ex.getMessage());
        return commonHandler(ResultStatusEnum.BAD_REQUEST,ex,detailErrMsg);
    }

    @ExceptionHandler(ConversionNotSupportedException.class)
    public WebResult conversionNotSupportedExceptionHandler( ConversionNotSupportedException ex) {
        String detailErrMsg = Strings.lenientFormat("ConversionNotSupportedException:  属性 %s, 值 %s, 类型 %s, 目标类型 %s, 错误码 %s",
                ex.getPropertyName(), ex.getValue(), ClassUtils.getDescriptiveType(ex.getValue()),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : ex.getCause(), ex.getErrorCode());
       return commonHandler(ResultStatusEnum.BAD_REQUEST,ex,detailErrMsg);
    }

    /**
     * 不支持当前请求方法
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public WebResult httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex){
        String detailErrMsg = Strings.lenientFormat("HttpRequestMethodNotSupportedException: 传入值 [%s], 允许值 %s", ex.getMethod(), ex.getSupportedHttpMethods());
        return commonHandler(ResultStatusEnum.METHOD_NOT_ALLOWED,ex,detailErrMsg);
    }

    /**
     * 方法参数类型不匹配
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public WebResult methodArgumentTypeMismatchExceptionHandler( MethodArgumentTypeMismatchException ex){
        String detailErrMsg = Strings.lenientFormat("参数类型不匹配, 参数 %s(%s), 类型应该为 %s",
                ex.getName(), ex.getValue(), ex.getRequiredType() == null ? ex.getRequiredType() : ex.getRequiredType().getSimpleName());
        return commonHandler(ResultStatusEnum.REQUEST_METHOD_PARAM_TYPE_ERR,ex,detailErrMsg);
    }

    @ExceptionHandler(TypeMismatchException.class)
    public WebResult typeMismatchExceptionHandler( TypeMismatchException ex) {
        String detailErrMsg = String.format("TypeMismatchException:  属性 %s, 值 %s, 类型 %s, 目标类型 %s,错误码 %s",
                ex.getPropertyName(), ex.getValue(), ClassUtils.getDescriptiveType(ex.getValue()),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : ex.getCause(), ex.getErrorCode());
        return commonHandler(ResultStatusEnum.BAD_REQUEST,ex,detailErrMsg);
    }

    /**
     * NoHandlerFoundException 没有对应处理器异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public WebResult noHandlerFoundExceptionHandler(NoHandlerFoundException ex){
        String detailErrMsg = String.format("NoHandlerFoundException:  [%s] %s 不存在匹配路径 ", ex.getHttpMethod(), ex.getRequestURL());
        return commonHandler(ResultStatusEnum.BAD_REQUEST,ex,detailErrMsg);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public WebResult illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        String detailErrMsg = Strings.lenientFormat("非法参数 %s", ex.getMessage());
        return commonHandler(ResultStatusEnum.BAD_REQUEST,ex,detailErrMsg);
    }

    @ExceptionHandler(IOException.class)
    public WebResult ioExceptionHandler( IOException ex) {
        String detailErrMsg = Strings.lenientFormat("IO异常 %s", ex.getMessage());
       return commonHandler(ResultStatusEnum.SYSTEM_ERR,ex,detailErrMsg);
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public WebResult asyncRequestTimeoutExceptionHandler(AsyncRequestTimeoutException ex) {
        String detailErrMsg = Strings.lenientFormat("NoHandlerFoundException:  请求超时 %s ", ex.getCause());
        return commonHandler(ResultStatusEnum.REQUEST_TIMEOUT,ex,detailErrMsg);
    }

    /**
     * 媒体类型
     * HttpMediaTypeNotAcceptableException 客户端请求期望响应的媒体类型与服务器响应的媒体类型不一致造成
     */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public WebResult httpMediaTypeNotAcceptableExceptionHandler( HttpMediaTypeNotAcceptableException ex){
        String detailErrMsg = Strings.lenientFormat("HttpMediaTypeNotAcceptableException: 允许值 %s", ex.getSupportedMediaTypes());
        return commonHandler(ResultStatusEnum.REQUEST_MEDIA_TYPE_ERR,ex,detailErrMsg);
    }

    /**
     * HttpMediaTypeNotSupportedException
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public WebResult httpMediaTypeNotAcceptableExceptionHandler( HttpMediaTypeNotSupportedException ex){
        String detailErrMsg = Strings.lenientFormat("HttpMediaTypeNotSupportedException: 传入值 [%s], 允许值 %s", ex.getContentType(), ex.getSupportedMediaTypes());
        return commonHandler(ResultStatusEnum.REQUEST_MEDIA_TYPE_ERR,ex,detailErrMsg);
    }

    /**
     * MissingPathVariableException
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public WebResult missingPathVariableExceptionHandler(MissingPathVariableException ex){
        String detailErrMsg = Strings.lenientFormat("MissingPathVariableException:缺失路径参数 %s，类型 %s",
                ex.getVariableName(), ex.getParameter().getParameterType().getSimpleName());
        return commonHandler(ResultStatusEnum.BAD_REQUEST,ex,detailErrMsg);
    }





    /**
     * 未知异常 ---> 系统繁忙
     * NullPointerException : 空指针异常
     * ClassCastException : 类型转换异常
     * ArrayIndexOutOfBoundsException : 数组越界异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {NullPointerException.class,ClassCastException.class,ArrayIndexOutOfBoundsException.class})
    public WebResult unknownExceptionHandler( Exception ex) {
        return commonHandler(ResultStatusEnum.SYSTEM_ERR,ex);
    }


    public WebResult commonHandler(ResultStatusEnum resultStatusEnum, Exception ex){
       return commonHandler(resultStatusEnum,ex,"");
    }

    public WebResult commonHandler(ResultStatusEnum resultStatusEnum, Exception ex,String detailErrMsg){
        WebResult webResult = WebResult.failed(resultStatusEnum,detailErrMsg);
        printLog(webResult,ex);
        return  webResult;
    }

    /**
     * 其它异常,默认处理器
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public WebResult defaultExceptionHandler(Exception ex) {
        WebResult webResult = WebResult.failed(ResultStatusEnum.SYSTEM_ERR);
        printLog(webResult,ex);
        return webResult;
    }


    /**
     * 异常信息打印日志
     * request中的请求参数，有时候获取不到，没有 WebLogAspect 中记录的请求参数全
     * @param webResult
     * @param ex
     */
    private void printLog( WebResult webResult, Exception ex){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String requestUrl = request.getRequestURL().toString();
        String methodName = request.getMethod();
        String contentType = request.getContentType();
        String requestParams = JSONObject.toJSONString(request.getParameterMap());
        String clientIp = IpUtil.getClientAddr(request);
        String token = request.getHeader(Const.TOKEN);
        String requestUuid = request.getAttribute(Const.REQUEST_TRACE_ID) == null ? "":request.getAttribute(Const.REQUEST_TRACE_ID).toString();
        logger.error("global exception handler requestUrl={}, methodName={},contentType={},requestParams={},clientIP={},token={},requestUuid={},webResult={}",
                requestUrl,methodName,contentType,requestParams,clientIp,token,requestUuid,JSONObject.toJSONString(webResult),ex);
    }

}
