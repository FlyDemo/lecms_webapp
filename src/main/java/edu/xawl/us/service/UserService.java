package edu.xawl.us.service;

import edu.xawl.common.entity.PageBean;
import edu.xawl.us.entity.UserBean;

public interface UserService {

	public UserBean login(String userName,String passWord);
	
	public PageBean<UserBean> findAllUserByLeval(PageBean<UserBean> pb,String role);
	
	public boolean checkLoginName(String loginName);
}
