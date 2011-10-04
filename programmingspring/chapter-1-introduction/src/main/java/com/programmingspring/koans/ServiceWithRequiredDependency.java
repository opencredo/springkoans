package com.programmingspring.koans;

public class ServiceWithRequiredDependency implements Service {

    private Repository repository;

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }
}
