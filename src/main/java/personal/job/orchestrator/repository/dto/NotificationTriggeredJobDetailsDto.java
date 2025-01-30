package personal.job.orchestrator.repository.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class NotificationTriggeredJobDetailsDto extends JobDetailsDto {

  private final String notificationSystemId;
}
