package com.andreitop.newco.aspect;


import com.andreitop.newco.dto.TripDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);


    @Before("com.andreitop.newco.aspect.PointcutContainer.repositoryFind()")
    public void beforeRepoFind(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + className + "." + methodName + " is about to be called");
    }

    @AfterReturning("com.andreitop.newco.aspect.PointcutContainer.repositoryFind()")
    public void afterRepoFind(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + className + "." + methodName + " was called right now");
    }

    @Around("com.andreitop.newco.aspect.PointcutContainer.serviceClassMethods()")
    public Object aroundService(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName();
        logger.info(" ---> Method " + className + "." + methodName + " is about to be called");
        Object retVal = pjp.proceed();
        logger.info(" ---> Method " + className + "." + methodName + " was called right now");
        return retVal;
    }

    @After("com.andreitop.newco.aspect.PointcutContainer.deleteMethod()")
    public void afterDeleteMethod(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Method " + className + "." + methodName + " was called right now");
    }

    @Before("com.andreitop.newco.aspect.PointcutContainer.postMethods()")
    public void beforePostRequest(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Post request " + className + "." + methodName + " is about to be sent");
    }

    @Before("com.andreitop.newco.aspect.PointcutContainer.postMethods() && args(trip,..)")
    public void afterMethodWithRequestBody(JoinPoint joinPoint, TripDto trip) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        logger.info(" ---> Post request " + className + "." + methodName + " was called right now");
        logger.info(" ---> Content of body: " + trip.getOrigin() + " " + trip.getDestination() + " " + trip.getPrice());
    }

}
