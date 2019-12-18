
package aspects;

import java.sql.Timestamp;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class AuditAspect {

    private final static Logger log = Logger.getLogger("ServiceAudit");

    @Before("execution(* controllers..*(..))")
    public void testMapping(JoinPoint joinPoint) {

        MethodSignature metSig = (MethodSignature) joinPoint.getSignature();
        String methodName = joinPoint.getTarget().getClass().getName() + "." + metSig.getName();
        String parameters = "";
        for(int i = 0; i < joinPoint.getArgs().length;i++){
            parameters += joinPoint.getArgs().getClass().getName() + " , ";
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String logMsg ="< " + timestamp + " , " + methodName + " , " + parameters + " >";
        log.info(logMsg);

    }

}