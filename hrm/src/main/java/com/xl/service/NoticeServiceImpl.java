package com.xl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xl.dao.NoticeDao;
import com.xl.domain.Notice;
import com.xl.utils.PageModel;

@Transactional
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public List<Notice> find(Notice notice, PageModel pageModel) {
		log.info("find方法执行了");
		Map<String, Object> params = new HashMap<>();
		params.put("notice", notice);
		Integer count = noticeDao.count(params);
		pageModel.setRecordCount(count);
		params.put("pageModel", pageModel);
		return noticeDao.selectByPage(params);
	}

	@Override
	public Notice findById(Integer id) {
		log.info("findById方法执行了");
		return noticeDao.selectById(id);
	}

	@Override
	public void deleteById(Integer id) {
		log.info("deleteById方法执行了");
		noticeDao.deleteById(id);
	}

	@Override
	public void update(Notice notice) {
		log.info("update方法执行了");
		noticeDao.update(notice);
	}

	@Override
	public void addNotice(Notice notice) {
		log.info("addNotice方法执行了");
		noticeDao.save(notice);
	}

}
