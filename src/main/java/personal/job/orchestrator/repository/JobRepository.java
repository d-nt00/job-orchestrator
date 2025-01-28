package personal.job.orchestrator.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import personal.job.orchestrator.repository.dto.JobDto;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.UUID;

import static org.springframework.data.relational.core.query.Update.update;

@Repository
@RequiredArgsConstructor
@Transactional
public class JobRepository {

    private final R2dbcEntityTemplate r2dbcEntityTemplate;

    @Getter
    @AllArgsConstructor
    private enum JobColumn {
        JOB_ID("job_id"),
        SCHEDULED_TIME("scheduled_time"),
        JOB_DETAILS("job_details"),
        JOB_STATUS("job_status");

        private final String name;
    }

    private Query onColumn(JobColumn column, UUID expected) {
        return Query.query(Criteria.where(column.getName()).is(expected));
    }

    public Mono<JobDto> findJob(UUID jobId) {
        return r2dbcEntityTemplate.select(JobDto.class)
                .matching(onColumn(JobColumn.JOB_ID, jobId))
                .one();
    }

    public Mono<Boolean> exists(UUID jobId) {
        return r2dbcEntityTemplate.select(JobDto.class)
                .matching(onColumn(JobColumn.JOB_ID, jobId))
                .exists();
    }

    public Mono<Long> updateJobScheduledTime(UUID jobId, OffsetDateTime newScheduledTime) {
        return r2dbcEntityTemplate.update(JobDto.class)
                .matching(onColumn(JobColumn.JOB_ID, jobId))
                .apply(update(JobColumn.SCHEDULED_TIME.getName(), newScheduledTime));
    }
}
