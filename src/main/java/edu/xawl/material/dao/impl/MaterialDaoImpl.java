package edu.xawl.material.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.xawl.common.dao.BaseDao;
import edu.xawl.common.entity.PageBean;
import edu.xawl.material.dao.MaterialDao;
import edu.xawl.material.entity.MaterialBean;
@Repository
public class MaterialDaoImpl implements MaterialDao {

	@Resource
	private BaseDao baseDao;

	@Override
	public Integer findCountByMaterialName(String materialName) {
		String hql = " select count(md) from MaterialDetailBean md where md.materialName=? and md.deleted=? ";
		List<Integer> findByHql = baseDao.findByHql(hql, materialName,false);
		return findByHql.size()>0?findByHql.get(0):0;
	}

	@Override
	public Integer findSurplusByName(String materialName) {
		String hql = " select count(md) from MaterialDetailBean md where md.materialName=? and md.deleted=? and md.usered=?";
		List<Integer> findByHql = baseDao.findByHql(hql, materialName,false,false);
		return findByHql.size()>0?findByHql.get(0):0;
	}
	
}
