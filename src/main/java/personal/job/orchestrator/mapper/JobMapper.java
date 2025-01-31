package personal.job.orchestrator.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import personal.job.orchestrator.domain.Job;
import personal.job.orchestrator.domain.JobDetails;
import personal.job.orchestrator.repository.dto.JobDetailsDto;
import personal.job.orchestrator.repository.dto.JobDto;

import java.util.UUID;

@Component
public class JobMapper implements AbstractMapper<Job, JobDto> {

  public static final JobMapper INSTANCE = new JobMapper();

  @Autowired
  private ObjectMapper objectMapper;

  @Override
  public Job toEntity(JobDto dto) {
    if (dto == null) {
      return new Job();
    }

    return Job.builder()
        .jobId(String.valueOf(dto.jobId()))
        .jobStatus(dto.jobStatus())
        .scheduledTime(dto.scheduledTime())
        .jobDetails(objectMapper.convertValue(dto.jobDetails(), JobDetails.class))
        .build();
  }

  @Override
  public JobDto toDto(Job job) {
    if (job == null) {
      return JobDto.builder().build();
    }

    return JobDto.builder()
        .jobId(UUID.fromString(job.getJobId()))
        .jobStatus(job.getJobStatus())
        .scheduledTime(job.getScheduledTime())
        .jobDetails(objectMapper.convertValue(job.getJobDetails(), JobDetailsDto.class))
        .build();
  }
}
