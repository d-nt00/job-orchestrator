package personal.job.orchestrator.repository.dto;

import org.springframework.data.relational.core.mapping.Table;
import personal.job.orchestrator.common.enums.JobStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("scheduling")
public record JobDto(UUID jobId,
                     JobStatus jobStatus,
                     LocalDateTime scheduledTime,
                     JobDetailsDto jobDetails) {
}
