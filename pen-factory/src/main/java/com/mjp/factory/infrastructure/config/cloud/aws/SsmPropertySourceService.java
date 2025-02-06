package com.mjp.factory.infrastructure.config.cloud.aws;

import org.springframework.core.env.PropertySource;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;
import software.amazon.awssdk.services.ssm.model.SsmException;

import java.util.HashMap;
import java.util.Map;


public class SsmPropertySourceService extends PropertySource<Object> {

    private static final String PATH_SSM_WORKER = "/factory/";

    private final SsmClient ssmClient;

    public SsmPropertySourceService(String name, SsmClient ssmClient) {
        super(name);
        this.ssmClient = ssmClient;
    }


    @Override
    public Object getProperty(String name) {
        try {
            GetParameterRequest request = GetParameterRequest.builder()
                    .name(PATH_SSM_WORKER+name)
                    .build();

            GetParameterResponse response = ssmClient.getParameter(request);
            return response.parameter().value();
        } catch (SsmException e) {
            return null;
        }
    }

    public Map<String, String> loadPropertiesFromSsm(String... parametersNames) {
        Map<String, String> properties = new HashMap<>();
        for(String parameterName : parametersNames) {
            String value = (String) getProperty(parameterName);
            if(value != null) {
                properties.put(parameterName, value);
            }
        }
        return properties;
    }
}
