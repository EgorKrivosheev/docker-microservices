package by.grodno.krivosheev.transformation.configs;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.validation.constraints.NotNull;

@Configuration
@EnableElasticsearchRepositories(basePackages = "by.grodno.krivosheev.transformation.elastic.repositories")
@ComponentScan(basePackages = "by.grodno.krivosheev.transformation.elastic")
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {
    @Value("${elasticsearch-server:localhost:9200}")
    private String elasticsearchServer;

    // TODO: method is deprecated
    @Bean
    @NotNull
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final var clientConfiguration = ClientConfiguration.builder()
                .connectedTo(elasticsearchServer)
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
