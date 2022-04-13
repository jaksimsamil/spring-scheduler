package com.spring.schedule.service.impl;

import com.spring.schedule.service.ScheduleExecService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScheduleExecAppleServiceImpl implements Runnable, ScheduleExecService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {
        LOGGER.info("Apple Run!!");
    }
}
