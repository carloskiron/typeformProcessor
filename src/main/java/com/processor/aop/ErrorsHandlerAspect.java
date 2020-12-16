package com.processor.aop;

import com.processor.common.ProcessorException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorsHandlerAspect {

    private static Logger logger = LoggerFactory.getLogger(ErrorsHandler.class);


    @Around("@annotation(com.processor.aop.ErrorsHandler) && execution(public * *(..))")
    public Object log(final ProceedingJoinPoint joinPoint) throws Throwable {

        try {

            long start = System.currentTimeMillis();

            Object proceed = joinPoint.proceed();

            long executionTime = System.currentTimeMillis() - start;

            logger.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

            return proceed;

        } catch (Exception ex) {

            logger.error("Error while invoking: " + joinPoint.getSignature(), ex);
            throw new ProcessorException("Something goes wrong. Check server logs for details");
        }

    }
}
