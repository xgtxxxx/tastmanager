package task.cron.trigger;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;

import task.model.EmailConfig;
import task.service.QuratzService;

public class EmailCronTrigger extends CronTriggerFactoryBean {
	
	@Autowired
	private QuratzService quratzService;
	
	@Override
	public void setJobDetail(JobDetail jobDetail) {
		super.setJobDetail(jobDetail);
		String name = jobDetail.getKey().getName();
		String cron = null;
		EmailConfig config = this.quratzService.getCronExpression();
		if(name.equals("jobtask")){
			cron = config.getDailyTime();
		}
		if(name.equals("notice")){
			cron = config.getNoticeTime();
		}
		this.setCronExpression(cron);
	}
	
}
