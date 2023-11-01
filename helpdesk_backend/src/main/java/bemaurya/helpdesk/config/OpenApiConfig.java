package bemaurya.helpdesk.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    // this is the configuration for the OpenAPI documentation
    @Bean(name = "usersMicroserviceOpenAPI")
    public OpenAPI usersMicroserviceOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("bemaurya Help Desk")
                        .description("bemaurya Help Desk API Documentation")
                        .version("2023.0915.0"));
    }
}