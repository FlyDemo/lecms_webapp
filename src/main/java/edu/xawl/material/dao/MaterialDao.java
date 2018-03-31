package edu.xawl.material.dao;

public interface MaterialDao {

	public Integer findCountByMaterialName(String materialName);

	public Integer findSurplusByName(String materialName);

	public Integer findBadNumByName(String materialName);


}
