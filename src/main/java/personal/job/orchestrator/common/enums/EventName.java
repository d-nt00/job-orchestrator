package personal.job.orchestrator.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EventName {
  NOTIFICATION_TRIGGERED_SYSTEM("notification-triggered-system");

  private final String value;
}
