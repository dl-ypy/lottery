package com.lottery.thread;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.lottery.action.LotteryAction;
/**
 * 生成彩票的循环线程
 * @author Administrator
 */
public class GenerateLotteryThread extends Thread{
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	private LotteryAction lottery = ac.getBean(LotteryAction.class);
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				lottery.generate();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
