package com.ariginting.employeeservice.configuration;

import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Configuration
public class AdaptorConfiguration {

    @Value("${integration.payroll.connect-timeout}")
    private Integer payrollConnectionTimeout;

    @Value("${integration.payroll.read-timeout}")
    private Integer payrollReadTimeout;

    private static final List<Header> HEADER_LIST = Collections.singletonList(new BasicHeader("Connection", "close"));


    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .requestFactory(() -> new HttpComponentsClientHttpRequestFactory(httpClientBuilder()
                        .setConnectionReuseStrategy(NoConnectionReuseStrategy.INSTANCE)
                        .setDefaultRequestConfig(RequestConfig
                                .custom()
                                .setConnectTimeout(payrollConnectionTimeout*1000)
                                .setConnectionRequestTimeout(payrollConnectionTimeout*1000)
                                .setSocketTimeout(payrollReadTimeout*1000)
                                .build())
                        .build()))
                .build();
    }

    private HttpClientBuilder httpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
        httpClientBuilder.disableAutomaticRetries();
        httpClientBuilder.setDefaultHeaders(HEADER_LIST);
        httpClientBuilder.setConnectionReuseStrategy(NoConnectionReuseStrategy.INSTANCE);
        return httpClientBuilder;
    }

}
