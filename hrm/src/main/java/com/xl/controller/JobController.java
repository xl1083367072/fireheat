package com.xl.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xl.domain.Job;
import com.xl.service.JobService;
import com.xl.utils.PageModel;

@Controller
@RequestMapping("/job")
public class JobController {

	private static final Logger log = Logger.getLogger(JobController.class);
	
	@Resource(name="jobService")
	private JobService jobService;
	
	@RequestMapping("/findAll")
	public String findAll(Model model) {
		List<Job> list = jobService.findAll();
		model.addAttribute("list", list);
		return "job/job";
	}
	
	/**
	 * 条件查询
	 * @param pageIndex
	 * @param job
	 * @return
	 */
	@RequestMapping("/findJob")
	public ModelAndView findJob(Integer pageIndex,Job job) {
		PageModel pageModel = new PageModel();
		if(pageIndex!=null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<Job> list = jobService.find(job, pageModel);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageModel", pageModel);
		modelAndView.setViewName("job/job");
		return modelAndView;
	}
	
	/**
	 * 添加职位
	 * @param flag
	 * @param job
	 * @return
	 */
	@RequestMapping("/addJob")
	public String addJob(String flag,Job job) {
		if(flag.equals("1")) {
			return "job/addJob";
		}else {
			jobService.addJob(job);
			return "redirect:findJob";
		}
	}
	
	/**
	 * 修改职位
	 * @param flag
	 * @param job
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateJob")
	public String updateJob(String flag,Job job,Model model) {
		if(flag.equals("1")) {
			Job job2 = jobService.findById(job.getId());
			model.addAttribute("job", job2);
			return "job/updateJob";
		}else {
			jobService.update(job);
			return "redirect:findJob";
		}
	}
	
	/**
	 * 删除职位
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteJob")
	public String deleteJob(String ids) {
		if(ids!=null&&!ids.equals("")) {
			String[] strings = ids.split(",");
			for (String string : strings) {
				jobService.deleteById(Integer.parseInt(string));
			}
			return "redirect:findJob";
		}else {
			return "redirect:main.jsp";
		}
	}
}
