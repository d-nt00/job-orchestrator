package personal.job.orchestrator.mapper.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.convert.ReadingConverter;
import personal.job.orchestrator.repository.dto.JobDetailsDto;

@ReadingConverter
public class JobDetailsDeserializer extends BaseJsonTypeDeserializer<JobDetailsDto> {
  public JobDetailsDeserializer(ObjectMapper objectMapper) {
    super(objectMapper, JobDetailsDto.class);
  }
}
