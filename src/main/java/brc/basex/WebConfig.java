package brc.basex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import brc.servlet.view.basex.BaseXViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    @Value("${xquery.dir:file:views/}")
    private String xqueryDir;

    @Value("${xquery.ext:.xql}")
    private String xqueryExt;

    @Bean
    public BaseXViewResolver baseXViewResolver() {
        BaseXViewResolver resolver = new BaseXViewResolver();
        resolver.setPrefix(xqueryDir);
        resolver.setSuffix(xqueryExt);
        return resolver;
    }
}