package com.mai.task;

import org.springframework.beans.factory.annotation.Autowired;

import com.mai.activity.service.LevelRuleService;
import com.mai.util.TimeUtils;

public class StarTask {

	 @Autowired
	private LevelRuleService levelService;
	 
	public void runTask(){
		System.out.println("定时任务开始"+TimeUtils.getNowTime());
		
		levelService.updateAllStar();
		
		System.out.println("定时任务结束"+TimeUtils.getNowTime());
	}
	
}
