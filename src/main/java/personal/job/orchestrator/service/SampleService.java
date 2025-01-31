package personal.job.orchestrator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal.job.orchestrator.common.enums.JobStatus;
import personal.job.orchestrator.repository.JobRepository;
import personal.job.orchestrator.repository.dto.JobDto;
import personal.job.orchestrator.repository.dto.NotificationTriggeredJobDetailsDto;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SampleService {

  private final JobRepository jobRepository;

  public Mono<JobDto> getFirstJob() {
    return jobRepository.findById(UUID.fromString("baba4406-2194-4dac-ac41-1ea2c3fa5676"));
  }

  public Mono<JobDto> insertJob() {
    JobDto jobDto = JobDto.builder()
        .jobStatus(JobStatus.CANCELED)
        .scheduledTime(LocalDateTime.now())
        .jobDetails(NotificationTriggeredJobDetailsDto.builder().build())
        .build();

    return jobRepository.save(jobDto);
  }
}
