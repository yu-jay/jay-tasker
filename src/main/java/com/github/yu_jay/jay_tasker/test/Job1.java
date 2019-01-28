package com.github.yu_jay.jay_tasker.test;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.github.yu_jay.jay_tasker.utils.QuartzHelper;

public class Job1 implements Job {
	
	private static int index = 1;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		System.out.println("我正在执行第" + index + "...");
		
		if(index > 10) {
			JobTest jobTest = (JobTest) context.getJobDetail().getJobDataMap().get("data");
			System.out.println(jobTest.getScheduler());
			try {
				QuartzHelper.shutdown(jobTest.getScheduler());
			} catch (SchedulerException e) {
				e.printStackTrace();
			}
		}
		
		index ++;
	}

}
