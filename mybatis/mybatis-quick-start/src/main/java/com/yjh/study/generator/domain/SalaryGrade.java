package com.yjh.study.generator.domain;

public class SalaryGrade extends BaseEntity {
    private Integer grade;

    private Float highSalary;

    private Float lowSalary;

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Float getHighSalary() {
        return highSalary;
    }

    public void setHighSalary(Float highSalary) {
        this.highSalary = highSalary;
    }

    public Float getLowSalary() {
        return lowSalary;
    }

    public void setLowSalary(Float lowSalary) {
        this.lowSalary = lowSalary;
    }
}