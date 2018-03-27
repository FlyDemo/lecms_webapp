package edu.xawl.work.service;

import edu.xawl.common.entity.PageBean;
import edu.xawl.work.entity.NewsBean;

public interface WorkService {
	
	public PageBean findNewsList(PageBean<NewsBean> pageBean);
}
