package edu.xawl.common.dao.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import edu.xawl.common.dao.BaseDao;
import edu.xawl.common.entity.BaseBean;

@SuppressWarnings("restriction")
@Repository
public class BaseDaoImpl implements BaseDao{

	@Resource
	private SessionFactory sessionFactory;
	
	public <T extends BaseBean> Integer merge(T t) {
		Session session = sessionFactory.getCurrentSession();
		try{
			Date nowTime = new Date();
			if(null==t.getId()||"".equals(t.getId())){
				t.setCreateTime(nowTime);
			}
			t.setModifyTime(nowTime);
			session.saveOrUpdate(t);
			session.flush();
		}catch(Exception e){
			return -1;
		}
		return 0;
	}

	public Integer delete(Class clazz,String seri) {
		Session session = sessionFactory.getCurrentSession();
		Object object = session.get(clazz, seri);
		if(null==object){
			return -1;
		}
		session.delete(object);
		return 0;
	}

	public <T> List<T> findByHql(String hql, String... args) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		for (int i=0;i<args.length;i++) {
			query.setParameter(i, args[i]);
		}
		return query.list();
	}

	public <T> List<T> find(Class clazz,String seri) {
		Session session = sessionFactory.getCurrentSession();
		return (List<T>) session.load(clazz, seri);
		
	}

}
