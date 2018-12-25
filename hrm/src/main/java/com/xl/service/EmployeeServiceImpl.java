package com.xl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xl.dao.EmployeeDao;
import com.xl.domain.Employee;
import com.xl.utils.PageModel;

@Transactional
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public List<Employee> findAll(Employee employee, PageModel pageModel) {
		log.info("findAll方法执行了");
		Map<String, Object> params = new HashMap<>();
		params.put("employee", employee);
		Integer count = employeeDao.count(params);
		pageModel.setRecordCount(count);
		params.put("pageModel", pageModel);
		return employeeDao.selectByPage(params);
	}

	@Override
	public void addEmployee(Employee employee) {
		log.info("addEmployee方法执行了");
		employeeDao.save(employee);
	}

	@Override
	public void deleteById(Integer id) {
		log.info("deleteById方法执行了");
		employeeDao.deleteById(id);
	}

	@Override
	public Employee findById(Integer id) {
		log.info("findById方法执行了");
		return employeeDao.selectById(id);
	}

	@Override
	public void update(Employee employee) {
		log.info("update方法执行了");
		employeeDao.update(employee);
	}

	@Override
	public List<Employee> findAll() {
		return employeeDao.selectAll();
	}

}
