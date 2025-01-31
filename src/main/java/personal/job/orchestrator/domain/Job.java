package personal.job.orchestrator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import personal.job.orchestrator.common.enums.JobStatus;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@Builder
@NoArgsConstructor
public class Job {

  private String jobId;

  private JobStatus jobStatus;

  private LocalDateTime scheduledTime;

  private JobDetails jobDetails;
}
