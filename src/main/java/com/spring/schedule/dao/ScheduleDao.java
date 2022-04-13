package com.spring.schedule.dao;
import java.util.List;
import com.spring.schedule.model.ScheduleDate;

public interface ScheduleDao {
	public List<ScheduleDate> getScheduleDateList();
}
