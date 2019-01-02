package com.yjh.study.generator.domain;

import java.util.Date;

public class Timekeeper extends BaseEntity {
    private String timekeeperId;

    private Date dateTime;

    private String inOut;

    private Long empId;

    public String getTimekeeperId() {
        return timekeeperId;
    }

    public void setTimekeeperId(String timekeeperId) {
        this.timekeeperId = timekeeperId == null ? null : timekeeperId.trim();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getInOut() {
        return inOut;
    }

    public void setInOut(String inOut) {
        this.inOut = inOut == null ? null : inOut.trim();
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }
}