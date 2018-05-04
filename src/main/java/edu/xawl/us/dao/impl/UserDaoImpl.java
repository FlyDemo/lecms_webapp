package edu.xawl.us.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import edu.xawl.common.dao.BaseDao;
import edu.xawl.common.entity.PageBean;
import edu.xawl.material.entity.BorrowFlow;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.entity.MaterialDetailBean;
import edu.xawl.material.enums.BorrowFlowStatus;
import edu.xawl.material.enums.MaterialStatus;
import edu.xawl.us.dao.UserDao;
import edu.xawl.us.entity.UserBean;

@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	private BaseDao baseDao;
	
	public UserBean findUserByLoginName(String loginName) {
		String hql = "from UserBean u where u.loginName = ? and u.deleted=? ";
		List list = baseDao.findByHql(hql, loginName,false);
		if(list.size()>0) return (UserBean) list.get(0);
		return null;
	}

	@Override
	public PageBean<BorrowFlow> findBorrowFlowWithStatus(UserBean currentUser,BorrowFlowStatus borrow) {
		String hql = " from BorrowFlow bf where bf.borrower=? and (bf.borrowStatus=? or bf.borrowStatus=?) order by bf.createTime ";
		return baseDao.findByPageQuery(new PageBean<BorrowFlow>(), hql , "BorrowFlow", currentUser,borrow,BorrowFlowStatus.BACK);
	}
	
	@Override
	public PageBean<BorrowFlow> findOverTimeList(UserBean currentUser) {
		String hql = " from BorrowFlow bf where bf.overTime=? and bf.borrower=? order by bf.createTime ";
		return baseDao.findByPageQuery(new PageBean<BorrowFlow>() , hql, "BorrowFlow", true,currentUser);
	}
	
	@Override
	public List<MaterialDetailBean> findMaterialDetailByMaterialAndReviewStatus(
			MaterialBean material) {
		String hql = " from MaterialDetailBean md where md.material=? and md.status=? ";
		return baseDao.findByHql(hql, material,MaterialStatus.REVIEW);
	}
}
