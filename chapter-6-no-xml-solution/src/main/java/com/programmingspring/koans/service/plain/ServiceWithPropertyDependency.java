package com.programmingspring.koans.service.plain;

import com.programmingspring.koans.repository.MyRepository;
import com.programmingspring.koans.service.MyService;

public class ServiceWithPropertyDependency implements MyService {

    private MyRepository repository;

    public MyRepository getRepository() {
        return repository;
    }

    public void setRepository(MyRepository repository) {
        this.repository = repository;
    }
}
