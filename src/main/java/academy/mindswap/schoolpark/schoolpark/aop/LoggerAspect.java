package academy.mindswap.schoolpark.schoolpark.aop;

import academy.mindswap.schoolpark.schoolpark.command.TeacherDTO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class LoggerAspect {
    @Around("@annotation(LoggerExecutionTime)")
    public Object loggerExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable{
        Arrays.stream((joinPoint.getArgs())).forEach(System.out::println);
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        ((List<TeacherDTO>) proceed).forEach(teacherDTO -> teacherDTO.setEmail("modified by logger"));
        System.out.println(joinPoint.getSignature() + "executed in : " + executionTime + " in ms");
        return proceed;
    }

    @Pointcut("execution(public * *..findByID(Integer))")
    public void findByID(){}

    @Around("findByID()")
    public Object loggerExecutionTime2 (ProceedingJoinPoint joinPoint) throws Throwable{

        Object proceed = joinPoint.proceed();
        return proceed;
    }
}
