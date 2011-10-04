package com.programmingspring.koans;

public class ServiceWithDefaultDependency implements Service {

    private Repository repository = new DefaultRepository();

    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    private class DefaultRepository implements Repository {}
}
