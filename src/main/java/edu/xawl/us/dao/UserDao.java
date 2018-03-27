package edu.xawl.us.dao;

import edu.xawl.us.entity.UserBean;

public interface UserDao {
	
	public UserBean findUserByLoginName(String loginName);
	
}
