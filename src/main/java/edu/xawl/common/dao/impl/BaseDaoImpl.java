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
import edu.xawl.common.entity.PageBean;

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

	public <T> List<T> findByHql(String hql, Object... args) {
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
	
	public <T> PageBean<T> findByPageQuery(PageBean<T> pageBean, String hql,String className,
			Object... obj) {
		if(pageBean.getCurrentPage()==null||"0".equals(pageBean.getCurrentPage())) pageBean.setCurrentPage(1);
		
		if(pageBean.getPageSize()==null||"0".equals(pageBean.getPageSize())) pageBean.setPageSize(10);
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		if(obj!=null&&obj.length>0){
			for (int i=0;i<obj.length;i++) {
				query.setParameter(i, obj[i]);
			}
		}
		
		//如果不知道一共多少条，先查询多少条
		if(pageBean.getTotalCount()==null){
			List list = query.list();
			if(list!=null){
				pageBean.setTotalCount(list.size());
			}
		}
		
		
		query.setFirstResult((pageBean.getCurrentPage()-1)*pageBean.getPageSize());
		query.setMaxResults(pageBean.getPageSize());
		List list = query.list();
		pageBean.setRowDatas(list);
		
		/*if(pageBean.getTotalCount()==null&&className!=null&&!"".equals(className)){
			Query query2 = session.createQuery(hql);
			query2.setParameter(0, false);

			pageBean.setTotalCount(Integer.valueOf(query2.list().get(0).toString()));
		}
		*/
		return pageBean;
	}

}
