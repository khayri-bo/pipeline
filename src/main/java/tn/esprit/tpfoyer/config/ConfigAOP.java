package tn.esprit.tpfoyer.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class ConfigAOP {

    @Before("execution(* tn.esprit.tpfoyer.service.*.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        log.info("Entering method: " + methodName + " with arguments: " + arguments);
    }

    @After("execution(* tn.esprit.tpfoyer.service.*.add*(..))")
    public void logMethodOut(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        log.info("Method " + methodName + " executed successfully!");
    }

    @Around("execution(* tn.esprit.tpfoyer.service.*.*(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();

        Object obj = pjp.proceed();

        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Method execution time: " + elapsedTime + " milliseconds.");

        return obj;
    }
}
