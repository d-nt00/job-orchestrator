package personal.job.orchestrator.repository;

import lombok.AllArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import personal.job.orchestrator.common.enums.JobStatus;
import personal.job.orchestrator.repository.dto.JobDetailsDto;
import personal.job.orchestrator.repository.dto.JobDto;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
@Transactional
public class JobRepository extends BaseReactiveRepository<UUID, JobDto, JobRepository.JobColumns> {

  public JobRepository(R2dbcEntityTemplate r2dbcEntityTemplate) {
    super(r2dbcEntityTemplate, JobDto.class, JobColumns.JOB_ID);
  }

  @AllArgsConstructor
  public enum JobColumns implements ColumnIdentity {
    JOB_ID("job_id", String.class),
    STATUS("job_status", JobStatus.class),
    SCHEDULED_TIME("scheduled_time", LocalDateTime.class),
    JOB_DETAILS("job_details", JobDetailsDto.class);

    private final String name;

    private final Class<?> type;

    @Override
    public String getName() {
      return name;
    }

    @Override
    public Class<?> getType() {
      return type;
    }
  }

}
