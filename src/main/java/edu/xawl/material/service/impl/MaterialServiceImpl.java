package edu.xawl.material.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;
import edu.xawl.material.dao.MaterialDao;
import edu.xawl.material.dao.MaterialDetailDao;
import edu.xawl.material.entity.BorrowFlow;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.entity.MaterialCategoryBean;
import edu.xawl.material.entity.MaterialDetailBean;
import edu.xawl.material.enums.BorrowFlowStatus;
import edu.xawl.material.enums.MaterialStatus;
import edu.xawl.material.service.MaterialService;
import edu.xawl.us.dao.UserDao;
import edu.xawl.us.entity.UserBean;
@Service
public class MaterialServiceImpl implements MaterialService {

	@Resource
	private MaterialDao materialDao;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private MaterialDetailDao materialDetailDao;

	@Resource
	private UserDao userDao;
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
		
		PageBean<MaterialBean> materialPageData = commonService.findByPageQuery(pb, " from MaterialBean m where m.deleted=? order by m.materialCategory.categorySortNum,m.modifyTime ", "MaterialBean",false);
		List<MaterialBean> rowDatas = materialPageData.getRowDatas();
		for (MaterialBean materialBean : rowDatas) {
			Integer totalMaterialCount = materialDao.findCountByMaterialName(materialBean.getMaterialName(),materialBean.getMaterialCategory());
			Integer surplus = materialDao.findSurplusByName(materialBean.getMaterialName(),materialBean.getMaterialCategory());
			Integer badNum = materialDao.findBadNumByName(materialBean.getMaterialName(),materialBean.getMaterialCategory());
			materialBean.setTotal(totalMaterialCount);
			materialBean.setSurPlus(surplus);
			materialBean.setBadNum(badNum);
		}
		return materialPageData;
	}


	@Override
	public PageBean<MaterialBean> findMaterialDataByCategory(PageBean<MaterialBean> pb,MaterialCategoryBean materialCategory) {
		pb = commonService.findByPageQuery(pb, " from MaterialBean m where m.deleted=? and m.materialCategory=? order by m.modifyTime,m.createTime ", "MaterialBean", false,materialCategory);
		List<MaterialBean> rowDatas = pb.getRowDatas();
		for (MaterialBean materialBean : rowDatas) {
			Integer totalMaterialCount = materialDao.findCountByMaterialName(materialBean.getMaterialName(),materialBean.getMaterialCategory());
			Integer surplus = materialDao.findSurplusByName(materialBean.getMaterialName(),materialBean.getMaterialCategory());
			Integer badNum = materialDao.findBadNumByName(materialBean.getMaterialName(),materialBean.getMaterialCategory());
			materialBean.setTotal(totalMaterialCount);
			materialBean.setSurPlus(surplus);
			materialBean.setBadNum(badNum);
		}
		return pb;
	}
	
	@Override
	public void saveMaterialDetailBean(String num, MaterialBean materialBean) {
		Integer materialDetailNum = Integer.valueOf(num);
		if(materialDetailNum!=0){
			for (int i = 0; i < materialDetailNum; i++) {
				MaterialDetailBean materialDetailBean = new MaterialDetailBean(materialBean.getMaterialCategory().getCategoryCode()+System.currentTimeMillis(), materialBean, MaterialStatus.NOMAL);
				materialDetailBean.setUsedNum(0);
				commonService.merge(materialDetailBean);
			}
		}else{
			System.out.println("只有器材名称，没有对应的器材生成。。");
		}
	}

	@Override
	public MaterialBean findMaterialById(String materialId) {
		String hql = " from MaterialBean m where m.id=? and m.deleted=? ";
		List<MaterialBean> materialBeanList = commonService.findByHql(hql,materialId,false);
		MaterialBean materialBean = null;
		if(materialBeanList.size()>0){
			materialBean = materialBeanList.get(0);
			Integer totalMaterialCount = commonService.findByHql(" from MaterialDetailBean md,MaterialBean m where md.material=m and m.id=? and md.status!=? ",materialId,MaterialStatus.DELETED).size();
			Integer surplus = commonService.findByHql(" from MaterialDetailBean md,MaterialBean m where md.material=m and m.id=? and md.status=? ",materialId,MaterialStatus.NOMAL).size();
			Integer badNum = commonService.findByHql(" from MaterialDetailBean md,MaterialBean m where md.material=m and m.id=? and md.status=? ",materialId,MaterialStatus.BAD).size();
			materialBean.setTotal(totalMaterialCount);
			materialBean.setSurPlus(surplus);
			materialBean.setBadNum(badNum);
		}
		return materialBean;
	}


	/**
	 * BORROW
	 */
	@Override
	public boolean borrowMateerial(List<MaterialDetailBean> materialDetail,
			String num,UserBean borrower) {
		BorrowFlow bf = new BorrowFlow();
		return false;
	}


	@Override
	public boolean borrowMaterial(BorrowFlow borrowFlow) {
		String materialId = borrowFlow.getMaterial().getId();
		List<MaterialDetailBean> materialDetailList = materialDetailDao.findNomalMaterialDetailByMaterialId(materialId);
		
		if(borrowFlow.getNum()>materialDetailList.size()){
			return false;
		}else{
			UserBean reviewer = userDao.findUserByLoginName("admin");
			borrowFlow.setReviewer(reviewer);
			borrowFlow.setCurrentPeople(reviewer);
			borrowFlow.setBorrowStatus(BorrowFlowStatus.BORROW);
			
			synchronized (reviewer) {
				for (int i=0;i<borrowFlow.getNum();i++) {
					MaterialDetailBean m = materialDetailList.get(i);
					m.setStatus(MaterialStatus.REVIEW);
					commonService.merge(m);
				}
			}
			
			commonService.merge(borrowFlow);
		}
		return true;
	}
}
