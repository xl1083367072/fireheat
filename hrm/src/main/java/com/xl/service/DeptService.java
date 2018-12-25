package com.xl.service;


import java.util.List;
import com.xl.domain.Dept;
import com.xl.utils.PageModel;

public interface DeptService {
	
	/**
	 * 根据条件查询所有部门
	 * @param params
	 * @return
	 */
	List<Dept> find(Dept dept,PageModel pageModel);
		
//	查询所有部门信息
	List<Dept> findAll();
	
//	根据id查询部门
	Dept findById(Integer id);
	
//	根据id删除部门
	void deleteById(Integer id);
	
//	添加部门
	void addDept(Dept dept);
	
//	修改部门信息
	void update(Dept dept);
}
