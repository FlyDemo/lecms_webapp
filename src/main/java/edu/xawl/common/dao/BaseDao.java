package edu.xawl.common.dao;

import java.util.List;

import edu.xawl.common.entity.BaseBean;
import edu.xawl.common.entity.PageBean;

public interface BaseDao {
	
	/**
	 * 增 改
	 * @param t
	 * @return
	 */
	public <T extends BaseBean> Integer merge(T t);
	
	/**
	 * 真删除，service提供一个假删除
	 * @param seri
	 * @return
	 */
	public Integer delete(Class clazz,String seri);

	/**
	 * 执行sql
	 * @param hql
	 * @param args
	 * @return
	 */
	public <T> List<T> findByHql(String hql,Object ... args);
	
	/**
	 * 按照id查询对象
	 * @param clazz
	 * @param seri
	 * @return
	 */
	public <T> List<T> find(Class clazz,String seri);
	
	/**
	 * 查询分页数据
	 * @param pb  pageBean对象
	 * @param hql   pageBean中数据来源的hql
	 * @param className  pageBean泛型的类名称
	 * @param args  hql中的参数
	 * @return   返回pageBean
	 */
	public<T> PageBean<T> findByPageQuery(PageBean<T> pb,String className,String hql,Object ...obj);
}
