package edu.xawl.material.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;
import edu.xawl.material.entity.BorrowFlow;
import edu.xawl.material.entity.MaterialBean;
import edu.xawl.material.entity.MaterialCategoryBean;
import edu.xawl.material.entity.MaterialDetailBean;
import edu.xawl.material.enums.MaterialStatus;
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
	@RequestMapping("/findMaterialDataByName")
	public String findMaterialDataByName(PageBean<MaterialBean> pb,Model model){
		pb = materialService.findMaterialDataByCode(pb);
		model.addAttribute("pageBean", pb);
		return "/ordinary/materialList";
	}
	
	
	@RequestMapping("/findMaterialDataByCategory")
	public String findMaterialDataByCategory(PageBean<MaterialBean>pb,String categoryId,Model model){
		MaterialCategoryBean materialCategory = (MaterialCategoryBean) commonService.findById(MaterialCategoryBean.class, categoryId);
		pb=materialService.findMaterialDataByCategory(pb,materialCategory);
		model.addAttribute("pageBean", pb);
		model.addAttribute("materialCategory", materialCategory);
		return "/ordinary/materialList";
	}
	
	/**
	 * ordinaryViewMaterial
	 * @param materialBean
	 * @param model
	 * @return
	 */
	@RequestMapping("/ordinaryViewMaterial")
	public String ordinaryViewMaterial(MaterialBean materialBean,Model model){
		materialBean = (MaterialBean) commonService.findById(MaterialBean.class, materialBean.getId());
		List<Object> findByHql = commonService.findByHql(" from MaterialDetailBean md where md.status=? and md.material=? ", MaterialStatus.NOMAL,materialBean);
		materialBean.setSurPlus(findByHql.size());
		String categoryId = materialBean.getMaterialCategory().getId();
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("materialBean", materialBean);
		return "/ordinary/viewMaterial";
	}
	
	@RequestMapping("/borrowMaterialPage")
	public String borrowMaterialPage(MaterialBean material,Model model){
		material = materialService.findMaterialById(material.getId());
		model.addAttribute("materialBean", material);
		return "/ordinary/borrowMaterial";
	}
	
	
	@ResponseBody
	@RequestMapping("/borrowMaterial")
	public boolean borrowMaterial(BorrowFlow borrowFlow,HttpServletRequest request){
		return materialService.borrowMaterial(borrowFlow);
	}
	
	/**
	 * borrowMaterial
	 * @param material
	 * @return
	 *//*
	@ResponseBody
	@RequestMapping("/borrowMaterial")
	public boolean borrowMaterial(MaterialBean material,String num,String borrowContent,HttpServletRequest request){
		Integer number = 0;
		UserBean borrower = (UserBean)request.getSession().getAttribute("user");
		try{
			number = Integer.valueOf(num);
		}catch(Exception e){
			System.out.println("===================borrow error==========================");
			System.out.println("caused by"+borrower.getName());
		}
		
		material = (MaterialBean) commonService.findById(MaterialBean.class, material.getId());
		List<MaterialDetailBean> materialDetail = commonService.findByHql(" from MaterialDetailBean md where md.status=? and md.material=? ", MaterialStatus.NOMAL,material);
		
		boolean flag = false;
		
		if(materialDetail.size()>=number){
			flag = materialService.borrowMateerial(materialDetail,num,borrower);
		}
		
		
		return flag;
	}*/
	
	/**
	 * 新建、编辑 器材
	 * @param materialBean
	 * @param op
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	@RequestMapping("/editMaterial")
	public String editMaterial(MaterialBean materialBean,String op,HttpServletRequest request,HttpServletResponse response,Model model) throws ServletException, IOException{
		if("create".equalsIgnoreCase(op)){//新建  什么也不用做  坐等保存
			
		}else if("view".equalsIgnoreCase(op)||"edit".equalsIgnoreCase(op)||"delete".equalsIgnoreCase(op)){//编辑   带出数据
			if("delete".equalsIgnoreCase(op)){//删除所有器材
				List<MaterialDetailBean> materialDetails = commonService.findByHql(" from MaterialDetailBean md where md.material=? and md.status!=? ", materialBean,MaterialStatus.DELETED);
				for (MaterialDetailBean materialDetailBean : materialDetails) {
					materialDetailBean.setStatus(MaterialStatus.DELETED);
					commonService.merge(materialDetailBean);
				}
				//删除掉这个名称
				MaterialBean material = (MaterialBean) commonService.findById(MaterialBean.class, materialBean.getId());
				material.setDeleted(true);
				commonService.merge(material);
				
				request.getRequestDispatcher("/MaterialController/findMaterialDataByName").forward(request, response);
			}
			materialBean = materialService.findMaterialById(materialBean.getId().trim());
			model.addAttribute("materialBean",materialBean);
		}else if("detail".equalsIgnoreCase(op)){//详情，带出此名称下的器材列表
			materialBean = (MaterialBean) commonService.findById(MaterialBean.class, materialBean.getId());
			PageBean<MaterialDetailBean> materialDetails = commonService.findByPageQuery(new PageBean<MaterialDetailBean>(), " from MaterialDetailBean md where md.material=? and md.status!=? ", "MaterialDetailBean", materialBean,MaterialStatus.DELETED);
			model.addAttribute("pageBean", materialDetails);
			model.addAttribute("materialBean", materialBean);
			model.addAttribute("op", op);
			return "/category/materialDetailList";
		}
		model.addAttribute("op", op);
		return "/category/editMaterial";
	}
	
	/**
	 * 保存器材  
	 * @param materialBean
	 * @param num
	 */
	@RequestMapping("/saveMaterial")
	public void saveMaterial(MaterialBean materialBean,String num,String op,HttpServletRequest request,HttpServletResponse response){
		/**
		 * 此方法思路如下：
		 * 	1.首先将器材的公共信息保存到MaterialBean中
		 * 	2.然后分析数量，根据数量，生成每一个器材的编号，给MaterialDetailBean中存入数据
		 * 	注意：生成器材编号规则（不能重复）：器材分类编号+当前时间戳
		 */
		
		if(null==num||"".equals(num.trim())){
			num = "0";
		}
		
		commonService.merge(materialBean);
		if("create".equalsIgnoreCase(op)){
			MaterialCategoryBean materialCategory = (MaterialCategoryBean) commonService.findById(MaterialCategoryBean.class, materialBean.getMaterialCategory().getId());
			materialBean.setMaterialCategory(materialCategory);
			materialService.saveMaterialDetailBean(num,materialBean);
		}
		try {
			request.getRequestDispatcher("/MaterialController/findMaterialDataByName").forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据分类，名称去查重
	 * @param materialCategory
	 * @param materialName
	 * @param op
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkMaterialName")
	public boolean checkMaterialName(String id,String materialCategory,String materialName){
		StringBuffer hql = new StringBuffer(" from MaterialBean m where m.materialCategory.id=? and m.materialName=? and m.deleted=? ");
		List<Object> obParam = new ArrayList<Object>();
		obParam.add(materialCategory);
		obParam.add(materialName);
		obParam.add(false);
		
		if(id!=null&&!id.trim().equals("")){
			//表示id存在，过滤这个id 的名称
			hql.append(" and m.id!=? ");
			obParam.add(id);
		}
		
		List<MaterialBean> nameList = commonService.findByHql(hql.toString(), obParam.toArray());
		
		if(nameList.size()>0){
			return true;
		}
		return false;
	}
	
	@ResponseBody
	@RequestMapping("/uploadImg")
	public String uploadImg(@RequestParam("img") CommonsMultipartFile file,HttpServletRequest request){
		System.out.println("fileName："+file.getOriginalFilename());
		
		String p = this.getClass().getClassLoader().getResource("/").getPath();
		
        String path= p.substring(0, p.indexOf("/WEB-INF"))+"/upload/"+new Date().getTime()+file.getOriginalFilename();
        
        File newFile=new File(path);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        try {
			file.transferTo(newFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return path.substring(path.indexOf("/upload"));
	}
}
