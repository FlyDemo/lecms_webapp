package edu.xawl.work.dao;

import edu.xawl.common.entity.PageBean;
import edu.xawl.work.entity.NewsBean;

public interface WorkDao {

	public PageBean findNewsList(PageBean<NewsBean> pageBean);
}
