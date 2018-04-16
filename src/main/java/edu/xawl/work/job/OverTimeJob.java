package edu.xawl.work.job;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import edu.xawl.common.service.CommonService;
import edu.xawl.common.utils.MailUtils;
import edu.xawl.material.entity.BorrowFlow;
import edu.xawl.material.enums.BorrowFlowStatus;
import edu.xawl.message.entity.MessageBean;
import edu.xawl.message.enums.MessageState;
import edu.xawl.us.entity.UserBean;
/**
 * 定时标记过时状态
 * @author Administrator
 *
 */
public class OverTimeJob implements Job {
	
	@Resource
	private CommonService commonService;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		String hql = " from BorrowFlow bf where (bf.borrowStatus = ? or bf.borrowStatus=?) and bf.overTime!=? ";
		List<BorrowFlow> borrowFlow = commonService.findByHql(hql, BorrowFlowStatus.CONSENT,BorrowFlowStatus.BACK,true);
		Date now = new Date();
		
		UserBean from = (UserBean)commonService.findByHql(" from UserBean u where u.loginName = ? ", "admin").get(0);
		
		for (BorrowFlow borrowFlow2 : borrowFlow) {
			
			long backTime = borrowFlow2.getGoBackTime().getTime();
			long tipTime = backTime-3*24*60*60*1000;
			if(tipTime-now.getTime()<0&&tipTime-now.getTime()>-1000*60*60*24){
				MessageBean messageBean = new MessageBean(from, borrowFlow2.getBorrower(), "您借用的器材:'"+borrowFlow2.getMaterial().getMaterialName()+"'即将达到归还期限!", MessageState.NO_READ, false, false);
				commonService.merge(messageBean);
				
				if(null==borrowFlow2.getBorrower().getMail()||"".equals(borrowFlow2.getBorrower().getMail())){
					System.out.println("===============邮件发送失败！=======================");
				}else{
					MailUtils.sendEmailFromAdmin("器材时间通知！", "您借用的器材:'"+borrowFlow2.getMaterial().getMaterialName()+"'即将达到归还期限!", borrowFlow2.getBorrower().getMail());
				}
			}
			if(borrowFlow2.getGoBackTime().before(now)){
				borrowFlow2.setOverTime(true);
				commonService.merge(borrowFlow2);
				
				MessageBean messageBean = new MessageBean(from, borrowFlow2.getBorrower(), "您借用的器材:'"+borrowFlow2.getMaterial().getMaterialName()+"'已经超过归还期限!", MessageState.NO_READ, false, false);
				commonService.merge(messageBean);
				
				if(null==borrowFlow2.getBorrower().getMail()||"".equals(borrowFlow2.getBorrower().getMail())){
					System.out.println("===============邮件发送失败！=======================");
				}else{
					MailUtils.sendEmailFromAdmin("器材时间通知！", "您借用的器材:'"+borrowFlow2.getMaterial().getMaterialName()+"'已经超过归还期限!", borrowFlow2.getBorrower().getMail());
				}
				
			}
		}
	}

}
