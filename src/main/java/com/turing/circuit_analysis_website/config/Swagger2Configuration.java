package com.turing.circuit_analysis_website.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * SWAGGER配置类
 * @author CHEN
 * 这是后台与前端对接的接口文档
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {

    @Bean
    public Docket guestApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("可匿名访问的查询接口文档")
                .apiInfo(guestApi())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.turing.circuit_analysis_website.controller.guest"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket AdminApiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("需登录访问的接口文档")
                .apiInfo(adminApi())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.turing.circuit_analysis_website.controller.admin"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo adminApi() {
        return new ApiInfoBuilder()
                .title("电路分析精品课程")
                .description("需登录访问的接口文档")
                .termsOfServiceUrl("http://118.178.125.139:9877")
                .version("1.0")
                .build();
    }

    private ApiInfo guestApi(){
        return new ApiInfoBuilder()
                .title("电路分析精品课程")
                .description("可匿名访问的查询接口文档")
                .termsOfServiceUrl("http://118.178.125.139:9877")
                .version("1.0")
                .build();
    }
}
