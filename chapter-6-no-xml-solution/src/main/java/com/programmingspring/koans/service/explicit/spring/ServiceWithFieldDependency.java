package com.programmingspring.koans.service.explicit.spring;

import com.programmingspring.koans.repository.MyRepository;
import com.programmingspring.koans.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceWithFieldDependency implements MyService {

    @Autowired
    private MyRepository repository;

    public MyRepository getRepository() {
        return repository;
    }
}
