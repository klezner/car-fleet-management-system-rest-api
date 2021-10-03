package pl.kl.companycarfleetmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Import(BeanValidatorPluginsConfiguration.class)
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("pl.kl.companycarfleetmanagementsystem"))
                .paths(PathSelectors.regex("/.*"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Company Car Fleet Management System")
                .description("Company Car Fleet Management System REST API for Web Application")
                .contact(new Contact("Krzysztof Lezner", "http://https://github.com/klezner", "klezner@gmail.com"))
                .license("License")
                .licenseUrl("License Url")
                .version("1.0.0")
                .build();
    }
}
