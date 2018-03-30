package edu.xawl.message.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.xawl.common.service.CommonService;
import edu.xawl.message.entity.MessageBean;
import edu.xawl.message.enums.MessageState;
import edu.xawl.message.service.MessageService;
import edu.xawl.us.entity.UserBean;

@Controller
@RequestMapping("/MessageController")
public class MessageController {
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private MessageService messageService;
	
	@ResponseBody
	@RequestMapping("/hasNewMessage")
	public boolean hasNewMessage(HttpServletRequest request){
		UserBean user = (UserBean)request.getSession().getAttribute("user");
		if(user!=null&&!"".equals(user.getId())){
			List<Object> findByHql = commonService.findByHql(" select count(m) from MessageBean m where m.to=? and m.messageState=? and m.deleted=? ", user,Enum.valueOf(MessageState.class, "NO_READ"),false);
			if(findByHql.size()>0){
				Integer integer = Integer.valueOf(findByHql.get(0).toString());
				return integer>0?true:false;
			}
		}
		return false;
	}
	
	/**
	 * 消息列表
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/messageList")
	public String messageList(HttpServletRequest request,Model model){
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		List<MessageBean> messageList = null;
		if(user!=null&&!"".equals(user.getId())){
			messageList = messageService.findMessageList(user);
		}
		model.addAttribute("messageList", messageList);
		return "/message/messageList";
	}
	
	
	/**
	 * 根据消息Id查看具体内容
	 */
	@RequestMapping("/showMessage")
	public String showMessage(MessageBean messageBean,Model model){
		MessageBean message = messageService.showMessage(messageBean.getId());
		model.addAttribute("message", message);
		return "/message/showMessage";
	}
	
	/**
	 * 修改消息状态
	 * @param messageBean  将要修改的消息传过来
	 * @param op           修改的具体操作  delete？   stateChange？
	 * @param request      获取当前user  以便回到自己的列表页面
	 * @param model		
	 * @return
	 */
	@RequestMapping("/editMessage")
	public String editMessage(MessageBean messageBean,String op,HttpServletRequest request,Model model){
		messageService.changeMessage(messageBean.getId(),op);
		
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		List<MessageBean> messageList = null;
		if(user!=null&&!"".equals(user.getId())){
			messageList = messageService.findMessageList(user);
		}
		model.addAttribute("messageList", messageList);
		return "/message/messageList";
	}

}
