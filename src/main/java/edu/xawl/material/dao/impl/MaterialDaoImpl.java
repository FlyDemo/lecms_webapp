package edu.xawl.material.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.xawl.common.dao.BaseDao;
import edu.xawl.common.entity.PageBean;
import edu.xawl.material.dao.MaterialDao;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.entity.MaterialCategoryBean;
import edu.xawl.material.enums.MaterialStatus;
@Repository
public class MaterialDaoImpl implements MaterialDao {

	@Resource
	private BaseDao baseDao;

	@Override
	public Integer findCountByMaterialName(String materialName,MaterialCategoryBean category) {
		String hql = " select count(md) from MaterialDetailBean md,MaterialBean m where md.material=m and m.materialName=? and m.materialCategory=? and md.status!=? ";
		List<Integer> findByHql = baseDao.findByHql(hql, materialName,category,MaterialStatus.DELETED);
		return findByHql.size()>0?Integer.valueOf(String.valueOf(findByHql.get(0))):0;
	}

	@Override
	public Integer findSurplusByName(String materialName,MaterialCategoryBean category) {
		String hql = " select count(md) from MaterialDetailBean md,MaterialBean m where md.material=m and m.materialName=? and m.materialCategory=? and md.status=? ";
		List<Integer> findByHql = baseDao.findByHql(hql, materialName,category,MaterialStatus.NOMAL);
		return findByHql.size()>0?Integer.valueOf(String.valueOf(findByHql.get(0))):0;
	}

	@Override
	public Integer findBadNumByName(String materialName,MaterialCategoryBean category) {
		String hql = " select count(md) from MaterialDetailBean md,MaterialBean m where md.material=m and m.materialName=? and m.materialCategory=? and md.status=? ";
		List<Integer> findByHql = baseDao.findByHql(hql, materialName,category,MaterialStatus.BAD);
		return findByHql.size()>0?Integer.valueOf(String.valueOf(findByHql.get(0))):0;
	}
	
}
