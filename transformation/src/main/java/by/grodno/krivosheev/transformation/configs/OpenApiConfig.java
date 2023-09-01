package by.grodno.krivosheev.transformation.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Value("${info.application.name:Transformation API}")
    private String title;
    @Value("${info.application.description:A transformation service that converts xml files into a single format packed in zip}")
    private String description;
    @Value("${info.application.description:1.0.0}")
    private String version;

    /**
     * Set custom Open API where {@code version} and {@code description} read to application.properties.
     *
     * @return new custom OpenAPI where version and description indicated in application.properties.
     */
    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI().info(
                new Info()
                        .title(title)
                        .description(description)
                        .version(version)
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://springdoc.org")
                        )
        );
    }
}
