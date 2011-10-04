package com.programmingspring.koans.service.explicit.spring;

import com.programmingspring.koans.repository.MyRepository;
import com.programmingspring.koans.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;

public class ServiceWithTwoNamedConstructorDependencies implements MyService {

    private final MyRepository firstRepository;
    private final MyRepository secondRepository;

    @Autowired
    public ServiceWithTwoNamedConstructorDependencies(MyRepository repoOne, MyRepository repoTwo) {
        this.firstRepository = repoOne;
        this.secondRepository = repoTwo;
    }

    public MyRepository getRepository() {
        return firstRepository;
    }

    public MyRepository getSecondRepository() {
        return secondRepository;
    }
}
