package com.customer.facingapi.services.configurations;

import com.customer.facingapi.services.implementations.FacingApiService;
import com.customer.facingapi.services.interfaces.IFacingApiService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public IFacingApiService getFacingApiService(){
        return new FacingApiService();
    }


}
