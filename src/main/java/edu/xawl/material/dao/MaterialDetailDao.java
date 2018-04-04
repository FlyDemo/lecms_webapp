package edu.xawl.material.dao;

import java.util.List;

import edu.xawl.material.entity.MaterialDetailBean;

public interface MaterialDetailDao {

	public List<MaterialDetailBean> findNomalMaterialDetailByMaterialId(String materialId);

}
