package com.github.yu_jay.jay_tasker.test;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class Job2 implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap data = context.getJobDetail().getJobDataMap();
		
		System.out.println(data.get("data"));
	}

}
