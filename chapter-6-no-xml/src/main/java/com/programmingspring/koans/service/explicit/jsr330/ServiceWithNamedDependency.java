package com.programmingspring.koans.service.explicit.jsr330;

import com.programmingspring.koans.repository.MyRepository;
import com.programmingspring.koans.service.MyService;

import javax.inject.Inject;

public class ServiceWithNamedDependency implements MyService {

    private MyRepository repository;

    public MyRepository getRepository() {
        return repository;
    }

    @Inject
    public void setRepository(MyRepository repository) {
        this.repository = repository;
    }
}
