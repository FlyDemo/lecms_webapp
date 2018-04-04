package edu.xawl.common.service;

import java.util.List;

import edu.xawl.common.entity.BaseBean;
import edu.xawl.common.entity.PageBean;


/**
 * 封装一个公共Service，提供最基础的一些增删改查。
 * @author Administrator
 *
 */
public interface CommonService {

	/**
	 * 新增或者修改
	 * @param obj
	 */
	public<T extends BaseBean> Integer merge(T obj);
	
	/**
	 * 查询
	 * @param clazz
	 * @param serilaz
	 * @return
	 */
	public<T> Object findById(Class<T> clazz,String serilaz);
	
	
	/**
	 * 执行sql
	 * @param hql
	 * @param args
	 * @return
	 */
	public <T> List<T> findByHql(String hql,Object ... args);
	
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
