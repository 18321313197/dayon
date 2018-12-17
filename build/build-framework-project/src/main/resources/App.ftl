package ${appTypeInfo.packageName};

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dayon.common.log4j.Log4jLogFactory;

public class App {

	public static void main(String[] args){
		// 加载Log4j2SystemLogDriver
		try {
			Class.forName("com.dayon.common.log4j.Log4jSystemLogDriver");
			Log4jLogFactory.getLogger().info("加载com.dayon.common.log4j.Log4jSystemLogDriver");
		} catch (ClassNotFoundException e) {
			Log4jLogFactory.getLogger().error("加载 com.dayon.common.log4j.Log4jSystemLogDriver 异常", e);
		}
		// 加载Spring容器
		@SuppressWarnings("resource")
		ApplicationContext app=new ClassPathXmlApplicationContext("spring-context.xml");
		synchronized (app) {
			try {
				app.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
