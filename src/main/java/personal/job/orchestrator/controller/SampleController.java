package personal.job.orchestrator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import personal.job.orchestrator.repository.dto.JobDto;
import personal.job.orchestrator.service.SampleService;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/fetch")
    public Mono<ResponseEntity<JobDto>> getJob() {
        return sampleService.getFirstJob()
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
