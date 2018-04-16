package edu.xawl.work.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import edu.xawl.common.dao.BaseDao;
import edu.xawl.common.entity.PageBean;
import edu.xawl.material.entity.BorrowFlow;
import edu.xawl.material.enums.BorrowFlowStatus;
import edu.xawl.work.dao.WorkDao;
import edu.xawl.work.entity.NewsBean;

@Repository
public class WorkDaoImpl implements WorkDao {
	
	@Resource
	private SessionFactory sf;
	
	@Resource
	private BaseDao baseDao;

	public PageBean findNewsList(PageBean<NewsBean> pageBean) {
		if(pageBean.getCurrentPage()==null||"0".equals(pageBean.getCurrentPage())) pageBean.setCurrentPage(1);
		
		if(pageBean.getPageSize()==null||"0".equals(pageBean.getPageSize())) pageBean.setPageSize(10);
		
		Session session = sf.getCurrentSession();
		Query query = session.createQuery(" from NewsBean Order by sortNum , modifyTime ");
		query.setFirstResult((pageBean.getCurrentPage()-1)*pageBean.getPageSize());
		query.setMaxResults(pageBean.getPageSize());
		List list = query.list();
		pageBean.setRowDatas(list);
		
		if(pageBean.getTotalCount()==null){
			Query query2 = session.createQuery(" select count(id) from NewsBean ");
			pageBean.setTotalCount(Integer.valueOf(query2.list().get(0).toString()));
		}
		
		return pageBean;
	}
	
	
	@Override
	public PageBean<BorrowFlow> findBorrowFlowByStatus(BorrowFlowStatus borrow) {
		String hql = " from BorrowFlow where borrowStatus=? ";
		return baseDao.findByPageQuery(new PageBean<BorrowFlow>(), hql, "BorrowFlow", borrow);
	}
}
