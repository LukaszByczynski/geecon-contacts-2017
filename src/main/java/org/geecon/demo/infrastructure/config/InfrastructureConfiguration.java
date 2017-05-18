package org.geecon.demo.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
class InfrastructureConfiguration {

    @Autowired
    void configureObjectMapper(ObjectMapper objectMapper) {
        ObjectMapperFactory.configureObjectMapper(objectMapper);
    }

    @Bean
    MappingJackson2HttpMessageConverter jacksonMessageConverter(ObjectMapper objectMapper) {
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
}
