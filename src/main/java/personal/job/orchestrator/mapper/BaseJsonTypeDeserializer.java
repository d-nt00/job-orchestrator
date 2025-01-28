package personal.job.orchestrator.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import personal.job.orchestrator.exception.ServiceException;

import static personal.job.orchestrator.common.enums.CommonErrorMessages.JSON_PARSING_ERROR;

@RequiredArgsConstructor
public abstract class BaseJsonTypeDeserializer<T> implements Converter<Json, T> {

    protected final ObjectMapper objectMapper;
    protected Class<T> targetClass;

    @Override
    public T convert(Json source) {
        try {
            String jsonString = source.asString();
            return objectMapper.readValue(jsonString, targetClass);
        } catch (JsonProcessingException e) {
            throw new ServiceException(JSON_PARSING_ERROR, e);
        }
    }
}
