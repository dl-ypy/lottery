package com.lottery.thread;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.lottery.action.UserAction;
/**
 * 负责调用冻结用户方法的循环线程
 * @author Administrator
 */
public class FreezeThread extends Thread{
	/*
	 * 要通过读取配置文件的方法读取到注入的UserAction
	 */
	ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
	private UserAction user = ac.getBean(UserAction.class);
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(2000);
				user.freeze();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
