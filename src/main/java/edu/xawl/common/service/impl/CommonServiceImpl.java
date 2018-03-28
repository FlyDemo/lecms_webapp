package edu.xawl.common.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import edu.xawl.common.entity.BaseBean;
import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;


@Service
public class CommonServiceImpl implements CommonService{

	@Resource
	private SessionFactory sessionFactory;
	
	public <T extends BaseBean> Integer merge(T t) {
		Session session = sessionFactory.getCurrentSession();
		try{
			Date nowTime = new Date();
			if(null==t.getId()||"".equals(t.getId())){
				t.setCreateTime(nowTime);
				t.setId(null);
			}
			t.setModifyTime(nowTime);
			session.saveOrUpdate(t);
			session.flush();
		}catch(Exception e){
			return -1;
		}
		return 0;
	}

	public <T> Object findById(Class<T> clazz, String serilaz) {
		return sessionFactory.getCurrentSession().load(clazz, serilaz);
	}
	
	public <T> List<T> findByHql(String hql, Object... args) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		for (int i=0;i<args.length;i++) {
			query.setParameter(i, args[i]);
		}
		return query.list();
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
