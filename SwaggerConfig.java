import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi htmlViewApi() {
        return GroupedOpenApi.builder()
                .group("html-api")
                .pathsToMatch("/", "/posts/**") // BoardController 경로들 포함
                .build();
    }
}
