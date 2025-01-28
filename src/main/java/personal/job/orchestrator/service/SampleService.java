package personal.job.orchestrator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal.job.orchestrator.repository.JobRepository;
import personal.job.orchestrator.repository.dto.JobDto;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final JobRepository jobRepository;

    public Mono<JobDto> getFirstJob() {
        return jobRepository.findJob(UUID.fromString("baba4406-2194-4dac-ac41-1ea2c3fa5676"));
    }
}
