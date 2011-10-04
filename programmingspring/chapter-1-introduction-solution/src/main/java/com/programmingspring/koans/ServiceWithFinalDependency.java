package com.programmingspring.koans;

public class ServiceWithFinalDependency implements Service {

    private final Repository repository;

    public ServiceWithFinalDependency(Repository repository) {
        this.repository = repository;
    }

    public Repository getRepository() {
        return repository;
    }
}
