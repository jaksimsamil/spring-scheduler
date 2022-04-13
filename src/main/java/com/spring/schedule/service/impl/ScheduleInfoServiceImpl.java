package com.spring.schedule.service.impl;

import java.util.List;

import com.spring.schedule.model.ScheduleDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.schedule.dao.ScheduleDao;
import com.spring.schedule.service.ScheduleInfoService;

@Component
public class ScheduleInfoServiceImpl implements ScheduleInfoService {

	@Autowired
	private ScheduleDao scheduleDao;

	@Override
	public List<ScheduleDate> getScheduleDateList() {
		return this.scheduleDao.getScheduleDateList();
	}
}


