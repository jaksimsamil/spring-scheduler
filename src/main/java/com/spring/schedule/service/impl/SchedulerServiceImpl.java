package com.spring.schedule.service.impl;

import com.spring.schedule.service.SchedulerService;
import com.spring.schedule.utils.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SchedulerServiceImpl implements SchedulerService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private static final Map<String, ThreadPoolTaskScheduler> scheduledMap = new HashMap<>();
    private String cron = "*/2 * * * * *";
    // 4월 6일 17시 28분
    //private String cron = "* 29 17 6 4 *";

    @Override
    public void startScheduler(Sample sampleType) {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.initialize();
        // scheduler setting
        scheduler.schedule(getRunnable(sampleType), getTrigger());
        scheduledMap.put(sampleType.getSampleType(), scheduler);
    }

    @Override
    public void setCron(String cron) {
        this.cron = cron;
    }

    @Override
    public void stopScheduler(Sample sampleType) {
        scheduledMap.get(sampleType.getSampleType()).shutdown();
    }

    private Runnable getRunnable(Sample sampleType) {
        // do something
        Runnable scheduleExecService;
        switch (sampleType){
            case APPLE:
                scheduleExecService = new ScheduleExecAppleServiceImpl();
                break;
            case SAMSUNG:
                scheduleExecService = new ScheduleExecSamsungServiceImpl();
                break;
            default:
                scheduleExecService = new ScheduleExecSamsungServiceImpl();
                break;
        }
        return scheduleExecService;
    }

    private Trigger getTrigger() {
        // cronSetting
        return new CronTrigger(cron);
    }
}
