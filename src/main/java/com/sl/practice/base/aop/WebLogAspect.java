package com.sl.practice.base.aop;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.sl.practice.base.constant.Const;
import com.sl.practice.base.utils.IpUtil;
import com.sl.practice.base.web.WebResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 *
 * 定义一个切入点.请求日志切面
 * 解释下：
 * 来了解下AspectJ类型匹配的通配符：
 * *：匹配任何数量字符；
 * ..：匹配任何数量字符的重复，如在类型模式中匹配任何数量子包；而在方法参数模式中匹配任何数量参数。
 * +：匹配指定类型的子类型；仅能作为后缀放在类型模式后边。
 * <p>
 *  第一个 * 代表任意修饰符及任意返回值.
 *  第二个 * 任意包名
 *  第三个 * 代表任意方法.
 *  第四个 * 定义在web包或者子包
 *  第五个 * 任意方法
 *  .. 匹配任意数量的参数.
 */
@Aspect
@Component
public class WebLogAspect {

    private final static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Value("${request.print.log}")
    private boolean requestPrintLog;

    @Value("${request.print.result}")
    private boolean requestPrintResult;

    /** 以 controller 包下定义的所有请求为切入点
     * 定义在controller包和所有子包里的任意类的任意方法的执行：
     * */
    @Pointcut("execution(public * com.sl.practice.web.controller..*.*(..))")
    public void webLog() {}

    /**
     * 环绕通知记录请求日志
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object resultObj = null;

        if(requestPrintLog){
            String requestUrl = request.getRequestURL().toString();
            String methodName = request.getMethod();
            String contentType = request.getContentType();
            String classMethod = proceedingJoinPoint.getSignature().getDeclaringTypeName() + "." + proceedingJoinPoint.getSignature().getName();
            String clientIp = IpUtil.getClientAddr(request);
            String requestParams =  JSONObject.toJSONString(request.getParameterMap());
            String token = request.getHeader(Const.TOKEN);
            Throwable th = null;
            long startTime = System.currentTimeMillis();
            String traceId = UUID.randomUUID().toString();
            request.setAttribute(Const.REQUEST_TRACE_ID,traceId);
            //真正执行请求
            try {
                resultObj = proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                th = throwable;
                throw throwable;
            } finally {
                String responseResult = new Gson().toJson(resultObj);
                long costTime = System.currentTimeMillis() - startTime;
                if(th == null){
                    if(requestPrintResult){
                        logger.info("web request aspect log requestUrl={}, methodName={},contentType={},classMethod={},requestParams={},clientIp={},token={},response={},costTime={}ms,traceId={}",
                                requestUrl,methodName,contentType,classMethod,requestParams,clientIp,token,responseResult,costTime,traceId);
                    }else{
                        Integer code = null;
                        String msg = "";
                        if(resultObj != null && resultObj instanceof WebResult){
                            code = ((WebResult) resultObj).getCode();
                            msg = ((WebResult) resultObj).getMsg();
                        }
                        logger.info("web request aspect log requestUrl={}, methodName={},contentType={},classMethod={},requestParams={},clientIp={},token={},responseCode={},responseMsg={},costTime={}ms,traceId={}",
                                requestUrl,methodName,contentType,classMethod,requestParams,clientIp,token,code,msg,costTime,traceId);
                    }
                }else{
                    logger.info("web request aspect log requestUrl={}, methodName={},contentType={},classMethod={},requestParams={},clientIp={},token={},response={},costTime={}ms,traceId={},throwable err",
                            requestUrl,methodName,contentType,classMethod,requestParams,clientIp,token,responseResult,costTime,traceId,th.getMessage());
                    th.printStackTrace();
                }
            }
        }else{
            resultObj = proceedingJoinPoint.proceed();
        }
        return resultObj;
    }
}
