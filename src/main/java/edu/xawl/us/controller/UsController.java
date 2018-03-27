package edu.xawl.us.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.xawl.us.entity.UserBean;
import edu.xawl.us.enums.UserLeval;

@Controller
public class UsController {
	
	@RequestMapping("/top")
	public String top(){
		return "/work/top";
	}
	
	//控制不同用户  不同导航
	@RequestMapping("/left")
	public String left(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		if(user==null){
			request.getRequestDispatcher("/UserController/loginPage").forward(request, response);
			return null;
		}else if(UserLeval.ORDINARY.toString().equalsIgnoreCase(user.getLeval().toString())){  //学生
			return "/work/left_ordinary";
		}else if(UserLeval.REPAIR_MAN.toString().equalsIgnoreCase(user.getLeval().toString())){
			return "/work/left_repairMan";
		}else if(UserLeval.SUPER_ADMIN.toString().equalsIgnoreCase(user.getLeval().toString())){
			return "/work/left_superAdmin";
		}else{
			return null;
		}
	}
	
	
	@RequestMapping("/right")
	public String right(){
		return "/work/right";
	}
	
	@RequestMapping("/bottom")
	public String bottom(){
		return "/work/bottom";
	}


}
