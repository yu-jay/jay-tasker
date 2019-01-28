package com.github.yu_jay.jay_tasker.test;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import com.github.yu_jay.jay_tasker.utils.QuartzHelper;

public class JobTest {
	
	public static Scheduler scheduler;

	public static void main(String[] args) {

		try {
			
			scheduler = QuartzHelper.addJob("test", Job1.class, 1, new JobTest());
			System.out.println(scheduler);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
	
	public static Scheduler getScheduler() {
		return scheduler;
	}

}
