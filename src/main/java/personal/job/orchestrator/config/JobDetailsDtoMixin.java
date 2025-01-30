package personal.job.orchestrator.config;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import personal.job.orchestrator.repository.dto.NotificationTriggeredJobDetailsDto;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "eventName",
    visible = true)
@JsonSubTypes({
    @JsonSubTypes.Type(value = NotificationTriggeredJobDetailsDto.class, name = "notification-triggered-system")
})
public abstract class JobDetailsDtoMixin {
}
