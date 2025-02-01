package personal.job.orchestrator.common.validator;

import org.apache.commons.lang3.ClassUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import personal.job.orchestrator.common.annotations.IsAssignableType;

import java.lang.reflect.Parameter;
import java.util.stream.IntStream;

@Aspect
public class ObjectTypeValidatorAspect {

  private IllegalArgumentException throwArgNotFound(String argName) {
    return new IllegalArgumentException("Argument %s not found".formatted(argName));
  }

  @Before("@annotation(isAssignable)")
  public void isAssignable(JoinPoint joinPoint, IsAssignableType isAssignable) {
    MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

    String wantedArg = isAssignable.arg();

    int wantedTypeIndex = getWantedArgumentIndex(methodSignature, wantedArg);
    int annotatedIndex = getAnnotatedArgumentIndex(methodSignature, wantedArg);

    Class<?> wantedType = methodSignature.getParameterTypes()[wantedTypeIndex];
    Object target = joinPoint.getArgs()[annotatedIndex];

    if (target != null && !ClassUtils.isAssignable(target.getClass(), wantedType)) {
      throw new IllegalArgumentException(
          "Expected: " + wantedType.getSimpleName() +
          ", but got: " + target.getClass().getSimpleName());
    }
  }

  private int getAnnotatedArgumentIndex(MethodSignature methodSignature, String wantedArgName) {
    Parameter[] parameters = methodSignature.getMethod().getParameters();

    return IntStream.range(0, parameters.length)
        .filter(i -> parameters[i].isAnnotationPresent(IsAssignableType.class)
            && parameters[i].getAnnotation(IsAssignableType.class).arg().equals(wantedArgName))
        .findFirst()
        .orElseThrow(() -> throwArgNotFound(wantedArgName));
  }

  private int getWantedArgumentIndex(MethodSignature methodSignature, String wantedArgName) {
    String[] argNames = methodSignature.getParameterNames();

    return IntStream.range(0, argNames.length)
        .filter(i -> argNames[i].equals(wantedArgName))
        .findFirst()
        .orElseThrow(() -> throwArgNotFound(wantedArgName));
  }
}
