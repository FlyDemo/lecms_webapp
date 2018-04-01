package edu.xawl.material.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.entity.MaterialDetailBean;
import edu.xawl.material.enums.MaterialStatus;
import edu.xawl.material.service.MaterialDetailService;

@Controller
@RequestMapping("/MaterialDetailController")
public class MaterialDetailController {

	@Resource
	private CommonService commonService;
	
	@Resource
	private MaterialDetailService materialDetailService;
	
	private Logger logger = LoggerFactory.getLogger(MaterialDetailController.class);
	
	@RequestMapping("/editMaterialDetil")
	public String editMaterialDetil(MaterialDetailBean materialDetail,String materialId,String op,HttpServletRequest request,HttpServletResponse response,Model model){
		if("create".equalsIgnoreCase(op)){//添加，带出此器材相关信息，让用户确认是否操作
			MaterialBean material = (MaterialBean) commonService.findById(MaterialBean.class, materialId);
			materialDetail.setMaterial(material);
			materialDetail.setMaterialCode(material.getMaterialCategory().getCategoryCode()+System.currentTimeMillis());
			materialDetail.setStatus(MaterialStatus.NOMAL);
			
			model.addAttribute("materialDetail", materialDetail);
			model.addAttribute("materialBean", material);
			model.addAttribute("op", op);
		}else if("view".equalsIgnoreCase(op)){//查看，带出相关信息给用户看即可
			MaterialBean material = (MaterialBean) commonService.findById(MaterialBean.class, materialId);
			MaterialDetailBean md = (MaterialDetailBean) commonService.findById(MaterialDetailBean.class, materialDetail.getId());
			model.addAttribute("materialDetail", md);
			model.addAttribute("materialBean", material);
			model.addAttribute("op", op);
		}else if("delete".equalsIgnoreCase(op)){//删除
			MaterialDetailBean md = (MaterialDetailBean) commonService.findById(MaterialDetailBean.class, materialDetail.getId());
			md.setStatus(MaterialStatus.DELETED);
			commonService.merge(md);
		}
		
		return "/category/showMaterialDetail";
	}
	
	
	@RequestMapping("/materialDetailDataList")
	public String materialDetailDataList(PageBean<MaterialDetailBean> pb,String materialId,Model model){
		MaterialBean materialBean = (MaterialBean) commonService.findById(MaterialBean.class, materialId);
		PageBean<MaterialDetailBean> materialDetails = commonService.findByPageQuery(pb, " from MaterialDetailBean md where md.material=? and md.status!=? ", "MaterialDetailBean", materialBean,MaterialStatus.DELETED);
		model.addAttribute("materialBean", materialBean);
		model.addAttribute("pageBean", materialDetails);
		return "/category/materialDetailList";
	}
	
	@ResponseBody
	@RequestMapping("/saveMaterialDetail")
	public boolean saveMaterialDetail(String materialId,String materialCode){
		try{
			MaterialBean material = (MaterialBean) commonService.findById(MaterialBean.class, materialId);
			MaterialDetailBean mdb = new MaterialDetailBean();
			mdb.setMaterialCode(materialCode);
			mdb.setMaterial(material);
			mdb.setStatus(MaterialStatus.NOMAL);
			commonService.merge(mdb);
		}catch(Exception e){
			logger.error("=========保存器材失败@==========="+e.getMessage());
			return false;
		}
		
		return true;
	}
	
}
