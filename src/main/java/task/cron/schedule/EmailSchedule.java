package task.cron.schedule;

import java.text.ParseException;
import java.util.List;

import org.quartz.CronTrigger;
import org.quartz.SchedulerException;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import task.mail.MailService;

public class EmailSchedule extends SchedulerFactoryBean implements Schedule {
	
	@Autowired
	private MailService mailService;
	
	private List<CronTriggerImpl> emailTriggers;
	
	public void setEmailTriggers(List<CronTriggerImpl> emailTriggers){
		CronTrigger[] ts = new CronTrigger[emailTriggers.size()];
		for (int i=0; i<ts.length; i++) {
			ts[i]=emailTriggers.get(i);
		}
		super.setTriggers(ts);
		this.emailTriggers = emailTriggers;
	}
	
	public void resetTrigger(String runtime,String triggerName) throws SchedulerException,NullPointerException, ParseException{
		CronTriggerImpl trigger = this.getTrigger(triggerName);
		if(trigger!=null){
			trigger.setCronExpression(runtime);
			this.getScheduler().rescheduleJob(trigger.getKey(), trigger);
		}else{
			throw new NullPointerException("trigger is null!");
		}
	}
	
	private CronTriggerImpl getTrigger(String name){
		for (CronTriggerImpl trigger : emailTriggers) {
			if(trigger.getKey().getName().equals(name)){
				return trigger;
			}
		}
		return null;
	}

}
