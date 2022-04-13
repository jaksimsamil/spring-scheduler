package com.spring.schedule.service;
import java.util.List;
import com.spring.schedule.model.ScheduleDate;

public interface ScheduleInfoService {
	public List<ScheduleDate> getScheduleDateList();
}