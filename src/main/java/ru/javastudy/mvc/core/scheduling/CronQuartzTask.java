package ru.javastudy.mvc.core.scheduling;

import java.util.Calendar;

/**
 * look application-context.xml
 * 1. cronTrigger
 * 2. quartzCronJob
 * 3. bean id="cronQuartzTask"
 * 4. Quartz Scheduler
 */
public class CronQuartzTask {

    public void cronTaskMethod() {
        //you can send emails to users here
        System.out.println("Cron Time: " + Calendar.getInstance().getTime());
    }
}