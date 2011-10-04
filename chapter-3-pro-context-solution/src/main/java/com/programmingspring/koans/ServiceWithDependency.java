package com.programmingspring.koans;

public class ServiceWithDependency implements Service {

    private Repository repository;

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
