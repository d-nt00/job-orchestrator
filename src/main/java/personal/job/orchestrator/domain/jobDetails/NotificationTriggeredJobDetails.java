package personal.job.orchestrator.domain.jobDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import personal.job.orchestrator.domain.JobDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationTriggeredJobDetails extends JobDetails {
  private String notificationSystemId;
}
