package com.xl.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xl.domain.Notice;
import com.xl.service.NoticeService;
import com.xl.utils.PageModel;

@Controller
@RequestMapping("/notice")
public class NotcieController {

	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	/**
	 * 条件查询
	 * @param pageIndex
	 * @param notice
	 * @param model
	 * @return
	 */
	@RequestMapping("/findNotice")
	public String findNotice(Integer pageIndex,Notice notice,Model model) {
		PageModel pageModel = new PageModel();
		if(pageIndex!=null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<Notice> list = noticeService.find(notice, pageModel);
		model.addAttribute("list", list);
		model.addAttribute("pageModel", pageModel);
		return "notice/notice";
	}
	
	/**
	 * 添加
	 * @param flag
	 * @param notice
	 * @return
	 */
	@RequestMapping("addNotice")
	public String addNotice(String flag,Notice notice) {
		if(flag.equals("1")) {
			return "notice/addNotice";
		}else {
			noticeService.addNotice(notice);
			return "redirect:findNotice";
		}
	}
	
	/**
	 * 修改公告
	 * @param flag
	 * @param notice
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateNotice")
	public String updateNotice(String flag,Notice notice,Model model) {
		if(flag.equals("1")) {
			Notice notice2 = noticeService.findById(notice.getId());
			model.addAttribute("notice", notice2);
			return "notice/updateNotice";
		}else {
			noticeService.update(notice);
			return "redirect:findNotice";
		}
	}
	
	/**
	 * 删除公告
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteNotice")
	public String deleteNotice(String ids) {
		if(ids!=null&&!ids.equals("")) {
			String[] strings = ids.split(",");
			for (String string : strings) {
				noticeService.deleteById(Integer.parseInt(string));
			}
			return "redirect:findNotice";
		}else {
			return "redirect:main.jsp";
		}
	}
	
	@RequestMapping("/previewNotice")
	public String previewNotice(Integer id,Model model) {
		Notice notice = noticeService.findById(id);
		model.addAttribute("notice", notice);
		return "notice/previewNotice";
	}
}
