package com.summer.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.summer.annotation.SystemLog;
import com.summer.common.utils.IpUtils;
import com.summer.entity.AccessLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志：切面处理类
 * 前置通知（Before）:在目标方法或者说连接点被调用前执行的通知；
 * 后置通知（After）：指在某个连接点完成后执行的通知；
 * 返回通知（After-returning）：指在某个连接点成功执行之后执行的通知；
 * 异常通知（After-throwing）：指在方法抛出异常后执行的通知；
 * 环绕通知（Around）：指包围一个连接点通知，在被通知的方法调用之前和之后执行自定义的方法。
 */
@Aspect
@Component
@Slf4j
public class SysLogAspect {

    /**
     * 定义切点 @Pointcut
     * 在注解的位置切入代码
     */
    @Pointcut("@annotation(com.summer.annotation.SystemLog)")
    public void exceptionLogPointCut() {
        //define pointcut to SystemLog file
    }

    @Around("exceptionLogPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 获取方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获取方法
        Method method = methodSignature.getMethod();
        //获取请求的类名
        String className = joinPoint.getTarget().getClass().getName();
        //获取请求的方法名
        String methodName = method.getName();
        //请求的参数
        Object[] args = joinPoint.getArgs();
        //将参数所在的数组转换成json
        String params = JSON.toJSONString(args, SerializerFeature.IgnoreNonFieldGetter);
        //获取用户ip地址
        //这个RequestContextHolder是Springmvc提供来获得请求的东西
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        //获取方法的注解
        SystemLog systemLog = method.getAnnotation(SystemLog.class);
        String operation = systemLog.value();
        //保存日志
        AccessLog accessLog = new AccessLog();
        accessLog.setClassName(className);
        accessLog.setMethodName(methodName);
        accessLog.setOperation(operation);
        //获取ip地址
        accessLog.setIp(IpUtils.getIpAddr(request));
        accessLog.setParams(params);

        Object result = null;
        try {
            result = joinPoint.proceed();
            accessLog.setLogLevel("info");
            accessLog.setLogMessage(result.toString());
//          accessLog.setLogMessage(JSON.toJSONString(result));
        } catch (Exception e) {
            log.error("打印异常信息", e);
            // 相当于异常通知部分
            accessLog.setLogLevel("exception");
            accessLog.setLogMessage(e.getMessage());
        } finally {
            // 执行时长(毫秒)
            long time = System.currentTimeMillis() - beginTime;
            accessLog.setTime(time);
            // 相当于最终通知
            accessLog.setCreateTime(new Date());//获取时间
            if ("exception".equals(accessLog.getLogLevel())) {
                log.error("请求信息体 [EXCEPTION]:" + accessLog.toString());
            } else {
                log.info("请求信息体 [INFO]:" + accessLog.toString());
            }

        }
        return result;
    }

}
