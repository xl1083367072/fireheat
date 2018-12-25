package com.xl.service;

import java.util.List;
import java.util.Map;

import com.xl.domain.Document;
import com.xl.utils.PageModel;

public interface DocumentService {

	/**
	 * 条件查询
	 * @param params
	 * @return
	 */
	List<Document> find(Document document,PageModel pageModel);

//	根据id查询
	Document findById(Integer id);
	
//	根据id删除
	void deleteById(Integer id);
	
//	修改文件
	void update(Document document);
	
//	添加文件
	void addDocument(Document document);
	
}
