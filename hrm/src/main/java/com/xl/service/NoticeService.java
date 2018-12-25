package com.xl.service;

import java.util.List;
import com.xl.domain.Notice;
import com.xl.utils.PageModel;

public interface NoticeService {

	/**
	 * 条件查询
	 * @param notice
	 * @param pageModel
	 * @return
	 */
	List<Notice> find(Notice notice,PageModel pageModel);
	
//	根据id查询公告
	Notice findById(Integer id);
	
//	根据id删除
	void deleteById(Integer id);
	
//	修改公告
	void update(Notice notice);
	
//	添加公告
	void addNotice(Notice notice);
	
}
