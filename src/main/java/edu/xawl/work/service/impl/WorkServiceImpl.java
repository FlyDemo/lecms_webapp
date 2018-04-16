package edu.xawl.work.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.xawl.common.dao.BaseDao;
import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;
import edu.xawl.material.entity.BorrowFlow;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.entity.MaterialDetailBean;
import edu.xawl.material.entity.MaterialDetailFlowBean;
import edu.xawl.material.enums.BorrowFlowStatus;
import edu.xawl.material.enums.MaterialDetailOp;
import edu.xawl.material.enums.MaterialStatus;
import edu.xawl.work.dao.WorkDao;
import edu.xawl.work.entity.NewsBean;
import edu.xawl.work.service.WorkService;

@Service
public class WorkServiceImpl implements WorkService {

	@Resource
	private WorkDao workDao;

	@Resource
	private BaseDao baseDao;
	
	@Resource
	private CommonService commonService;
	
	public PageBean findNewsList(PageBean<NewsBean> pageBean) {
		return workDao.findNewsList(pageBean);
	}

	@Override
	public PageBean<BorrowFlow> findDealteBorrowList() {
		return workDao.findBorrowFlowByStatus(BorrowFlowStatus.BORROW);
	}
	
	@Override
	public PageBean<BorrowFlow> findDealteBackList() {
		return workDao.findBorrowFlowByStatus(BorrowFlowStatus.BACK);
	}

	@Override
	public void dealte(BorrowFlow borrowFlow,String reviewContent, String materials, String op) {
		
		String[] split = materials.split(",");
		
		MaterialBean material = borrowFlow.getMaterial();
		
		if(op.equalsIgnoreCase("agree")){//同意
			int illegal = 0;
			/**
			 * 从flow中拿出器材，判断器材状态是否为正常，如果正常，那么需要把标记为借出的改为正常
			 * 操作表中存入一条记录
			 * 触发一个job
			 * 改变flow的状态
			 */
			
			for (int i=0;i<split.length;i++) {
				MaterialDetailBean md = (MaterialDetailBean) baseDao.findByHql(" from MaterialDetailBean md where md.materialCode=? ", split[i]).get(0);				
				if(!md.getStatus().equals(MaterialStatus.REVIEW)){
					illegal++;
				}
				md.setStatus(MaterialStatus.USED);
				md.setUsedNum(md.getUsedNum()+1);
				baseDao.merge(md);
				MaterialDetailFlowBean materialDetailFlow = new MaterialDetailFlowBean();
				materialDetailFlow.setBorrowFlow(borrowFlow);
				materialDetailFlow.setOper(borrowFlow.getBorrower());
				materialDetailFlow.setOp(MaterialDetailOp.BORROW);
				materialDetailFlow.setOpMaterial(md);
				baseDao.merge(materialDetailFlow);
			}
			
			if(illegal>0){
				List<MaterialDetailBean> mls = baseDao.findByHql(" from MaterialDetailBean md where md.status=? and md.material=? ", MaterialStatus.REVIEW,material);
				if(mls.size()>0){
					for (int i = 0; i < illegal; i++) {
						MaterialDetailBean materialDetailBean = mls.get(i);
						materialDetailBean.setStatus(MaterialStatus.NOMAL);
						baseDao.merge(materialDetailBean);
					}
				}
			}
			
			long current = new Date().getTime();
			long endTime = current+material.getFreeUseTime()*24*60*60*1000;
			borrowFlow.setGoBackTime(new Date(endTime));
			borrowFlow.setBorrowStatus(BorrowFlowStatus.CONSENT);
			baseDao.merge(borrowFlow);
			
			//此处触发一个job去通知
			
		}else{//拒绝
			/**
			 * 从flow中拿出器材，然后，恢复为正常
			 * 触发一个job
			 * 改变flow的状态
			 */
			List<MaterialDetailBean> materialDetails =  baseDao.findByHql(" from MaterialDetailBean md where md.material=? and md.status=?  order by md.status desc ",material,MaterialStatus.REVIEW);
			for (int i = 0; i < borrowFlow.getNum(); i++) {
				MaterialDetailBean materialDetailBean = materialDetails.get(i);
				materialDetailBean.setStatus(MaterialStatus.NOMAL);
				baseDao.merge(materialDetailBean);
			}
			
			borrowFlow.setBorrowStatus(BorrowFlowStatus.REFUSAL);
			baseDao.merge(borrowFlow);
			//触发一个job通知
		}
	}
	
	@Override
	public void dealteBack(String id) {
		BorrowFlow borrowFlow = (BorrowFlow) commonService.findById(BorrowFlow.class, id);
		String hql = " from MaterialDetailFlowBean mdf where mdf.borrowFlow=? ";
		List<MaterialDetailFlowBean> materialFlows = commonService.findByHql(hql, borrowFlow);
		for (MaterialDetailFlowBean materialDetailFlowBean : materialFlows) {
			MaterialDetailBean opMaterial = materialDetailFlowBean.getOpMaterial();
			opMaterial.setStatus(MaterialStatus.NOMAL);
			commonService.merge(opMaterial);
			
			MaterialDetailFlowBean mdfb = new MaterialDetailFlowBean();
			mdfb.setOp(MaterialDetailOp.BACK);
			mdfb.setOper(borrowFlow.getBorrower());
			mdfb.setOpMaterial(opMaterial);
			commonService.merge(mdfb);
		}
		borrowFlow.setBorrowStatus(BorrowFlowStatus.BACKED);
		commonService.merge(borrowFlow);
	}
	
	@Override
	public void bad(String badCode,String badContext) {
		String[] split = badCode.split(",");
		String[] split2 = badContext.split(",");
		if(split.length>1){
			for (int i = 1; i < split.length; i++) {
				String hql = " from MaterialDetailBean md where md.materialCode=? ";
				List<MaterialDetailBean> findByHql = commonService.findByHql(hql, split[i]);
				MaterialDetailBean m = findByHql.get(0);
				m.setStatus(MaterialStatus.BAD);
				m.setBadContext(split2[i-1]);
				if(m.getBadNum()==null||"".equals(m.getBadNum())){
					m.setBadNum(1);
				}else{
					m.setBadNum(m.getBadNum()+1);
				} 
				commonService.merge(m);
			}
		}
	}
	
	@Override
	public PageBean<MaterialDetailBean> badMaterialDetailList(PageBean<MaterialDetailBean> pb) {
		String hql = " from MaterialDetailBean md where md.status=? ";
		PageBean<MaterialDetailBean> findByPageQuery = commonService.findByPageQuery(pb, hql, "MaterialDetailBean", MaterialStatus.BAD);
		return findByPageQuery;
	}
}
