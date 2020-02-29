package com.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//SwaggerConfig是配置文件，加载配置文件用@Configeration
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        return  new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()).
                pathMapping("/").
                select().paths(PathSelectors.regex("/.*")).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("amei的接口文档").
                contact(new Contact("amei","","wanglmamei@gmail.com")).
                description("amei的swaggerui生成的接口文档").
                version("1.0.0.0").
                build();
    }

}
