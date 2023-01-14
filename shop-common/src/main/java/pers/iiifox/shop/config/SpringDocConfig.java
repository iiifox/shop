package pers.iiifox.shop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 田章
 * @description OpenApi3.0 文档配置类 /swagger-ui/index.html
 * @date 2022/12/23
 */
@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI shopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("电商项目 API")
                        .version("1.0")
                        .description("Knife4j集成springdoc-openapi的接口文档")
                        .license(new License().name("Apache 2.0").url("https://iiifox.github.io/")));
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder().group("用户模块")
                .pathsToMatch("/api/address/v1/**", "/api/user/v1/**")
                .build();
    }

}
