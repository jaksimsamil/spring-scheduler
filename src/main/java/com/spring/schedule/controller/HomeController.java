package com.spring.schedule.controller;

import java.util.HashMap;
import java.util.Locale;

import com.spring.schedule.service.SchedulerService;
import com.spring.schedule.utils.Sample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;

import com.spring.schedule.service.ScheduleInfoService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ScheduleInfoService scheduleService;

	@Autowired
	private SchedulerService schedulerService;

	@RequestMapping(value="/")
	public String setting(Locale locale, Model model, HttpServletRequest request) {
		model.addAttribute("openDateList", this.scheduleService.getScheduleDateList());
		return "home";
	}

	@RequestMapping(value="/updateScheduler")
	public @ResponseBody HashMap<Object, Object> updateScheduler(@RequestParam HashMap<Object, Object> params) throws Exception{
		Sample sampleType = Sample.valueOf((String) params.get("sampleType"));
		schedulerService.stopScheduler(sampleType);
		Thread.sleep(1000);
		schedulerService.setCron((String) params.get("cron"));
		schedulerService.startScheduler(sampleType);

		HashMap<Object, Object> res = new HashMap<Object, Object>();
		res.put("res", "success");
		return res;
	}

	@RequestMapping(value="/stopScheduler")
	public @ResponseBody HashMap<Object, Object> stopScheduler(@RequestParam  HashMap<Object, Object> params) throws Exception{
		schedulerService.stopScheduler(Sample.valueOf((String) params.get("sampleType")));
		HashMap<Object, Object> res = new HashMap<Object, Object>();
		res.put("res", "success");
		return res;
	}

	@RequestMapping(value="/restartScheduler")
	public @ResponseBody HashMap<Object, Object> restartScheduler(@RequestParam  HashMap<Object, Object> params) throws Exception{
		schedulerService.startScheduler(Sample.valueOf((String) params.get("sampleType")));
		HashMap<Object, Object> res = new HashMap<Object, Object>();
		res.put("res", "success");
		return res;
	}
}
