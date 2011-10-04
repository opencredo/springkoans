package com.programmingspring.koans.service.explicit.spring;

import com.programmingspring.koans.repository.MyRepository;
import com.programmingspring.koans.service.MyService;

public class ServiceWithFieldDependency implements MyService {

    private MyRepository repository;

    public MyRepository getRepository() {
        return repository;
    }
}
