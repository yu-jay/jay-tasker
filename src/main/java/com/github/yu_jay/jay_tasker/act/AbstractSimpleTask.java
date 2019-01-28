package com.github.yu_jay.jay_tasker.act;

import com.github.yu_jay.jay_tasker.iter.IRate;

/**
 * 简单任务抽象实现
 * @author yujie
 *
 */
public abstract class AbstractSimpleTask extends AbstractTask {
	
	//进度
	private Rate rate;

	/**
	 * 构造方法
	 * @param total 任务的总数量
	 */
	public AbstractSimpleTask(int total) {
		super();
		this.rate = new Rate(total);
	}

	/**
	 * 获取进度
	 */
	@Override
	public IRate getRate() {
		return rate;
	}
	
	/**
	 * 设置最新的任务进度
	 * @param completed
	 */
	public void setCompleted(int completed) {
		rate.setCompleted(completed);
	}

}
