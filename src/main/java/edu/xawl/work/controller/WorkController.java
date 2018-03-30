package edu.xawl.work.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.xawl.common.entity.PageBean;
import edu.xawl.common.service.CommonService;
import edu.xawl.material.entity.MaterialCategoryBean;
import edu.xawl.us.entity.UserBean;
import edu.xawl.work.entity.InstitutionBean;
import edu.xawl.work.entity.NewsBean;
import edu.xawl.work.service.WorkService;

@Controller
@RequestMapping("/WorkController")
public class WorkController {
	
	@Resource
	private WorkService workService;
	
	@Resource
	private CommonService commonService;
	
	
	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");    
	    dateFormat.setLenient(false);    
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));    
	}
	
	@RequestMapping("/workIndex")
	public String workIndex(HttpServletRequest req,Model model){
		/*HttpSession session = req.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if(user==null){
			model.addAttribute("loginMessage", "请先登陆！");
			return "/index/login";
		}*/
		ServletContext application = req.getSession().getServletContext();
		List<MaterialCategoryBean> category = (List<MaterialCategoryBean>) application.getAttribute("materialCategory");
		if(category==null){
			category = commonService.findByHql("  from MaterialCategoryBean m where m.deleted=? order by m.categorySortNum,m.modifyTime  ", false);
			application.setAttribute("materialCategory", category);
		}
		
		return "/work/workIndex";
	}
	
	@RequestMapping("/editNewsPage")
	public String editNewsPage(NewsBean news,@RequestParam(value="op",required=true)String op,Model model){
		if(news!=null&&news.getId()!=null&&!"".equals(news.getId())){
			news = (NewsBean) commonService.findById(NewsBean.class, news.getId());
			model.addAttribute("NewsBean", news);
		}
		model.addAttribute("op", op);
		return "/index/front/editNewsPage";
	}
	
	@ResponseBody
	@RequestMapping("/saveNews")
	public boolean saveNews(NewsBean newsBean,HttpServletRequest request){
		if(newsBean!=null) {
			newsBean.setCreator((UserBean)request.getSession().getAttribute("user"));
			newsBean.setDeleted(false);
			commonService.merge(newsBean);
		}
		return true;
	}
	
	@RequestMapping("/findNewsList")
	public String findNewsList(PageBean<NewsBean> pageBean,Model model){
		PageBean findPageBean = commonService.findByPageQuery(pageBean, "from NewsBean where deleted=? Order by modifyTime desc ","NewsBean",false);
		model.addAttribute("pageBean", findPageBean);
		return "/index/front/editNewsList";
	}
	
	
	@RequestMapping("/deleteNews")
	public String deleteNews(NewsBean newsBean,Model model){
		newsBean = (NewsBean) commonService.findById(NewsBean.class, newsBean.getId());
		newsBean.setDeleted(true);
		commonService.merge(newsBean);
		
		PageBean findPageBean = commonService.findByPageQuery(new PageBean(), "from NewsBean where deleted=? Order by modifyTime desc ","NewsBean",false);
		model.addAttribute("pageBean", findPageBean);
		return "/index/front/editNewsList";
	}
	
	//=================================上面是新闻，下面是规章制度==================================================
	
	
	@RequestMapping("/findInstitutionList")
	public String findInstitutionList(PageBean<InstitutionBean> pageBean,Model model){
		PageBean findPageBean = commonService.findByPageQuery(pageBean, "from InstitutionBean where deleted=? Order by modifyTime desc ","InstitutionBean",false);
		model.addAttribute("pageBean", findPageBean);
		return "/index/front/editInstitutionList";
	}
	
	@RequestMapping("/editInstitutionPage")
	public String editInstitutionPage(InstitutionBean ins,@RequestParam(value="op",required=true)String op,Model model){
		if(ins!=null&&ins.getId()!=null&&!"".equals(ins.getId())){
			ins = (InstitutionBean) commonService.findById(InstitutionBean.class, ins.getId());
			model.addAttribute("InstitutionBean", ins);
		}
		model.addAttribute("op", op);
		return "/index/front/editInstitutionPage";
	}
	
	
	@RequestMapping("/deleteInstitution")
	public String deleteInstitution(InstitutionBean ins,Model model){
		ins = (InstitutionBean) commonService.findById(InstitutionBean.class, ins.getId());
		ins.setDeleted(true);
		commonService.merge(ins);
		
		PageBean findPageBean = commonService.findByPageQuery(new PageBean<InstitutionBean>(), "from InstitutionBean where deleted=? Order by modifyTime desc ","InstitutionBean",false);
		model.addAttribute("pageBean", findPageBean);
		return "/index/front/editInstitutionList";
	}
	
	
	@ResponseBody
	@RequestMapping("/saveInstitution")
	public boolean saveInstitution(InstitutionBean ins,HttpServletRequest request){
		if(ins!=null) {
			ins.setDeleted(false);
			commonService.merge(ins);
		}
		return true;
	}

}
