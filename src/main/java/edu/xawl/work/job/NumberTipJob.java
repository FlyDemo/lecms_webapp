package edu.xawl.work.job;

import java.util.List;

import javax.annotation.Resource;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import edu.xawl.common.service.CommonService;
import edu.xawl.common.utils.MailUtils;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.enums.MaterialStatus;
import edu.xawl.message.entity.MessageBean;
import edu.xawl.message.enums.MessageState;
import edu.xawl.us.entity.UserBean;
/**
 * 定时提醒数量不足
 * @author Administrator
 *
 */
public class NumberTipJob implements Job{

	@Resource
	private CommonService commonService;
	
	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		String hql = " from MaterialBean mb where mb.deleted=? ";
		List<MaterialBean> material = commonService.findByHql(hql, false);
		
		for (MaterialBean materialBean : material) {
			String hql1 = " select count(mdb) from MaterialDetailBean mdb where mdb.material=? and mdb.status=? ";
			List<Integer> numList = commonService.findByHql(hql1, materialBean,MaterialStatus.NOMAL);
			UserBean from = (UserBean) commonService.findById(UserBean.class, "0");
			UserBean to = (UserBean) commonService.findById(UserBean.class, "1");
			if(numList.size()>0){
				Integer integer = Integer.valueOf(String.valueOf(numList.get(0)));
				if(materialBean.getTipNum()!=null&&!"".equals(materialBean.getTipNum())&&integer<=materialBean.getTipNum()){//提醒数量不足
					MessageBean messageBean = new MessageBean(from, to, "器材不足信息提示！具体信息如下：（器材分类）"+materialBean.getMaterialCategory().getCategoryName()+"（器材名称）"+materialBean.getMaterialName()+"余量不足"+materialBean.getTipNum(), MessageState.NO_READ, false, false);
					commonService.merge(messageBean);
					
					if(null==to.getMail()||"".equals(to.getMail())){
						System.out.println("===============邮件发送失败！=======================");
					}else{
						MailUtils.sendEmailFromAdmin("器材不足信息提示！", "器材不足信息提示！具体信息如下：（器材分类）"+materialBean.getMaterialCategory().getCategoryName()+"（器材名称）"+materialBean.getMaterialName()+"余量不足"+materialBean.getTipNum(), to.getMail());
					}
				}
			}
		}
	}

}
