package personal.job.orchestrator.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import personal.job.orchestrator.config.properties.ConversionProperties;
import personal.job.orchestrator.repository.dto.JobDetailsDto;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConversionConfig {

  @Bean(name = "r2dbcAdapter")
  public List<Object> r2dbcTypeAdapter(ConversionProperties conversionProperties, ObjectMapper objectMapper) {
    List<Class<? extends Converter<?, ?>>> serializers = conversionProperties.getSerializers();
    List<Class<? extends Converter<?, ?>>> deserializers = conversionProperties.getDeserializers();

    List<Object> adapters = new ArrayList<>();
    adapters.addAll(toInstancesFromClasses(serializers, objectMapper));
    adapters.addAll(toInstancesFromClasses(deserializers, objectMapper));


    return adapters;
  }

  @Bean
  public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
    ObjectMapper objectMapper = builder.build();
    objectMapper.addMixIn(JobDetailsDto.class, JobDetailsDtoMixin.class);

    return objectMapper;
  }

  @SneakyThrows
  private Object instantiate(Class<?> targetClass, Object... args) {
    return targetClass.getDeclaredConstructor(args[0].getClass()).newInstance(args);
  }

  private List<Object> toInstancesFromClasses(List<Class<? extends Converter<?, ?>>> classes, Object... args) {
    return classes.stream()
        .map(clazz -> instantiate(clazz, args))
        .toList();
  }
}
