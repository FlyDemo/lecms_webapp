package edu.xawl.material.service;

import edu.xawl.common.entity.PageBean;
import edu.xawl.material.entity.MaterialBean;

public interface MaterialService {
	public PageBean<MaterialBean> findMaterialDataByCode(PageBean<MaterialBean> pb);

	/**
	 * 保存MaterialDetailBean数据  需要按照数量   以及规则生成数据
	 * @param num
	 * @param materialBean
	 */
	public void saveMaterialDetailBean(String num, MaterialBean materialBean);

	public MaterialBean findMaterialById(String materialId);
}
