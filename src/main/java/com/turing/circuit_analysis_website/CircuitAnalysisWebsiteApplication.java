package com.turing.circuit_analysis_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableCaching
@EnableSwagger2
@SpringBootApplication
public class CircuitAnalysisWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(CircuitAnalysisWebsiteApplication.class, args);
    }

//    /**
//     * 支持前端路由的 history 模式
//     * @return
//     */
//    @Bean
//    public WebServerFactoryCustomizer webServerFactoryCustomizer() {
//
//        return new WebServerFactoryCustomizer<ConfigurableServletWebServerFactory>() {
//            @Override
//            public void customize(ConfigurableServletWebServerFactory factory) {
//
//                ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/index.html");
//                factory.addErrorPages(error404Page);
//            }
//        };
//    }
}
