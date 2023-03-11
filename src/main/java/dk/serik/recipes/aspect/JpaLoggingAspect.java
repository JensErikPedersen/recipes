package dk.serik.recipes.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
/**
 * This Aspects purpose is to log exception thrown by Hibernate Jpa exceptions
 * The aspect are triggered for all joinpoints / methods in all interfaces/classes that extend/implement JpaRepository interface *
 */
public class JpaLoggingAspect {

    @Pointcut("execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))))")
    public void repositoryClassMethods() {}

    @Around("repositoryClassMethods()")
    public Object logRepositoryErrors(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e); // log Jpa Exception
            throw e;
        }
    }
}
