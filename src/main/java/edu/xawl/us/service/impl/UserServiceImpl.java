package edu.xawl.us.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.xawl.common.dao.BaseDao;
import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;
import edu.xawl.us.dao.UserDao;
import edu.xawl.us.entity.UserBean;
import edu.xawl.us.enums.UserLeval;
import edu.xawl.us.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private BaseDao baseDao;
	
	public UserBean login(String userName, String passWord) {
		UserBean user = userDao.findUserByLoginName(userName);
		if(user!=null){
			if(passWord.equals(user.getPassWord())) 
			return user;
		}
		return null;
	}

	@Override
	public PageBean<UserBean> findAllUserByLeval(PageBean<UserBean> pb,String role) {
		//获取页面传递过来的角色，要是角色为空或者不是有效角色，直接返回null
		if(!"".equals(role)){
			try{
				UserLeval leval = Enum.valueOf(UserLeval.class, role);
				PageBean<UserBean> allUsers = commonService.findByPageQuery(pb, " from UserBean u where u.leval=? ", " UserBean ", leval);
				return allUsers;
			}catch(Exception e){
				
			}
		}
		return null;
		
	}

	@Override
	public boolean checkLoginName(String loginName) {
		
		List<Object> findByHql = baseDao.findByHql(" from UserBean where loginName = ? ", loginName);
		if(findByHql.size()==0){
			return true;
		}
		return false;
	}

}
