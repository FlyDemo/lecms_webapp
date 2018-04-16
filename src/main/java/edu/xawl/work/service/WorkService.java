package edu.xawl.work.service;

import edu.xawl.common.entity.PageBean;
import edu.xawl.material.entity.BorrowFlow;
import edu.xawl.material.entity.MaterialDetailBean;
import edu.xawl.work.entity.NewsBean;

public interface WorkService {
	
	public PageBean findNewsList(PageBean<NewsBean> pageBean);

	public PageBean<BorrowFlow> findDealteBorrowList();

	public void dealte(BorrowFlow borrowFlow,String reviewContent, String materials, String op);

	public PageBean<BorrowFlow> findDealteBackList();

	public void dealteBack(String id);

	public void bad(String badCode, String badContext);

	public PageBean<MaterialDetailBean> badMaterialDetailList(
			PageBean<MaterialDetailBean> pb);
}
