package edu.xawl.material.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.xawl.common.dao.BaseDao;
import edu.xawl.common.entity.PageBean;
import edu.xawl.material.dao.MaterialDao;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.enums.MaterialStatus;
@Repository
public class MaterialDaoImpl implements MaterialDao {

	@Resource
	private BaseDao baseDao;

	@Override
	public Integer findCountByMaterialName(String materialName) {
		String hql = " select count(md) from MaterialDetailBean md where md.Material.materialName=? and md.status!=? ";
		List<Integer> findByHql = baseDao.findByHql(hql, materialName,MaterialStatus.DELETED);
		return findByHql.size()>0?findByHql.get(0):0;
	}

	@Override
	public Integer findSurplusByName(String materialName) {
		String hql = " select count(md) from MaterialDetailBean md where md.Material.materialName=? and md.status=? ";
		List<Integer> findByHql = baseDao.findByHql(hql, materialName,MaterialStatus.NOMAL);
		return findByHql.size()>0?findByHql.get(0):0;
	}

	@Override
	public Integer findBadNumByName(String materialName) {
		String hql = " select count(md) from MaterialDetailBean md where md.Material.materialName=? and md.status=? ";
		List<Integer> findByHql = baseDao.findByHql(hql, materialName,MaterialStatus.BAD);
		return findByHql.size()>0?findByHql.get(0):0;
	}
	
}
