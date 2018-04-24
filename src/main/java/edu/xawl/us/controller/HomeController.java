package edu.xawl.us.controller;

import java.util.List;



import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import edu.xawl.common.service.CommonService;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.enums.MaterialStatus;
import edu.xawl.work.entity.InstitutionBean;
import edu.xawl.work.entity.NewsBean;

@Controller
public class HomeController {

	@Resource
	private CommonService commonService;
	
	@RequestMapping(value="/home")
	public String test(Model model){
		String hql = " from InstitutionBean ib where ib.deleted=? order by id.modifyTime desc ";
		List<InstitutionBean> findByHql = commonService.findByHql(hql, false);
		List<InstitutionBean> subList = null;
		if(findByHql.size()>7){
			subList = findByHql.subList(0, 7);
		}else{
			subList = findByHql;
		}
		
		model.addAttribute("institutionList", subList);
		
		String hql1 = " from NewsBean n where n.deleted=? order by n.topShow desc,n.newsTime desc ";
		List<NewsBean> newsList = commonService.findByHql(hql1, false);
		List<NewsBean> subList1 = null;
		if(newsList.size()>9){
			subList1 = newsList.subList(0, 9);
		}else{
			subList1 = newsList;
		}
		
		model.addAttribute("newsList", subList1);
		
		String hql2 = " from MaterialBean mb where mb.deleted=? order by mb.modifyTime desc ";
		List<MaterialBean> findByHql2 = commonService.findByHql(hql2, false);
		List<MaterialBean> subList2 = null;
		if(findByHql2.size()>12){
			subList2 = findByHql2.subList(0, 12);
		}else{
			subList2 = findByHql2;
		}
		
		model.addAttribute("materialAll",findByHql2);
		model.addAttribute("material", subList2);
		
		return "/index/default";
	}
	
	@RequestMapping("/indexNews")
	public String indexNews(NewsBean newsBean,Model model){
		newsBean = (NewsBean) commonService.findById(NewsBean.class, newsBean.getId());
		model.addAttribute("bean", newsBean);
		model.addAttribute("flag", "news");
		return "/index/viewPage";
	}
	
	@RequestMapping("/indexInstitution")
	public String indexInstitution(InstitutionBean it,Model model){
		it = (InstitutionBean) commonService.findById(InstitutionBean.class, it.getId());
		model.addAttribute("bean", it);
		model.addAttribute("flag", "it");
		return "/index/viewPage";
	}
	
	/**
	 * 使用次数   / 可使用次数
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/indexConsume")
	public Object indexConsume(String id){
		JSONObject obj = new JSONObject();
		MaterialBean material = (MaterialBean) commonService.findById(MaterialBean.class, id);
		String hql = " select SUM(mdb.usedNum) from MaterialDetailBean mdb where mdb.material=? and mdb.status!=? ";
		List<Integer> materialDetails = commonService.findByHql(hql, material,MaterialStatus.DELETED);
		obj.put("canUse", material.getUseNum());
		obj.put("used", materialDetails.get(0));
		return obj.toString();
	}
	
	
	@RequestMapping("/noDataList")
	public String noDataList(){
		return "/200";
	}
}
