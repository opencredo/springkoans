package com.programmingspring.koans;

public class SingletonBean {

    private PrototypeBean prototypeBean;

    public PrototypeBean getPrototypeBean() {
        return prototypeBean;
    }

    public void setPrototypeBean(PrototypeBean prototypeBean) {
        this.prototypeBean = prototypeBean;
    }
}
