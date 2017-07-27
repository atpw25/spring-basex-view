package brc.servlet.view.basex;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

public class BaseXViewResolver extends UrlBasedViewResolver {

    public BaseXViewResolver() {
        setContentType(MediaType.APPLICATION_XML.toString());
        setViewClass(requiredViewClass());
        setPrefix("file:views/");
        setSuffix(".xql");
    }

    @Override
    protected Class<?> requiredViewClass() {
        return BaseXView.class;
    }

}
