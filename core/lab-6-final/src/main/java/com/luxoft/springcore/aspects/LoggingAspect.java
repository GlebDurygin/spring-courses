package com.luxoft.springcore.aspects;

import com.luxoft.springcore.objects.City;
import com.luxoft.springcore.objects.Person;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.luxoft.springcore.travel.TravelManager+.travel(..))")
    public void travelMethod() {
    }

    @Around("travelMethod() ")
    public Object travelLogger(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        Object[] methodArgs = thisJoinPoint.getArgs();
        Person person = (Person) methodArgs[0];
        City destination = (City) methodArgs[1];
        Object result = thisJoinPoint.proceed();
        System.out.println(person.getName() + " has arrived to " + destination.toString());
        return result;
    }
}
