package cn.imust.web.controller.utils;

import cn.imust.domain.system.SysLog;
import cn.imust.domain.system.User;
import cn.imust.service.system.SysLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Component  //组件
@Aspect     //AOP切面
public class SysLogAspect {
    @Autowired
    private HttpSession session;
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private SysLogService sysLogService;

    @Around(value = "execution(* cn.imust.web.controller.*.*.*(..))")
    public Object addSysLog(ProceedingJoinPoint pjp) throws Throwable {
        //从session中获取用户信息
        User user=(User)session.getAttribute("loginUser");
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        if (user != null) {
            SysLog sysLog = new SysLog();
            sysLog.setId(UUID.randomUUID().toString());
            sysLog.setTime(new Date());
            sysLog.setUserName(user.getUserName());
            sysLog.setCompanyName(user.getCompanyName());
            sysLog.setCompanyId(user.getCompanyId());
            sysLog.setIp(request.getLocalAddr());
            sysLog.setMethod(method.getName());
            RequestMapping requestMapping = method.getDeclaredAnnotation(RequestMapping.class);
            if (requestMapping != null) {
                sysLog.setAction(requestMapping.name());
                sysLogService.save(sysLog);
            }
        }
        return pjp.proceed();
    }
}
