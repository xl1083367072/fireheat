package com.xl.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xl.domain.Dept;
import com.xl.service.DeptService;
import com.xl.utils.PageModel;

@Controller
@RequestMapping("/dept")
public class DeptController {

	private static final Logger log = Logger.getLogger(DeptController.class);
	
	@Resource(name="deptService")
	private DeptService deptService;
	
	/**
	 * 查询所有
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAll")
	public String findAll(Model model) {
		List<Dept> list = deptService.findAll();
		model.addAttribute("list", list);
		return "dept/dept";
	}
	
	/**
	 * 添加部门
	 * @param flag 1表示跳转到添加页面，否则是执行添加操作
	 * @param dept
	 * @return
	 */
	@RequestMapping("/addDept")
	public String addDept(String flag,Dept dept) {
		if(flag.equals("1")) {
//			跳转到添加页面
			return "dept/addDept";
		}else {
			log.info("添加部门============================="+dept);
//			执行添加，并跳转到查询请求
			deptService.addDept(dept);
			return "redirect:findDept";
		}
	}
	
	/**
	 * 条件查询
	 * @param pageIndex 请求第几页
	 * @param model 
	 * @param dept 条件
	 * @return
	 */
	@RequestMapping("/findDept")
	public String findDept(Integer pageIndex,Model model,Dept dept) {
		log.info("查询部门============================="+dept);
		PageModel pageModel = new PageModel();
		if(pageIndex!=null) {
			pageModel.setPageIndex(pageIndex);
		}
//		查询并跳转到显示页面
		List<Dept> list = deptService.find(dept, pageModel);
		model.addAttribute("list", list);
		model.addAttribute("pageModel", pageModel);
		return "dept/dept";
	}
	
	/**
	 * 修改部门信息
	 * @param dept
	 * @param flag
	 * @return
	 */
	@RequestMapping("/updateDept")
	public String updateDept(Dept dept,String flag,Model model) {
		if(flag.equals("1")) {
			Dept dept2 = deptService.findById(dept.getId());
			model.addAttribute("dept", dept2);
//			跳转到修改页面
			return "dept/updateDept";
		}else {
			log.info("修改部门============================="+dept);
//			否则执行添加操作,重定向到到查询请求
			deptService.update(dept);
			return "redirect:findDept";
		}
	}
	
	/**
	 * 删除部门
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteDept")
	public String deleteDept(String ids) {
		if(ids!=null&&!ids.equals("")) {
			log.info("删除部门============================="+ids);
			String[] strings = ids.split(",");
			for (String string : strings) {
				deptService.deleteById(Integer.parseInt(string));
			}
			return "redirect:findDept";
		}else {
			return "redirect:main.jsp";
		}
	}
	
	
	
	
	
	
}
