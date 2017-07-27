package brc.servlet.view.basex;

import org.basex.core.Context;
import org.basex.io.serial.SerialMethod;
import org.basex.io.serial.Serializer;
import org.basex.io.serial.SerializerOptions;
import org.basex.query.QueryProcessor;
import org.basex.query.iter.Iter;
import org.basex.query.value.item.Item;
import org.springframework.context.ApplicationContextException;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.view.AbstractUrlBasedView;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class BaseXView extends AbstractUrlBasedView {
    private Context context;

    public BaseXView() {
        this.context = new Context();
    }

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String query = getXquerySource();


        try(QueryProcessor proc = new QueryProcessor(query, context)) {

            // Bind model map received from Spring controller as XQuery external variables
            for (String k: model.keySet()) {
                proc.bind(k, model.get(k));
            }

            // Execute the query and get an iterator to the results
            Iter iter = proc.iter();

            // Configure the content-type based on the output:method in XQuery. Default to application/xml.
            SerializerOptions sopts = proc.qc.serParams();
            MediaType mediaType = determineMediaType(sopts);
            response.setContentType(mediaType.toString());


            try(Serializer ser = proc.getSerializer(response.getOutputStream())) {
                // Iterate through all items and serialize contents
                for(Item item; (item = iter.next()) != null;) {
                    ser.serialize(item);
                }
            }
        }
    }

    protected String getXquerySource() {
        String url = getUrl();
        Assert.state(url != null, "'url' not set");

        if (logger.isDebugEnabled()) {
            logger.debug("Loading XQuery file from '" + url + "'");
        }
        try {
            Resource resource = getApplicationContext().getResource(url);
            return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        }
        catch (IOException ex) {
            throw new ApplicationContextException("Can't load XQuery file from '" + url + "'", ex);
        }
    }

    private MediaType determineMediaType(SerializerOptions sopts) {
        final String type = sopts.get(SerializerOptions.MEDIA_TYPE);
        if(!type.isEmpty()) {
            return new MediaType(type);
        }

        // determine content type dependent on output method
        final SerialMethod sm = sopts.get(SerializerOptions.METHOD);
        switch(sm) {
            case BASEX:
            case ADAPTIVE:
            case XML:
                return MediaType.APPLICATION_XML;
            case XHTML:
            case HTML:
                return MediaType.TEXT_HTML;
            case JSON:
                return MediaType.APPLICATION_JSON;
            default:
                return MediaType.TEXT_PLAIN;
        }
    }

    @PreDestroy
    public void closeContext() {
        if (this.context != null) {
            this.context.close();
            this.context = null;
        }
    }
}
