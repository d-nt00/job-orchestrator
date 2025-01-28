package personal.job.orchestrator.repository.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class NotificationTriggeredJobDetailsDto extends JobDetailsDto {

    private String notificationSystemId;
}
