package com.xl.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xl.domain.Document;
import com.xl.service.DocumentService;
import com.xl.utils.PageModel;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Resource(name="documentService")
	private DocumentService documentService;

	/**
	 * 条件查询
	 * @param pageIndex
	 * @param document
	 * @param model
	 * @return
	 */
	@RequestMapping("/findDocument")
	public String findDocument(Integer pageIndex,Document document,Model model) {
		PageModel pageModel = new PageModel();
		if(pageIndex!=null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<Document> list = documentService.find(document, pageModel);
		model.addAttribute("list", list);
		model.addAttribute("pageModel", pageModel);
		return "document/document";
	}
	
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteDocument")
	public String deleteDocument(String ids) {
		if(ids!=null&&!ids.equals("")) {
			String[] strings = ids.split(",");
			for (String string : strings) {
				documentService.deleteById(Integer.parseInt(string));
			}
			return "redirect:findDocument";
		}else {
			return "redirect:main.jsp";
		}
	}
	
	/**
	 * 上传文件
	 * @param flag
	 * @param document
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/addDocument")
	public String addDocument(String flag,Document document,HttpServletRequest request) throws IllegalStateException, IOException {
		if(flag.equals("1")) {
			return "document/addDocument";
		}else {
			if(!document.getFile().isEmpty()) {
//				源文件名
				String originalFilename = document.getFile().getOriginalFilename();
				String path = request.getServletContext().getRealPath("/upload/");
				File file = new File(path, originalFilename);
				if(!file.getParentFile().getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				document.getFile().transferTo(new File(path+File.separator+originalFilename));
				document.setFilename(originalFilename);
			}
			documentService.addDocument(document);
			return "redirect:findDocument";
		}
	}
	
	/**
	 * 修改文件
	 * @param flag
	 * @param document
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/updateDocument")
	public String updateDocument(String flag,Document document,Model model,HttpServletRequest request) throws IllegalStateException, IOException {
		if(flag.equals("1")) {
			Document document2 = documentService.findById(document.getId());
			model.addAttribute("document", document2);
			return "document/updateDocument";
		}else {
			if(!document.getFile().isEmpty()) {
				String path = request.getServletContext().getRealPath("/upload");
				String originalFilename = document.getFile().getOriginalFilename();
				File file = new File(path,originalFilename);
				if(!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				document.getFile().transferTo(file);
			}
			documentService.update(document);
			return "redirect:findDocument";
		}
	}
	
	/**
	 * 下载文件
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(Integer id,HttpServletRequest request) throws IOException {
		Document document = documentService.findById(id);
		String filename = document.getFilename();
		String path = request.getServletContext().getRealPath("/upload");
		File file = new File(path, filename);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", filename);
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers,HttpStatus.CREATED);
	}
}
