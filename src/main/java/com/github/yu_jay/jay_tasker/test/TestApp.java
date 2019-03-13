package com.github.yu_jay.jay_tasker.test;

import org.quartz.SchedulerException;

import com.github.yu_jay.jay_tasker.utils.QuartzHelper;

public class TestApp {

	public static void main(String[] args) {

		try {
			QuartzHelper.addJob("test", Job2.class, 1, "ok");
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
