package edu.xawl.filter;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.xawl.us.entity.UserBean;
import edu.xawl.us.service.UserService;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Resource
	private UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		/**
		 * 检测session中对象是否存在，如果不存在将Cookie中的放入session
		 */
		UserBean userBean = (UserBean) request.getSession().getAttribute("user");
		if(userBean==null){
			Cookie[] cookies = request.getCookies();
			String userName = null;
			String passWord = null;
			for (Cookie cookie : cookies) {
				if(cookie.getName().trim().equalsIgnoreCase("loginName")){
					userName = cookie.getValue();
				}
				if(cookie.getName().trim().equalsIgnoreCase("passWord")){
					passWord = cookie.getValue();
				}
			}
			if(userName!=null&&passWord!=null){
				UserBean login = userService.login(userName, DigestUtils.md5DigestAsHex(passWord.getBytes()));
				if(login!=null){
					request.getSession().setAttribute("user", login);
				}
			}
		}
		
		/**
		 * 检测是否访问受限页面
		 */
		if(request.getServletPath().startsWith("/UserController/loginPage")||request.getServletPath().startsWith("/UserController/login")){
			return true;
		}else{
			UserBean user = (UserBean) request.getSession().getAttribute("user");
			if(user!=null){
				return true;
			}else{
				Cookie[] cookies = request.getCookies();
				String userName = null;
				String passWord = null;
				for (Cookie cookie : cookies) {
					if(cookie.getName().trim().equalsIgnoreCase("loginName")){
						userName = cookie.getValue();
					}
					if(cookie.getName().trim().equalsIgnoreCase("passWord")){
						passWord = cookie.getValue();
					}
				}
				if(userName!=null&&passWord!=null){
					UserBean login = userService.login(userName, DigestUtils.md5DigestAsHex(passWord.getBytes()));
					if(login!=null){
						request.getSession().setAttribute("user", login);
						return true;
					}
				}
					
				/*request.getRequestDispatcher("/UserController/loginPage").forward(request, response);*/
				response.sendRedirect(request.getSession().getServletContext().getContextPath()+"/UserController/loginPage");
				return false;
				
			}
		}
	}
}
