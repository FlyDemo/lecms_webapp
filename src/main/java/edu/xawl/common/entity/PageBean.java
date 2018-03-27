package edu.xawl.common.entity;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.stereotype.Component;

/**
 * 封装分页数据
 * @author Administrator
 *
 * @param <T>
 */
@Component
public class PageBean<T> {
	
	protected Integer currentPage;  //当前页数
	 
	protected Integer pageSize = 10;   //每页显示多少条
	
	protected Integer totalCount;  //总记录数
	
	protected List<T> rowDatas;    //要显示的数据

	/**
	 * 获取当前是第几页
	 * @return
	 */
	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * 获取每页显示多少条
	 * @return
	 */
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取数据库中的总记录数
	 * @return
	 */
	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 获取当前页要显示的数据
	 * @return
	 */
	public List<T> getRowDatas() {
		return rowDatas;
	}

	public void setRowDatas(List<T> rowDatas) {
		this.rowDatas = rowDatas;
	}
	
	/**
	 * 获取总页数
	 * @return
	 */
	@Transient
	public Integer getTotalPage(){
		return totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize)+1;
	}
}
