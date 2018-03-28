package edu.xawl.us.controller;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;
import edu.xawl.common.utils.CronUtils;
import edu.xawl.message.entity.MailContentBean;
import edu.xawl.message.enums.MailContentType;
import edu.xawl.quartz.service.QuartzService;
import edu.xawl.us.entity.UserBean;
import edu.xawl.us.service.UserService;
import edu.xawl.work.job.SaveUserMailJob;

/**
 * 用户相关Controller
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/UserController")
public class UserController {


	@Resource
	private SessionFactory sf;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private QuartzService quartzService;
	
	
	@RequestMapping("/loginPage")
	public String loginPage(HttpServletRequest request,Model model){
		request.getSession().invalidate();
		return "/index/login";
	}
	
	@ResponseBody
	@RequestMapping("/login")
	public boolean login(UserBean user,HttpServletRequest req,Model model){
		HttpSession session = req.getSession();
		session.removeAttribute("user");
		UserBean loginUser = userService.login(user.getLoginName().trim(), DigestUtils.md5DigestAsHex(user.getPassWord().trim().getBytes()));
		System.out.println(user.getLoginName());
		if(loginUser!=null) {
			session.setAttribute("user", loginUser);
			return true;
		}else{
			return false;
		}
	}
	
	@ResponseBody
	@RequestMapping("/userExit")
	public boolean userExit(HttpServletRequest request){
		request.getSession().invalidate();
		return true;
	}
	
	
	@RequestMapping("/workIndex")
	public String workIndex(HttpServletRequest req,Model model){
		/*HttpSession session = req.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if(user==null){
			model.addAttribute("loginMessage", "请先登陆！");
			return "/index/login";
		}*/
		return "/work/workIndex";
	}
	
	@RequestMapping("/userBaseInfo")
	public String userBaseInfo(){
		return "/user/userBase";
	}
	
	
	@RequestMapping("/UserList")
	public String UserList(PageBean<UserBean> pb,String role,Model model){
		if(pb!=null){
			pb = userService.findAllUserByLeval(pb,role);
		}else{
			pb = userService.findAllUserByLeval(new PageBean<UserBean>(),role);
		}
		model.addAttribute("role", role);
		model.addAttribute("pageBean", pb);
		return "/user/userList";
	}
	
	
	@RequestMapping("/editUser")
	public String editUser(UserBean userBean,String op,Model model){
		
		if(null!=userBean.getId()&&!"".equals(userBean.getId())){
			userBean = (UserBean)commonService.findById(UserBean.class, userBean.getId());
		}
		model.addAttribute("userBean",userBean);
		model.addAttribute("op", op);
		return "/user/editUserPage";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(UserBean user,String role,Model model){
		model.addAttribute("role", role.toString());
		user = (UserBean) commonService.findById(UserBean.class, user.getId());
		user.setDeleted(!user.isDeleted());
		commonService.merge(user);
		
		PageBean pb = new PageBean();
		pb = userService.findAllUserByLeval(pb,role);
		model.addAttribute("pageBean", pb);
		return "/user/userList";
	}
	
	@ResponseBody
	@RequestMapping("/saveUser")
	public boolean saveUser(UserBean userBean){
		userBean.setPassWord(DigestUtils.md5DigestAsHex(userBean.getPassWord().trim().getBytes()));
		commonService.merge(userBean);
		
		//用户保存完成后，发送邮件任务，通知用户
		if(!"".equals(userBean.getMail())){
			//根据业务类型获取邮件内容
			List<MailContentBean> mailContentList = commonService.findByHql(" from MailContentBean m where m.contentType=? ", Enum.valueOf(MailContentType.class, "SAVE_USER"));
			MailContentBean mailContent = null;
			if(mailContentList.size()>0){
				mailContent = mailContentList.get(0);
			}
			HashMap paramMap = new HashMap();
			paramMap.put("mailContent", mailContent.getId());
			paramMap.put("toUser", userBean.getId());
			String cron = CronUtils.getCronWithSeconds(System.currentTimeMillis()+60*1000);
			quartzService.addJob(UUID.randomUUID().toString(), "SAVE_USER", UUID.randomUUID().toString(), UUID.randomUUID().toString(),paramMap , SaveUserMailJob.class, cron);
		}
		return true;
	}
	
	@ResponseBody
	@RequestMapping("/checkLoginName")
	public boolean checkLoginName(UserBean userBean){
		String loginName = userBean.getLoginName().trim();
		return userService.checkLoginName(loginName);
	}
}
