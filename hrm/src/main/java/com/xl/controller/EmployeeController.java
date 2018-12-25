package com.xl.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.print.DocFlavor.STRING;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xl.domain.Dept;
import com.xl.domain.Employee;
import com.xl.domain.Job;
import com.xl.service.DeptService;
import com.xl.service.EmployeeService;
import com.xl.service.JobService;
import com.xl.utils.PageModel;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger log = Logger.getLogger(JobController.class);
	
	@Resource(name="employeeService")
	private EmployeeService employeeService;
	@Resource(name="deptService")
	private DeptService deptService;
	@Resource(name="jobService")
	private JobService jobService;
	/**
	 * 查询所有
	 * @param model
	 * @return
	 */
	@RequestMapping("/findAll")
	public String findAll(Model model) {
		List<Employee> list = employeeService.findAll();
		model.addAttribute("list", list);
		return "employee/employee";
	}
	
	/**
	 * 条件查询
	 * @param pageIndex
	 * @param employee
	 * @param model
	 * @return
	 */
	@RequestMapping("/findEmployee")
	public String findEmployee(Integer job_id,Integer dept_id,String pageIndex,Employee employee,Model model) {
		PageModel pageModel = new PageModel();
		if(pageIndex!=null){
			pageModel.setPageIndex(Integer.valueOf(pageIndex));
		}
		Job job = new Job();
		job.setId(job_id);
		Dept dept = new Dept();
		dept.setId(dept_id);
		employee.setJob(job);
		employee.setDept(dept);
		List<Employee> list = employeeService.findAll(employee, pageModel);
		List<Dept> depts = deptService.findAll();
		List<Job> jobs = jobService.findAll();
		model.addAttribute("list", list);
		model.addAttribute("pageModel", pageModel);
		model.addAttribute("depts", depts);
		model.addAttribute("jobs", jobs);
		return "employee/employee";
	}
	
	/**
	 * 添加员工
	 * @param flag
	 * @param employee
	 * @return
	 */
	@RequestMapping("/addEmployee")
	public String addEmployee(Integer job_id,Integer dept_id,String flag,Employee employee,Model model) {
		if(flag.equals("1")) {
			List<Dept> depts = deptService.findAll();
			List<Job> jobs = jobService.findAll();
			model.addAttribute("depts", depts);
			model.addAttribute("jobs", jobs);
			return "employee/addEmployee";
		}else {
			Job job = new Job();
			job.setId(job_id);
			Dept dept = new Dept();
			dept.setId(dept_id);
			employee.setJob(job);
			employee.setDept(dept);
			employeeService.addEmployee(employee);
			return "redirect:findEmployee";
		}
	}
	
	/**
	 * 修改员工
	 * @param flag
	 * @param employee
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateEmployee")
	public String updateEmployee(String flag,Employee employee,Model model) {
		if(flag.equals("1")) {
			Employee employee2 = employeeService.findById(employee.getId());
			List<Dept> depts = deptService.findAll();
			List<Job> jobs = jobService.findAll();
			model.addAttribute("depts", depts);
			model.addAttribute("jobs", jobs);
			model.addAttribute("employee", employee2);
			return "employee/updateEmployee";
		}else {
			employeeService.update(employee);
			return "redirect:findEmployee";
		}
	}
	
	/**
	 * 删除员工
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteEmployee")
	public String deleteEmployee(String ids) {
		if(ids!=null&&!ids.equals("")) {
			String[] strings = ids.split(",");
			for (String string : strings) {
				employeeService.deleteById(Integer.parseInt(string));
			}
			return "redirect:findEmployee";
		}else {
			return "redirect:main.jsp";
		}
	}
}
