package edu.xawl.work.dao;

import edu.xawl.common.entity.PageBean;
import edu.xawl.material.entity.BorrowFlow;
import edu.xawl.material.enums.BorrowFlowStatus;
import edu.xawl.work.entity.NewsBean;

public interface WorkDao {

	public PageBean findNewsList(PageBean<NewsBean> pageBean);

	public PageBean<BorrowFlow> findBorrowFlowByStatus(BorrowFlowStatus borrow);
}
