package edu.xawl.material.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;
import edu.xawl.material.entity.MaterialCategoryBean;

@Controller
@RequestMapping("/MaterialCategoryController")
public class MaterialCategoryController {

	@Resource
	private CommonService commonService;
	/**
	 * 
	 * @param pb
	 * @param model
	 * @return
	 */
	@RequestMapping("/findMaterialCategoryData")
	public String findMaterialCategoryData(PageBean<MaterialCategoryBean> pb,Model model){
		PageBean<MaterialCategoryBean> materialCategory = commonService.findByPageQuery(pb, " from MaterialCategoryBean m where m.deleted=? order by m.categorySortNum,m.modifyTime ", "MaterialCategoryBean",false);
		model.addAttribute("pageBean", materialCategory);
		return "/category/categoryList";
	}

	
	/**
	 * 查看，新增，修改方法
	 * @param categoryBean  确定是哪个分类
	 * @param op       操作类型  修改还是创建
	 * @param model   
	 * @return
	 */
	@RequestMapping("/editCategory")
	public String editCategory(HttpServletRequest request,MaterialCategoryBean categoryBean,String op,Model model){
		if(op.equals("delete")){//删除
			MaterialCategoryBean dbCategory = (MaterialCategoryBean) commonService.findById(MaterialCategoryBean.class, categoryBean.getId());
			dbCategory.setDeleted(true);
			commonService.merge(dbCategory);
			
			ServletContext application = request.getSession().getServletContext();
			List<MaterialCategoryBean> category = (List<MaterialCategoryBean>) application.getAttribute("materialCategory");
			if(category==null){
				category = commonService.findByHql("  from MaterialCategoryBean m where m.deleted=? order by m.categorySortNum,m.modifyTime  ", false);
				application.setAttribute("materialCategory", category);
			}else{
				MaterialCategoryBean delete = null;
				for (MaterialCategoryBean materialCategoryBean : category) {
					if(materialCategoryBean.getId().equalsIgnoreCase(dbCategory.getId())){
						delete = materialCategoryBean;
					}
				}
				category.remove(delete);
			}
			
			PageBean<MaterialCategoryBean> materialCategory = commonService.findByPageQuery(new PageBean<MaterialCategoryBean>(), " from MaterialCategoryBean m where m.deleted=? order by m.categorySortNum,m.modifyTime ", "MaterialCategoryBean",false);
			model.addAttribute("pageBean", materialCategory);
			return "/category/categoryList";
		}else if(op.equals("view")||op.equals("edit")){//查看 编辑
			categoryBean = (MaterialCategoryBean) commonService.findById(MaterialCategoryBean.class, categoryBean.getId());
			model.addAttribute("op", op);
			model.addAttribute("categoryBean", categoryBean);
		}
		return "/category/editCategory";
	}
	
	
	@ResponseBody
	@RequestMapping("/checkCategoryCodeAndName")
	public boolean checkCategoryCodeAndName(HttpServletRequest request,String id,String currentCategoryName,String currentCategoryCode){
		//判断application中是否存在导航，如果存在，直接比较，如果不存在，就查出来放进application中，然后去比较
		//需要注意的是，当添加，删除修改了之后，都需要去更新application中的数据
		
		//判断校验的是name还是code
		String validObj = currentCategoryName==null?"code":"name";
		
		ServletContext application = request.getSession().getServletContext();
		List<MaterialCategoryBean> category = (List<MaterialCategoryBean>) application.getAttribute("materialCategory");
		if(category==null){
			category = commonService.findByHql("  from MaterialCategoryBean m where m.deleted=? order by m.categorySortNum,m.modifyTime  ", false);
			application.setAttribute("materialCategory", category);
		}
		
		if(category.size()>0){
			
			//判断是新建还是编辑
			if(id.trim()!=null&&!id.trim().equals("")){//编辑
				for (MaterialCategoryBean materialCategoryBean : category) {
					if(validObj.equals("name")){//name
						if(currentCategoryName.equals(materialCategoryBean.getCategoryName())){
							if(!materialCategoryBean.getId().equals(id)){
								return true;
							}
						}
					}else{//code
						if(currentCategoryCode.equals(materialCategoryBean.getCategoryCode())){
							if(!materialCategoryBean.getId().equals(id)){
								return true;
							}
						}
					}
				}
			}else{//新建
				for (MaterialCategoryBean materialCategoryBean : category) {
					if(validObj.equals("name")){//name
						if(currentCategoryName.equals(materialCategoryBean.getCategoryName())){
							return true;
						}
					}else{//code
						if(currentCategoryCode.equals(materialCategoryBean.getCategoryCode())){
							return true;
						}
					}
				}
			}
			
		}
		return false;
	}
	
	
	@RequestMapping("/saveMaterialCategory")
	public String saveMaterialCategory(HttpServletRequest request,MaterialCategoryBean categoryBean){
		commonService.merge(categoryBean);
		
		ServletContext application = request.getSession().getServletContext();
		List<MaterialCategoryBean> category = (List<MaterialCategoryBean>) application.getAttribute("materialCategory");
		if(category==null){
			category = commonService.findByHql("  from MaterialCategoryBean m where m.deleted=? order by m.categorySortNum,m.modifyTime  ", false);
			application.setAttribute("materialCategory", category);
		}else{
			category.add(categoryBean);
		}
		
		return "/category/categoryList";
	}
	
}
