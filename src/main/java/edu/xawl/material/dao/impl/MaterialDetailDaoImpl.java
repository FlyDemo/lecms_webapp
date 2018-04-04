package edu.xawl.material.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import edu.xawl.common.dao.BaseDao;
import edu.xawl.material.dao.MaterialDetailDao;
import edu.xawl.material.entity.MaterialDetailBean;
import edu.xawl.material.enums.MaterialStatus;
@Repository
public class MaterialDetailDaoImpl implements MaterialDetailDao {

	@Resource
	private BaseDao baseDao;

	@Override
	public List<MaterialDetailBean> findNomalMaterialDetailByMaterialId(
			String materialId) {
		String hql = " from MaterialDetailBean md where md.material.id=? and md.status=? ";
		return baseDao.findByHql(hql, materialId,MaterialStatus.NOMAL);
	}
}
