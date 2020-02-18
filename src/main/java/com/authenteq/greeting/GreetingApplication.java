package com.authenteq.greeting;

import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.SpringWebApplicationFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import static com.google.common.base.Preconditions.checkArgument;
import static java.beans.Introspector.decapitalize;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.wicket.protocol.http.WicketFilter.APP_FACT_PARAM;
import static org.apache.wicket.protocol.http.WicketFilter.FILTER_MAPPING_PARAM;
import static org.springframework.util.ClassUtils.getShortName;

@SpringBootApplication
@EnableConfigurationProperties(GreetingProperties.class)
public class GreetingApplication implements ServletContextInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GreetingApplication.class, args);
    }

    private final String wicketConfiguration;

    public GreetingApplication(@Value("${wicket.configuration:DEVELOPMENT}") final String wicketConfiguration) {
        checkArgument(isNotBlank(wicketConfiguration), "Argument 'wicketConfiguration' cannot be blank");

        this.wicketConfiguration = wicketConfiguration;
    }

    @Override
    public void onStartup(final ServletContext servletContext) throws ServletException {
        FilterRegistration filter = servletContext.addFilter("wicket-filter", WicketFilter.class);
        filter.setInitParameter(APP_FACT_PARAM, SpringWebApplicationFactory.class.getName());
        filter.setInitParameter("applicationBean", decapitalize(getShortName(WicketApplication.class)));
        filter.setInitParameter(FILTER_MAPPING_PARAM, "/*");
        filter.setInitParameter("wicket.configuration", wicketConfiguration);
        filter.addMappingForUrlPatterns(null, false, "/*");
    }

}
