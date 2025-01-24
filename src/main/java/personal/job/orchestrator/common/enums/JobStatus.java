package personal.job.orchestrator.common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum JobStatus {
    NEW("new"),
    PROCESSING("processing"),
    COMPLETED("completed"),
    CANCELED("canceled");

    private final String value;
}
