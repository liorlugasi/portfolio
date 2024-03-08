package com.customer.manager.services.configurations;

import com.customer.manager.services.implementations.ManagerService;
import com.customer.manager.services.interfaces.IManagerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public IManagerService getManagerService(){
        return new ManagerService();
    }
}
