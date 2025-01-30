package personal.job.orchestrator.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import personal.job.orchestrator.exception.ServiceException;

import static personal.job.orchestrator.common.enums.CommonErrorMessages.JSON_PARSING_ERROR;

@RequiredArgsConstructor
public abstract class BaseJsonTypeSerializer<F> implements Converter<F, Json> {

  protected final ObjectMapper objectMapper;

  @Override
  public Json convert(F source) {
    try {
      String rawJsonString = objectMapper.writer().withDefaultPrettyPrinter()
          .writeValueAsString(source);
      return Json.of(rawJsonString);
    } catch (JsonProcessingException e) {
      throw new ServiceException(JSON_PARSING_ERROR, e);
    }
  }
}
