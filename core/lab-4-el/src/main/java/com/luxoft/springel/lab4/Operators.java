package com.luxoft.springel.lab4;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("operatorsBean")
public class Operators {

    @Value("#{moscowBean.population > parisBean.population}")
    private boolean lessThanTest;

    @Value("#{moscowBean.population + parisBean.population}")
    private long addTest;

    public boolean isLessThanTest() {
        return lessThanTest;
    }

    public void setLessThanTest(boolean lessThanTest) {
        this.lessThanTest = lessThanTest;
    }

    public long getAddTest() {
        return addTest;
    }

    public void setAddTest(long addTest) {
        this.addTest = addTest;
    }
}
