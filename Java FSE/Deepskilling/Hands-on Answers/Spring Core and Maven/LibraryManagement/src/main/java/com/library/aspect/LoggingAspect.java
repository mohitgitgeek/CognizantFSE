package com.library.aspect;
import org.aspectj.lang.*; import org.aspectj.lang.annotation.*; import org.springframework.stereotype.Component;
@Aspect @Component public class LoggingAspect { @Around("execution(* com.library.service..*(..))") public Object time(ProceedingJoinPoint p)throws Throwable {long start=System.nanoTime();try{return p.proceed();}finally{System.out.println(p.getSignature()+" took "+(System.nanoTime()-start)+" ns");}} }
