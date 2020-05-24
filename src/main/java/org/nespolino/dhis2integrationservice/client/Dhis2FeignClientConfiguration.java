package org.nespolino.dhis2integrationservice.client;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients
@EnableConfigurationProperties(Dhis2AuthProperties.class)
class Dhis2FeignClientConfiguration {

    @Bean
    BasicAuthRequestInterceptor basicAuthRequestInterceptor(Dhis2AuthProperties dhis2AuthProperties) {
        return new BasicAuthRequestInterceptor(dhis2AuthProperties.getUsername(), dhis2AuthProperties.getPassword());
    }

}
