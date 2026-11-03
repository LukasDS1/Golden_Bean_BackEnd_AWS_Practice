package com.github.pay_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration 
public class SqsConfig {

    @Bean 
    SqsClient sqsClient(){
        return SqsClient.create();
    }

}
