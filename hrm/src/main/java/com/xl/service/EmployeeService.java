package com.xl.service;

import java.util.List;
import com.xl.domain.Employee;
import com.xl.utils.PageModel;

public interface EmployeeService {

	/**
	 * 条件查询
	 * @param employee 条件
	 * @param pageModel 分页
	 * @return
	 */
	List<Employee> findAll(Employee employee,PageModel pageModel);
	
//	查询所有员工
	List<Employee> findAll();
	
//	添加员工
	void addEmployee(Employee employee);
	
//	根据id删除员工
	void deleteById(Integer id);
	
//	根据id查询员工
	Employee findById(Integer id);
	
//	更新员工信息
	void update(Employee employee);
	
}
