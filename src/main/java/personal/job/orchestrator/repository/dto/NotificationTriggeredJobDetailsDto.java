package personal.job.orchestrator.repository.dto;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationTriggeredJobDetailsDto extends JobDetailsDto {

  private String notificationSystemId;
}
