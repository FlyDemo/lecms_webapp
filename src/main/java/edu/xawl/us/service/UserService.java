package edu.xawl.us.service;

import edu.xawl.common.entity.PageBean;
import edu.xawl.material.entity.BorrowFlow;
import edu.xawl.us.entity.UserBean;

public interface UserService {

	public UserBean login(String userName,String passWord);
	
	public PageBean<UserBean> findAllUserByLeval(PageBean<UserBean> pb,String role);
	
	public boolean checkLoginName(String loginName);

	/**
	 * 我的申请  表示管理未通过的数据
	 * @param currentUser
	 * @return
	 */
	public PageBean<BorrowFlow> findMyBorrow(UserBean currentUser);

	public PageBean<BorrowFlow> findMyNoBack(UserBean currentUser);

	public PageBean<BorrowFlow> findMyGoBack(UserBean currentUser);

	public PageBean<BorrowFlow> findOverTimeList(UserBean currentUser);

	public void cansMyBorrow(String id);

	public void back(BorrowFlow borrowFlow);

	public void cansBack(BorrowFlow borrowFlow);
}
