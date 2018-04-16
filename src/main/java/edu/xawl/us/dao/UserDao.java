package edu.xawl.us.dao;

import java.util.List;

import edu.xawl.common.entity.PageBean;
import edu.xawl.material.entity.BorrowFlow;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.entity.MaterialDetailBean;
import edu.xawl.material.enums.BorrowFlowStatus;
import edu.xawl.us.entity.UserBean;

public interface UserDao {
	
	public UserBean findUserByLoginName(String loginName);

	public PageBean<BorrowFlow> findBorrowFlowWithStatus(UserBean currentUser,BorrowFlowStatus borrow);

	public PageBean<BorrowFlow> findOverTimeList(UserBean currentUser);

	public List<MaterialDetailBean> findMaterialDetailByMaterialAndReviewStatus(
			MaterialBean material);
	
}
