package com.spring.schedule.service;

import com.spring.schedule.utils.Sample;

public interface SchedulerService {
	public void startScheduler(Sample sampleType);
	public void stopScheduler(Sample sampleType);
	public void setCron(String cron);
}