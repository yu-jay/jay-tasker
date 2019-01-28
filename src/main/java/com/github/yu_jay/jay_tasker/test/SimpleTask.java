package com.github.yu_jay.jay_tasker.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.github.yu_jay.jay_tasker.act.AbstractSimpleTask;

public class SimpleTask extends AbstractSimpleTask {
	
	private int total = 0;
	
	private List<String> sqls = new ArrayList<String>();

	private static final ExecutorService executor = Executors.newSingleThreadExecutor();
	
	public SimpleTask(int total) {
		super(total);
		for(int i=0; i<total; i++) {
			sqls.add("第" + i + "条记录。");
		}
	}

	@Override
	public void startExecute() {
		executor.submit(() -> {
			while(total < sqls.size()) {
				System.out.println("现在进度：" + (int)(this.getRate().getRate()*100) + "%");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println("现在进度：" + total);
		});
		while(total < sqls.size()) {
			System.out.println("正在执行" + sqls.get(total));
			total ++;
			this.setCompleted(total);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		executor.shutdown();
	}

	@Override
	public void endExecute() {
		
	}
	
	public static void main(String[] args) {
		SimpleTask simpleTask = new SimpleTask(10);
		simpleTask.start();
	}

}
