package edu.xawl.us.controller;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	public boolean login(UserBean user,HttpServletRequest req,HttpServletResponse resp,Model model){
		HttpSession session = req.getSession();
		session.removeAttribute("user");
		UserBean loginUser = userService.login(user.getLoginName().trim(), DigestUtils.md5DigestAsHex(user.getPassWord().trim().getBytes()));
		if(loginUser!=null) {
			session.setAttribute("user", loginUser);
			Cookie nameCookie = new Cookie("loginName", loginUser.getLoginName());
			Cookie passCookie = new Cookie("passWord",user.getPassWord());
			resp.addCookie(nameCookie);
			resp.addCookie(passCookie);
			return true;
		}else{
			return false;
		}
	}
	
	@RequestMapping("/userExit")
	public String userExit(HttpServletRequest request){
		request.getSession().invalidate();
		return "/index/login";
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
	public boolean saveUser(UserBean userForm,String op){ //如果op没有值 ，那就是管理员操作，如果不是管理员操作，那就传入是保存基本信息，还是密码
		UserBean userBean = (UserBean) commonService.findById(UserBean.class,userForm.getId());
		if("base".equalsIgnoreCase(op)){
			userBean.setName(userForm.getName());
			userBean.setMail(userForm.getMail());
		}else{
			userBean.setPassWord(DigestUtils.md5DigestAsHex(userForm.getPassWord().trim().getBytes()));
		}
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
	
	@RequestMapping("/personInfo")
	public String personInfo(HttpServletRequest request,Model model){
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		user=(UserBean) commonService.findById(UserBean.class, user.getId());
		request.getSession().setAttribute("user", user);
		model.addAttribute("userBean", user);
		model.addAttribute("op", "view");
		return "/personal/editPersonalUserPage";
	}
}
