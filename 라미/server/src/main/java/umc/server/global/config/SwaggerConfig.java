package umc.server.global.config;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swagger() {
        Info info = new Info().
                title("Project").
                description("Project Swagger").
                version("0.0.1");

        //JWT 토큰 헤더 방식
        String securityScheme = "JWT TOKEN";

        // 스웨거 API들이 JWT TOKEN 인증 방식을 필요로 한다고 명시(Authorize 버튼)
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(securityScheme);

        Components components = new Components()
                .addSecuritySchemes(
                        securityScheme,
                        new SecurityScheme()
                            .name(securityScheme)
                            .type(SecurityScheme.Type.HTTP)
                            .scheme("Bearer")
                            .bearerFormat("JWT")
                );

        return new OpenAPI()
                .info(info)
                .addServersItem(new Server().url("/")) // 스웨거가 API 호출 시 기준 URL을 /로 설정
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
