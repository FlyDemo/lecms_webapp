package edu.xawl.material.dao;

import edu.xawl.material.entity.MaterialCategoryBean;

public interface MaterialDao {

	public Integer findCountByMaterialName(String materialName,MaterialCategoryBean category);

	public Integer findSurplusByName(String materialName,MaterialCategoryBean category);

	public Integer findBadNumByName(String materialName,MaterialCategoryBean category);


}
