package com.xl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xl.dao.JobDao;
import com.xl.domain.Job;
import com.xl.utils.PageModel;

@Transactional
@Service("jobService")
public class JobServiceImpl implements JobService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private JobDao jobDao;
	
	@Override
	public Job findById(Integer id) {
		log.info("findById方法执行了");
		return jobDao.selectById(id);
	}

	@Override
	public List<Job> findAll() {
		log.info("findAll方法执行了");
		return jobDao.selectAll();
	}

	@Override
	public List<Job> find(Job job, PageModel pageModel) {
		log.info("find方法执行了");
		Map<String, Object> params = new HashMap<>();
		params.put("job", job);
		Integer count = jobDao.count(params);
		pageModel.setRecordCount(count);
		params.put("pageModel", pageModel);
		return jobDao.selectByPage(params);
	}

	@Override
	public void deleteById(Integer id) {
		log.info("deleteById方法执行了");
		jobDao.deleteById(id);
	}

	@Override
	public void addJob(Job job) {
		log.info("addJob方法执行了");
		jobDao.save(job);
	}

	@Override
	public void update(Job job) {
		log.info("update方法执行了");
		jobDao.update(job);
	}

}
