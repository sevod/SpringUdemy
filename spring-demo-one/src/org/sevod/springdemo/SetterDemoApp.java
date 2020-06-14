package org.sevod.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SetterDemoApp {

	public static void main(String[] args) {
		
		// load the spring configuration file
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// retrieve bean from spring container
		
		CricketCoach cricketCoach = context.getBean("myCricketCoath", CricketCoach.class);			
		
		// call methods on the bean
		
		System.out.println(cricketCoach.getDailyFortune());
		
		//call our new methods to get the literal values 
		System.out.println(cricketCoach.getEmailAddress());
		System.out.println(cricketCoach.getTeam());
		
		// close the context
		context.close();
	}

}
