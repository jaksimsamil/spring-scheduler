package com.spring.schedule.utils;

public enum Sample {
    SAMSUNG("samsung"), APPLE("apple");

    private final String sampleType;
    Sample(String sampleType){ this.sampleType = sampleType; }
    public String getSampleType(){ return this.sampleType; }
}
