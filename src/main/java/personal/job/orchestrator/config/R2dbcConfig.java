package personal.job.orchestrator.config;

import io.r2dbc.spi.ConnectionFactory;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;

import java.util.List;

@Configuration
@EnableConfigurationProperties
@RequiredArgsConstructor
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    @Qualifier("r2dbcAdapter")
    private final List<Object> adapters;

    @Override
    public ConnectionFactory connectionFactory() {
        return null;
    }

    @Override
    protected @NonNull List<Object> getCustomConverters() {
        return adapters;
    }
}
