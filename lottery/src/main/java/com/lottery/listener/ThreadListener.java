package com.lottery.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.lottery.thread.FreezeThread;
import com.lottery.thread.GenerateLotteryThread;

/**
 * 负责调用线程监听器
 * @author Administrator
 */
public class ThreadListener implements ServletContextListener{
	private FreezeThread freeze;
	private GenerateLotteryThread generate;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if (null!=freeze && !freeze.isInterrupted()) {
			freeze.interrupt();   //中断线程
		}
		
		if (null!=generate && !generate.isInterrupted()) {
			generate.interrupt();   //中断线程
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		if (null == freeze) {
			FreezeThread freeze = new FreezeThread();
			freeze.start();
		}
		if (null == generate) {
			GenerateLotteryThread generate = new GenerateLotteryThread();
			generate.start();
		}
		
	}

}
