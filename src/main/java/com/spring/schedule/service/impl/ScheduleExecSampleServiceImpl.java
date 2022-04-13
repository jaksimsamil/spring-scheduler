package com.spring.schedule.service.impl;

import com.spring.schedule.service.ScheduleExecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleExecSampleServiceImpl implements ScheduleExecService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "*/2 * * * * *")
    public void schedulerRunnable() {
        LOGGER.info("Spring Scheduler Running!!");
    }
}
