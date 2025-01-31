package personal.job.orchestrator.repository.dto;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import personal.job.orchestrator.common.enums.JobStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("scheduling")
@Builder
public record JobDto(@Id UUID jobId,
                     JobStatus jobStatus,
                     LocalDateTime scheduledTime,
                     JobDetailsDto jobDetails) {
}
