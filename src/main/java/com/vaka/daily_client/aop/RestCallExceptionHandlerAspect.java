package com.vaka.daily_client.aop;

import com.vaka.daily_client.exception.ServerNotRespondingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

@Aspect
@Component
@Slf4j
public class RestCallExceptionHandlerAspect {
    @Around("@annotation(RestCall)")
    public Object handleResourceAccessException(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (ResourceAccessException e) {
            log.error("Server is not responding: ", e);
            throw new ServerNotRespondingException();
        }
    }
}
