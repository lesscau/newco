package com.andreitop.newco.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointcutContainer {

    @Pointcut("execution( * com.andreitop.newco.repository.*Repo*.find*(..))")
    public void repositoryFind() {
    }

    @Pointcut("execution( * *delete*(..))")
    public void deleteMethod() {
    }

    @Pointcut("within(com.andreitop.newco.service.*)")
    public void serviceClassMethods() {
    }

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMethods() {
    }

}
