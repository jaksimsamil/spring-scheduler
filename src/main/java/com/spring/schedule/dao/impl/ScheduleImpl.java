package com.spring.schedule.dao.impl;
import java.util.List;

import com.spring.schedule.model.ScheduleDate;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.schedule.dao.ScheduleDao;

@Component
public class ScheduleImpl implements ScheduleDao {
	@Autowired
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession = sqlSession;
	}
	
	@Override
	public List<ScheduleDate> getScheduleDateList() {
		return this.sqlSession.selectList("getScheduleDateList");
	}
}