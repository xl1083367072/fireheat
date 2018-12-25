package com.xl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xl.dao.DeptDao;
import com.xl.domain.Dept;
import com.xl.utils.PageModel;

@Transactional
@Service("deptService")
public class DeptServiceImpl implements DeptService {

	private static final Logger log = Logger.getLogger(DeptServiceImpl.class);
	
	@Autowired
	private DeptDao deptDao;
	
	@Override
	public List<Dept> find(Dept dept, PageModel pageModel) {
		log.info("find方法执行了");
		Map<String , Object> params = new HashMap<>();
		params.put("dept", dept);
		Integer count = deptDao.count(params);
		pageModel.setRecordCount(count);
		params.put("pageModel", pageModel);
		return deptDao.selectByPage(params);
	}

	@Override
	public List<Dept> findAll() {
		log.info("findAll方法执行了");
		return deptDao.selectAllDept();
	}

	@Override
	public Dept findById(Integer id) {
		log.info("selectById方法执行了");
		return deptDao.selectById(id);
	}

	@Override
	public void deleteById(Integer id) {
		log.info("deleteById方法执行了");
		deptDao.deleteById(id);
	}

	@Override
	public void addDept(Dept dept) {
		log.info("addDept方法执行了");
		deptDao.save(dept);
	}

	@Override
	public void update(Dept dept) {
		log.info("update方法执行了");
		deptDao.update(dept);
	}

}
