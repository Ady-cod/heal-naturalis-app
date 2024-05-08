package com.codecool.healnaturalisapp.config;

import com.codecool.healnaturalisapp.Constants;
import com.codecool.healnaturalisapp.model.Therapy;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config,
                                                     CorsRegistry cors) {
        HttpMethod[] theUnsupportedActions = {
                HttpMethod.POST,
                HttpMethod.PATCH,
                HttpMethod.DELETE,
                HttpMethod.PUT};

        config.exposeIdsFor(Therapy.class);


        disableHttpMethods(Therapy.class, config, theUnsupportedActions);

        // Set page size to Integer.MAX_VALUE, so that we can get all data at once by default
        config.setDefaultPageSize(Integer.MAX_VALUE);
        config.setMaxPageSize(Integer.MAX_VALUE);


        /* Configure CORS Mapping */
        cors.addMapping(config.getBasePath() + "/**")
                .allowedOrigins(Constants.ALLOWED_ORIGINS);
    }

    private void disableHttpMethods(Class theClass,
                                    RepositoryRestConfiguration config,
                                    HttpMethod[] theUnsupportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metadata, httpMethods) ->
                        httpMethods.disable(theUnsupportedActions));
    }
}
