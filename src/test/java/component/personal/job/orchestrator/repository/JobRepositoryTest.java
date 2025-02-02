package component.personal.job.orchestrator.repository;

import component.personal.job.orchestrator.ComponentTest;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.r2dbc.connection.init.ScriptUtils;
import personal.job.orchestrator.repository.JobRepository;
import personal.job.orchestrator.repository.dto.JobDto;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static fixture.personal.job.orchestrator.JobFixtures.getDummyJobDto;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ComponentTest
public class JobRepositoryTest {

  private static final Logger log = LoggerFactory.getLogger(JobRepositoryTest.class);
  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private ConnectionFactory connectionFactory;

  @BeforeEach
  public void setUp(@Value("classpath:/test-data/insert-test-data.sql") Resource resource) {
    executeScriptBlocking(resource);
  }

  private void executeScriptBlocking(Resource sqlScript) {
    Mono.from(connectionFactory.create())
        .flatMap(connection -> ScriptUtils.executeSqlScript(connection, sqlScript))
        .block();
  }

  @Test
  public void givenJobDto_whenSave_thenSave() {
    JobDto dummyJob = getDummyJobDto();
    Mono<JobDto> savedJobMono = jobRepository.save(dummyJob);

    StepVerifier.create(savedJobMono)
        .assertNext(savedJob -> {
          assertNotNull(savedJob);
          assertNotNull(savedJob.jobId());
        })
        .verifyComplete();

    Mono<JobDto> retrievedJobMono = savedJobMono
        .map(JobDto::jobId)
        .flatMap(jobRepository::findById);

    StepVerifier.create(retrievedJobMono)
        .assertNext(foundJob -> {
          try {
            assertNotNull(foundJob);
            assertNotNull(foundJob.jobId());
            assertEquals(dummyJob.jobStatus(), foundJob.jobStatus());
            assertTrue(dummyJob.scheduledTime().isEqual(foundJob.scheduledTime()));
            assertThat(dummyJob.jobDetails()).usingRecursiveComparison().isEqualTo(foundJob.jobDetails());
          } catch (Exception e) {
            log.error("e: ", e);
            log.info("foundJob: {}, dummyJob: {}", foundJob.scheduledTime(), dummyJob.scheduledTime());
          }
        })
        .verifyComplete();
  }
}
