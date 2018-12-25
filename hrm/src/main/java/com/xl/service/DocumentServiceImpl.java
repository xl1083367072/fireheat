
package com.xl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xl.dao.DocumentDao;
import com.xl.domain.Document;
import com.xl.utils.PageModel;

@Transactional
@Service("documentService")
public class DocumentServiceImpl implements DocumentService{

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private DocumentDao documentDao;
	
	@Override
	public List<Document> find(Document document,PageModel pageModel) {
		log.info("find方法执行了");
		Map<String, Object> params = new HashMap<>();
		params.put("document", document);
		Integer count = documentDao.count(params);
		pageModel.setRecordCount(count);
		params.put("pageModel", pageModel);
		return	documentDao.selectByPage(params);
	}

	@Override
	public Document findById(Integer id) {
		log.info("findById方法执行了");
		return documentDao.selectById(id);
	}

	@Override
	public void deleteById(Integer id) {
		log.info("deleteById方法执行了");
		documentDao.deleteById(id);
	}

	@Override
	public void update(Document document) {
		log.info("update方法执行了");
		documentDao.update(document);
	}

	@Override
	public void addDocument(Document document) {
		log.info("addDocument方法执行了");
		documentDao.save(document);
	}

}
