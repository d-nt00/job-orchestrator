package personal.job.orchestrator.repository.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "eventName",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = NotificationTriggeredJobDetailsDto.class, name = "notification-triggered-system")
})
public abstract class JobDetailsDto {
    private String eventName;
}
