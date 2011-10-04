package com.programmingspring.koans.config;

import com.programmingspring.koans.repository.MyRepository;
import com.programmingspring.koans.service.MyService;
import com.programmingspring.koans.service.plain.ServiceWithPropertyDependency;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    private RepositoryConfig repositoryConfig;

    private MyRepository repository;

    @Bean
    public MyService someService() {
        ServiceWithPropertyDependency service = new ServiceWithPropertyDependency();

        return service;
    }

    @Bean
    public MyService someOtherService() {
        ServiceWithPropertyDependency service = new ServiceWithPropertyDependency();

        return service;
    }

    //just for koans:

    public RepositoryConfig getRepositoryConfig() {
        return repositoryConfig;
    }

    public MyRepository getRepository() {
        return repository;
    }
}
