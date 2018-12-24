package com.yjh.study.time;

public class MyTime {

    private String signature = "my time";
    private Long value;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "MyTime{" +
                "signature='" + signature + '\'' +
                ", value=" + value +
                '}';
    }
}
