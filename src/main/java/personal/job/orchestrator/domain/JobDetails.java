package personal.job.orchestrator.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class JobDetails {
  private String eventName;
}
