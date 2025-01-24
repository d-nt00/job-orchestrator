package personal.job.orchestrator.repository.dto;

import personal.job.orchestrator.common.enums.JobStatus;

import java.time.OffsetDateTime;

public record JobDto(String jobId,
                     JobStatus status,
                     OffsetDateTime scheduledTime,
                     BaseJobDetailsDto jobDetailsDto) {
}
