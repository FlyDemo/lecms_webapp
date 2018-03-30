package edu.xawl.material.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;
import edu.xawl.material.dao.MaterialDao;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.service.MaterialService;
@Service
public class MaterialServiceImpl implements MaterialService {

	@Resource
	private MaterialDao materialDao;
	
	@Resource
	private CommonService commonService;

	@Override
	public PageBean<MaterialBean> findMaterialDataByCode(PageBean<MaterialBean> pb) {
		/**
		 * 先将数据按照名称分组查询出来，然后设置数量
		 */
		//列表实现器材名称.器材说明.器材分类.器材价格.生产厂商.保修期.器材总量.器材剩余量.操作
		/*PageBean<MaterialBean> materialPageData = commonService.findByPageQuery(pb, " select m.materialName,m.materialDesc,m.materialCategory,m.price,m.materialCreator,m.materialRepairTime from MaterialBean m where m.deleted=? group by m.materialName,m.materialDesc,m.materialCategory,m.price,m.materialCreator,m.materialRepairTime order by m.categorySortNum,m.modifyTime ", "MaterialBean",false);
		List<MaterialBean> rowDatas = materialPageData.getRowDatas();
		for (MaterialBean materialBean : rowDatas) {
			Integer totalMaterialCount = materialDao.findCountByMaterialName(materialBean.getMaterialName());
			Integer surplus = materialDao.findSurplusByName(materialBean.getMaterialName());
			materialBean.setTotal(totalMaterialCount);
			materialBean.setSurplus(surplus);
		}*/
		
		PageBean<MaterialBean> materialPageData = commonService.findByPageQuery(pb, " select m.materialName,m.materialDesc,m.materialCategory,m.price,m.materialCreator,m.materialRepairTime from MaterialBean m where m.deleted=? order by m.categorySortNum,m.modifyTime ", "MaterialBean",false);
		List<MaterialBean> rowDatas = materialPageData.getRowDatas();
		for (MaterialBean materialBean : rowDatas) {
			Integer totalMaterialCount = materialDao.findCountByMaterialName(materialBean.getMaterialName());
			Integer surplus = materialDao.findSurplusByName(materialBean.getMaterialName());
			materialBean.setTotal(totalMaterialCount);
			materialBean.setSurplus(surplus);
		}
		return materialPageData;
	}
}
