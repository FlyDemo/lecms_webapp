package edu.xawl.message.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.xawl.common.service.CommonService;
import edu.xawl.message.enums.MessageState;
import edu.xawl.us.entity.UserBean;

@Controller
@RequestMapping("/MessageController")
public class MessageController {
	
	@Resource
	private CommonService commonService;
	
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

}
