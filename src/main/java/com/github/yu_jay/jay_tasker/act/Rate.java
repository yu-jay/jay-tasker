package com.github.yu_jay.jay_tasker.act;

import com.github.yu_jay.jay_tasker.iter.IRate;

/**
 * 抽象进度对象，用于字类继承
 * @author jayu
 *
 */
public class Rate implements IRate {

	/**
	 * 总数
	 */
	private int total = 0;
	
	/**
	 * 已完成数
	 */
	private int completed = 0;
	
	//完成数锁
	private Object completedLock = new Object();
	
	/**
	 * 进度保留一位小数
	 */
	@Override
	public float getRate() {
		synchronized (completedLock) {
			if(0 != total) {
				return (float)completed/total;
			}
			return 0;
		}
	}
	
	//构造方法
	public Rate(int total) {
		super();
		this.total = total;
	}

	/**
	 * 设置已完成数
	 * @param completed 当前已完成数
	 */
	public void setCompleted(int completed) {
		synchronized (completedLock) {
			this.completed = completed;
		}
	}
	
	//test
	public static void main(String[] args) {
		float r = (float)3/10;
		System.out.println(r);
	}

}
