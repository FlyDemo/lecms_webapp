package edu.xawl.material.service;

import edu.xawl.common.entity.PageBean;
import edu.xawl.material.entity.MaterialBean;

public interface MaterialService {
	public PageBean<MaterialBean> findMaterialDataByCode(PageBean<MaterialBean> pb);
}
