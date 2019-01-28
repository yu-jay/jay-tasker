package com.github.yu_jay.jay_tasker.act;

import com.github.yu_jay.jay_tasker.iter.IRate;

/**
 * 循环进度，永远不会停止的任务，比如定时器固定时间执行一个任务
 * @author 余杰
 *
 */
public class LoopRate implements IRate {

	@Override
	public float getRate() {
		return -1f;
	}

}
