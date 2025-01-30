package personal.job.orchestrator.common.validator;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import personal.job.orchestrator.repository.ColumnIdentity;

@Aspect
public class ObjectTypeValidatorAspect {

  @Before("@annotation(personal.job.orchestrator.common.annotations.ValidateObject)")
  public void isOfType(JoinPoint joinPoint) {
    ColumnIdentity column = (ColumnIdentity) joinPoint.getArgs()[0];
    Object newValue = joinPoint.getArgs()[2];

    Class<?> columnType = column.getType();

    if (newValue != null && !columnType.isAssignableFrom(newValue.getClass())) {
      throw new IllegalArgumentException("Invalid type for column '" + column.getName() +
          "'. Expected: " + columnType.getSimpleName() +
          ", but got: " + newValue.getClass().getSimpleName());
    }
  }
}
