package edu.xawl.material.service;

import java.util.List;

import edu.xawl.common.entity.PageBean;
import edu.xawl.material.entity.BorrowFlow;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.entity.MaterialCategoryBean;
import edu.xawl.material.entity.MaterialDetailBean;
import edu.xawl.us.entity.UserBean;

public interface MaterialService {
	public PageBean<MaterialBean> findMaterialDataByCode(PageBean<MaterialBean> pb);

	/**
	 * 保存MaterialDetailBean数据  需要按照数量   以及规则生成数据
	 * @param num
	 * @param materialBean
	 */
	public void saveMaterialDetailBean(String num, MaterialBean materialBean);

	public MaterialBean findMaterialById(String materialId);

	public PageBean<MaterialBean> findMaterialDataByCategory(PageBean<MaterialBean> pb,MaterialCategoryBean materialCategory);

	public boolean borrowMateerial(List<MaterialDetailBean> materialDetail,String num, UserBean borrower);

	public boolean borrowMaterial(BorrowFlow borrowFlow);
}
