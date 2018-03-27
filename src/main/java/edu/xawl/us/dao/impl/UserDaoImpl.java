package edu.xawl.us.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import edu.xawl.us.dao.UserDao;
import edu.xawl.us.entity.UserBean;

@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	private SessionFactory sessionFactory;
	
	public UserBean findUserByLoginName(String loginName) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from UserBean u where u.loginName = ?");
		query.setParameter(0,loginName);
		List<UserBean> list = query.list();
		if(list.size()>0) return list.get(0);
		return null;
	}
}
