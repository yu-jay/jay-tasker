package com.github.yu_jay.jay_tasker.act;

import com.github.yu_jay.jay_tasker.iter.IRate;

/**
 * 循环任务简单抽象类
 * @author jayu
 *
 */
public abstract class AbstractLoopTask extends AbstractTask {

	//进度
	private LoopRate rate;
	
	public AbstractLoopTask() {
		super();
		this.rate = new LoopRate();
	}

	@Override
	public IRate getRate() {
		return rate;
	}

}
