package by.grodno.krivosheev.transformation.configs;

import by.grodno.krivosheev.transformation.services.TransformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.io.File;
import java.io.IOException;

@EnableKafka
@Configuration
@AutoConfigureBefore(KafkaAutoConfiguration.class)
@RequiredArgsConstructor
public class KafkaConfig {
    private final TransformationService transformationService;

    @Value("${spring.datasource.hikari.maximum-pool-size:20}")
    private int poolConnection;

    @Bean(name = "listenerTaskExecutor")
    public ThreadPoolTaskExecutor listenerTaskExecutor() {
        var poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setThreadNamePrefix("Listener-");
        poolTaskExecutor.setCorePoolSize(poolConnection / 2);
        poolTaskExecutor.setMaxPoolSize(poolConnection);
        return poolTaskExecutor;
    }

    @KafkaListener(topics = "${topic-name:transformation}", groupId = "${group-id:0}")
    public void listener(String msg) throws IOException {
        transformationService.transformation(new File(msg));
    }
}
