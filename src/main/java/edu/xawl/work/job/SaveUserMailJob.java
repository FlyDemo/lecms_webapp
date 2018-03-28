package edu.xawl.work.job;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import edu.xawl.message.service.MessageService;

public class SaveUserMailJob implements Job{

	@Resource
	private MessageService messageService;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
			//取出业务传来数据
			JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
			String mailContentId = (String) jobDataMap.get("mailContent");
			String toUserId = (String)jobDataMap.get("toUser");
			
			messageService.saveUserToMail(mailContentId,toUserId);
	}

}
