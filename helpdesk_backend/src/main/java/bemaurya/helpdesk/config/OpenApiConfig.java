package bemaurya.helpdesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
    // this is the configuration for the OpenAPI documentation
    @Bean(name = "usersMicroserviceOpenAPI")
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("BeMaurya Help Desk")
                        .description("BeMaurya Help Desk API Documentation")
                        .version("2023.0915.0"));
    }
}