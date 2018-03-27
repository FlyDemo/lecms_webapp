package edu.xawl.work.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.xawl.common.entity.PageBean;
import edu.xawl.work.dao.WorkDao;
import edu.xawl.work.entity.NewsBean;
import edu.xawl.work.service.WorkService;

@Service
public class WorkServiceImpl implements WorkService {

	@Resource
	private WorkDao workDao;

	public PageBean findNewsList(PageBean<NewsBean> pageBean) {
		return workDao.findNewsList(pageBean);
	}
}
