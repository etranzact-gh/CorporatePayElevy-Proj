package com.etz.CorporatePayElevy.Scheduler;

import java.io.FileReader;
import java.util.Properties;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.etz.CorporatePayElevy.TransDetails.MainClass;


public class SchedulerClass {
	private static Properties properties;

	public static void main(String[] args) throws SchedulerException {
		getTrigger();
	}

	public static void getTrigger() throws SchedulerException {
		try (
			FileReader reader = new FileReader("cfg\\CorperatePayElevy.properties")){

			properties = new Properties();
			properties.load(reader);
         
		} catch (Exception e) {
			e.printStackTrace();
		}

		JobDetail j = JobBuilder.newJob(MainClass.class).build();

		Trigger t = TriggerBuilder.newTrigger().withIdentity("SCHEDULER", "SERVICES").startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule(properties.getProperty("CRON_JOB_TIME"))).build();

		Scheduler s = StdSchedulerFactory.getDefaultScheduler();
		s.start();
		s.scheduleJob(j, t);
 
	}

}