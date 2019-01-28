package com.github.yu_jay.jay_tasker.utils;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 定时任务管理助手
 * @author jayu
 *
 */
public class QuartzHelper {
	
	//默认工作组
	private final static String JOB_GROUP_NAME = "defaultJobGroup";
	
	//默认触发器组
	private final static String TRIGGER_GROUP_NAME = "defaultTriggerGroup";
	
	//默认触发器名
	private final static String TRIGGER_NAME = "defaultTrigger";
	
	/**
	 * 添加定时任务(立即执行)
	 * @param jobName 任务名称
	 * @param classType 任务类
	 * @param seconds 定时器间隔秒数
	 * @return
	 * @throws SchedulerException
	 */
	public static Scheduler addJob(String jobName, Class<? extends Job> classType, int seconds) 
			throws SchedulerException {
		return addJob(jobName, classType, seconds, null);
	}
	
	/**
	 * 添加定时任务(立即执行)(可以向任务传递参数)
	 * @param jobName 任务名称
	 * @param classType 任务类
	 * @param seconds 定时器间隔秒数
	 * @param data 向任务类中传递的数据
	 * @return
	 * @throws SchedulerException
	 */
	public static Scheduler addJob(String jobName, Class<? extends Job> classType, int seconds, Object data) 
			throws SchedulerException {
		return addJob(jobName, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, classType, seconds, data);
	}
	
	/**
	 * 添加定时任务(立即执行)
	 * @param jobName 任务名称
	 * @param jobGroup 任务所属组
	 * @param triggerName 触发器名称
	 * @param triggerGroup 出发前所属组
	 * @param classType 任务类
	 * @param seconds 定时器间隔秒数
	 * @param data 向任务类中传递的数据
	 * @return
	 * @throws SchedulerException
	 */
	public static Scheduler addJob(String jobName, String jobGroup, String triggerName, 
			String triggerGroup, Class<? extends Job> classType, int seconds, Object data) 
					throws SchedulerException {
		//触发器（立即执行）
		Trigger trigger = TriggerBuilder.newTrigger()//创建一个新的TriggerBuilder来规范一个触发器
    		   .withIdentity(triggerName, triggerGroup)//给触发器起一个名字和组名
    		   .startNow()//立即执行
    		   .withSchedule(
	    		   SimpleScheduleBuilder.simpleSchedule()
	    		   .withIntervalInSeconds(seconds)//时间间隔  单位：秒
	    		   .repeatForever()//一直执行
    		   )
    		   .build();//产生触发器
		return addJob(jobName, jobGroup, triggerName, triggerGroup, classType, trigger, data);
	}
	
	/**
	 * 添加定时任务(在一定时间后执行)
	 * @param jobName 任务名称
	 * @param jobGroup 任务所属组
	 * @param triggerName 触发器名称
	 * @param triggerGroup 出发前所属组
	 * @param classType 任务类
	 * @param runTime 开始时间
	 * @param seconds 定时器间隔秒数
	 * @param data 向任务类中传递的数据
	 * @return
	 * @throws SchedulerException
	 */
	public static Scheduler addJob(String jobName, String jobGroup, String triggerName, 
			String triggerGroup, Class<? extends Job> classType, Date runTime, int seconds, Object data) 
			throws SchedulerException {
		//触发器
        Trigger trigger = TriggerBuilder.newTrigger()//创建一个新的TriggerBuilder来规范一个触发器
    		   .withIdentity(triggerName, triggerGroup)//给触发器起一个名字和组名
    		   .startAt(runTime)//立即执行
    		   .withSchedule(
	    		   SimpleScheduleBuilder.simpleSchedule()
	    		   .withIntervalInSeconds(seconds)//时间间隔  单位：秒
	    		   .repeatForever()//一直执行
    		   )
    		   .build();//产生触发器
        return addJob(jobName, jobGroup, triggerName, triggerGroup, classType, trigger, data);
	}
	
	/**
	 * 添加定时任务（通过触发器）
	 * @param jobName 任务名称
	 * @param classType 任务类
	 * @param trigger 触发器
	 * @return
	 * @throws SchedulerException
	 */
	public static Scheduler addJob(String jobName, Class<? extends Job> classType, Trigger trigger) 
			throws SchedulerException {
		return addJob(jobName, JOB_GROUP_NAME, TRIGGER_NAME, TRIGGER_GROUP_NAME, classType, trigger, null);
	}
	
	/**
	 * 添加定时任务（通过触发器）
	 * @param jobName 任务名称
	 * @param jobGroup 任务所属组
	 * @param triggerName 触发器名称
	 * @param triggerGroup 出发前所属组
	 * @param classType 任务类
	 * @param trigger 触发器
	 * @param data 向任务类中传递的数据
	 * @return
	 * @throws SchedulerException
	 */
	public static Scheduler addJob(String jobName, String jobGroup, String triggerName, 
			String triggerGroup, Class<? extends Job> classType, Trigger trigger, Object data) 
					throws SchedulerException {
		//任务工厂
		SchedulerFactory schedulerFactory = new StdSchedulerFactory();
		//任务
		Scheduler scheduler = schedulerFactory.getScheduler();
		//用于描叙Job实现类及其他的一些静态信息，构建一个作业实例
		JobDetail jobDetail = JobBuilder.newJob(classType)
				.withIdentity(jobName, jobGroup)
				.build();
		scheduler.scheduleJob(jobDetail, trigger);
		//设置参数
		jobDetail.getJobDataMap().put("data", data);
	    //启动
        if(!scheduler.isShutdown()) {
        	scheduler.start();
        }
		return scheduler;
	}
	
	/**
	 * 关闭定时任务
	 * @param scheduler
	 * @throws SchedulerException
	 */
	public static void shutdown(Scheduler scheduler) 
			throws SchedulerException {
		if(null != scheduler) {
			scheduler.shutdown();
		}
	}

}
