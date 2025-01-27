package personal.job.orchestrator.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "spring.conversion")
@Getter
@Setter
public class ConversionProperties {
    private List<Class<? extends Converter<?, ?>>> serializers;
    private List<Class<? extends Converter<?, ?>>> deserializers;
}
