package edu.xawl.material.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.service.MaterialService;

@Controller
@RequestMapping("/MaterialController")
public class MaterialController {

	@Resource
	private CommonService commonService;
	
	@Resource 
	private MaterialService materialService;
	
	/**
	 *  查看器材列表，列表实现器材名称.器材说明.器材分类.器材加个.器材总量.器材剩余量.操作
	 * @param pb
	 * @param categoryId
	 * @param model
	 * @return
	 */
	@RequestMapping("/findMaterialDataByCode")
	public String findMaterialDataByCode(PageBean<MaterialBean> pb,Model model){
		pb = materialService.findMaterialDataByCode(pb);
		model.addAttribute("pageBean", pb);
		return "/category/materialList";
	}
}
