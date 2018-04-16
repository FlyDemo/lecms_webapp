package edu.xawl.us.service.impl;

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
import edu.xawl.material.enums.MaterialStatus;
import edu.xawl.us.dao.UserDao;
import edu.xawl.us.entity.UserBean;
import edu.xawl.us.enums.UserLeval;
import edu.xawl.us.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Resource
	private CommonService commonService;
	
	@Resource
	private BaseDao baseDao;
	
	public UserBean login(String userName, String passWord) {
		UserBean user = userDao.findUserByLoginName(userName);
		if(user!=null){
			if(passWord.equals(user.getPassWord())) 
			return user;
		}
		return null;
	}

	@Override
	public PageBean<UserBean> findAllUserByLeval(PageBean<UserBean> pb,String role) {
		//获取页面传递过来的角色，要是角色为空或者不是有效角色，直接返回null
		if(!"".equals(role)){
			try{
				UserLeval leval = Enum.valueOf(UserLeval.class, role);
				PageBean<UserBean> allUsers = commonService.findByPageQuery(pb, " from UserBean u where u.leval=? ", " UserBean ", leval);
				return allUsers;
			}catch(Exception e){
				
			}
		}
		return null;
		
	}

	@Override
	public boolean checkLoginName(String loginName) {
		
		List<Object> findByHql = baseDao.findByHql(" from UserBean where loginName = ? ", loginName);
		if(findByHql.size()==0){
			return true;
		}
		return false;
	}

	@Override
	public PageBean<BorrowFlow> findMyBorrow(UserBean currentUser) {
		return userDao.findBorrowFlowWithStatus(currentUser,BorrowFlowStatus.BORROW);
	}
	
	@Override
	public void cansMyBorrow(String id) {
		BorrowFlow borrowFlow = (BorrowFlow) commonService.findById(BorrowFlow.class, id);
		MaterialBean material = borrowFlow.getMaterial();
		List<MaterialDetailBean> materialDetail = userDao.findMaterialDetailByMaterialAndReviewStatus(material);
		for(int i=0;i<borrowFlow.getNum();i++){
			MaterialDetailBean materialDetailBean = materialDetail.get(i);
			materialDetailBean.setStatus(MaterialStatus.NOMAL);
			commonService.merge(materialDetailBean);
		}
		
		borrowFlow.setBorrowStatus(BorrowFlowStatus.DELETED);
		commonService.merge(borrowFlow);
	}

	@Override
	public PageBean<BorrowFlow> findMyNoBack(UserBean currentUser) {
		PageBean<BorrowFlow> findBorrowFlowWithStatus = userDao.findBorrowFlowWithStatus(currentUser, BorrowFlowStatus.CONSENT);
		List<BorrowFlow> rowDatas = findBorrowFlowWithStatus.getRowDatas();
		for (BorrowFlow borrowFlow : rowDatas) {
			List<MaterialDetailFlowBean> findByHql = baseDao.findByHql(" from MaterialDetailFlowBean mdf where mdf.borrowFlow=? ", borrowFlow);
			borrowFlow.setDetails(findByHql);
		}
		return  findBorrowFlowWithStatus;
	}
	
	@Override
	public void back(BorrowFlow borrowFlow) {
		BorrowFlow bf = (BorrowFlow) commonService.findById(BorrowFlow.class, borrowFlow.getId());
		bf.setBorrowStatus(BorrowFlowStatus.BACK);
		baseDao.merge(bf);
	}
	
	@Override
	public void cansBack(BorrowFlow borrowFlow) {
		BorrowFlow bf = (BorrowFlow) commonService.findById(BorrowFlow.class, borrowFlow.getId());
		bf.setBorrowStatus(BorrowFlowStatus.CONSENT);
		baseDao.merge(bf);
	}

	@Override
	public PageBean<BorrowFlow> findMyGoBack(UserBean currentUser) {
		String hql = " from BorrowFlow bf where bf.borrower=? and bf.borrowStatus=? order by bf.modifyTime desc";
		return commonService.findByPageQuery(new PageBean<BorrowFlow>(), hql, "BorrowFlow", currentUser,BorrowFlowStatus.BACKED);
	}
	
	@Override
	public PageBean<BorrowFlow> findOverTimeList(UserBean currentUser) {
		return userDao.findOverTimeList(currentUser);
	}
}
