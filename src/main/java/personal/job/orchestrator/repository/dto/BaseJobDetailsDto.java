package personal.job.orchestrator.repository.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseJobDetailsDto {
    private String eventName;
}
