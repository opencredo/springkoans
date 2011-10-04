package com.programmingspring.koans.config;

import com.programmingspring.koans.repository.MyRepository;
import com.programmingspring.koans.service.MyService;
import com.programmingspring.koans.service.plain.ServiceWithPropertyDependency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServicesConfig {

    @Autowired
    private RepositoryConfig repositoryConfig;

    @Autowired
    private MyRepository repository;

    @Bean
    public MyService someService() {
        ServiceWithPropertyDependency service = new ServiceWithPropertyDependency();
        service.setRepository(repository);
        return service;
    }

    @Bean
    public MyService someOtherService() {
        ServiceWithPropertyDependency service = new ServiceWithPropertyDependency();
        service.setRepository(repositoryConfig.firstRepository());
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
