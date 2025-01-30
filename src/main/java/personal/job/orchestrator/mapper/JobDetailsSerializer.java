package personal.job.orchestrator.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.convert.WritingConverter;
import personal.job.orchestrator.repository.dto.JobDetailsDto;

@WritingConverter
public class JobDetailsSerializer extends BaseJsonTypeSerializer<JobDetailsDto> {
  public JobDetailsSerializer(ObjectMapper objectMapper) {
    super(objectMapper);
  }
}
