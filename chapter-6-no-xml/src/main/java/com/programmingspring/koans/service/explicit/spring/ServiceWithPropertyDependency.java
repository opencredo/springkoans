package com.programmingspring.koans.service.explicit.spring;

import com.programmingspring.koans.repository.MyRepository;
import com.programmingspring.koans.service.MyService;

public class ServiceWithPropertyDependency implements MyService {

    private MyRepository repository;
    private boolean setterCalled;

    public MyRepository getRepository() {
        return repository;
    }

    public void setRepository(MyRepository repository) {
        this.repository = repository;
        setterCalled = true;
    }

    public boolean wasSetterCalled() {
        return setterCalled;
    }
}
