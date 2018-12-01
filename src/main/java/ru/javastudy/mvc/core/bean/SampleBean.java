package ru.javastudy.mvc.core.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component(value = "sampleBean")
public class SampleBean {

    private String stringValue;
    private int number;

    public SampleBean() {
    }

    @PostConstruct
    public void init() {
        stringValue = "postConstructValue";
        number = 42;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}