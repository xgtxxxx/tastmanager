package task.architecture.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component 
public class LogAspect {
	private final Logger logger = Logger.getLogger(LogAspect.class);

	@Around("execution(* task.service..*Impl.*(..))")
	public Object logPerformance(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		long endTime = System.currentTimeMillis();
		logger.debug("Executing method:"+pjp.getSignature()+ ", "+(endTime-startTime)/1000+"s used.");
		return retVal;
	}
}
