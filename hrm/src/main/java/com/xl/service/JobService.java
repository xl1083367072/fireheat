package com.xl.service;

import java.util.List;
import java.util.Map;

import com.xl.domain.Job;
import com.xl.utils.PageModel;

public interface JobService {

//	根据id查询职位
	Job findById(Integer id);
	
//	查询所有职位
	List<Job> findAll();
	
	/**
	 * 根据条件查询职位信息
	 * @param params 
	 * @return
	 */
	List<Job> find(Job job,PageModel pageModel);
	
//	根据id删除
	void deleteById(Integer id);
	
//	添加职位
	void addJob(Job job);
	
//	修改职位信息
	void update(Job job);
	
}
