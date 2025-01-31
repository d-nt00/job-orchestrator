package fixture.personal.job.orchestrator;

import personal.job.orchestrator.common.enums.EventName;
import personal.job.orchestrator.common.enums.JobStatus;
import personal.job.orchestrator.repository.dto.JobDto;
import personal.job.orchestrator.repository.dto.NotificationTriggeredJobDetailsDto;

import java.time.LocalDateTime;

public class JobFixtures {
  public static JobDto getDummyJobDto() {
    return JobDto.builder()
        .scheduledTime(LocalDateTime.now())
        .jobStatus(JobStatus.COMPLETED)
        .jobDetails(NotificationTriggeredJobDetailsDto.builder().eventName(EventName.NOTIFICATION_TRIGGERED_SYSTEM.getValue()).build())
        .build();
  }
}
