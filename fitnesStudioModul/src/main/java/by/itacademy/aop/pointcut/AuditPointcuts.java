package by.itacademy.aop.pointcut;

import org.aspectj.lang.annotation.Pointcut;

public class AuditPointcuts {

    @Pointcut("execution(void add(*))")
    public void allAddMethods() {
    }

    @Pointcut("execution(public void update(..))")
    public void allUpdateMethods() {
    }
}