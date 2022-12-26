package pers.iiifox.shop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 田章
 * @description OpenApi3.0 文档配置类
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

    // /**
    //  * 根据@Tag 上的排序，写入x-order
    //  *
    //  * @return the global open api customizer
    //  */
    // @Bean
    // public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
    //     return openApi -> {
    //         if (openApi.getTags() != null) {
    //             openApi.getTags().forEach(tag -> {
    //                 Map<String, Object> map = new HashMap<>();
    //                 tag.setExtensions(map);
    //             });
    //         }
    //         if (openApi.getPaths() != null) {
    //             openApi.addExtension("x-test123", "333");
    //         }
    //     };
    // }

    @Bean
    public GroupedOpenApi userApi() {
        // String[] paths = {"/**"};
        // String[] packagedToMatch = {"pers.iiifox.shop.user"};
        return GroupedOpenApi.builder().group("用户模块")
                .pathsToMatch("/api/address/v1/**", "/api/user/v1/**")
                // .addOperationCustomizer((operation, handlerMethod) ->
                //         operation.addParametersItem(new HeaderParameter()
                //                 .name("groupCode")
                //                 .example("测试")
                //                 .description("集团code")
                //                 .schema(new StringSchema()._default("BR").name("groupCode").description("集团code"))
                //         ))
                .build();
    }

}
