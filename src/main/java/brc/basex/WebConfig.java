package brc.basex;

import brc.servlet.view.basex.BaseXViewResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
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