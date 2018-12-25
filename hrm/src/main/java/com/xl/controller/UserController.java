package com.xl.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xl.domain.User;
import com.xl.service.UserService;
import com.xl.utils.PageModel;

@Controller
public class UserController {

	private static final Logger log = Logger.getLogger(UserController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	/**
	 * 登录请求
	 * @param loginname
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping("/login")
	public ModelAndView login(String loginname,String password,HttpSession session) {
		User user = userService.login(loginname, password);
		ModelAndView modelAndView = new ModelAndView();
		if(user==null) {
			modelAndView.addObject("message", "用户名或密码错误，请重新登录");
//			跳转到登录页面,redirect和forward不会加上前缀和后缀
			modelAndView.setViewName("forward:loginForm.jsp");
		}else {
			log.info("用户登录=============================");
			session.setAttribute("user", user);
//			跳转到主页面
			modelAndView.setViewName("redirect:main.jsp");
		}
		return modelAndView;
	}
	
	@RequestMapping("/findAll")
	public String findAll(Model model) {
		List<User> list = userService.findAll();
		model.addAttribute("list", list);
		return "user/user";
	}
	
	
	/**
	 * 条件查询用户
	 * @param pageIndex
	 * @param user
	 * @param model
	 * @return
	 */
	@RequestMapping("/findUser")
	public String findUser(Integer pageIndex,User user,Model model) {
		log.info("条件查询用户============================="+user);
		PageModel pageModel = new PageModel();
		if(pageIndex!=null) {
			pageModel.setPageIndex(pageIndex);
		}
		List<User> list = userService.findAll(user, pageModel);
		model.addAttribute("list", list);
		model.addAttribute("pageModel", pageModel);
//		跳转到显示用户页面
		return "user/user";
	}
	
	/**
	 * 添加用户
	 * @param flag 请求标志，1是请求添加，否则是执行添加
	 * @param user
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("/addUser")
	public ModelAndView addUser(String flag,User user) {
		ModelAndView modelAndView = new ModelAndView();
		if(flag.equals("1")) {
//			跳转到添加用户页面
			modelAndView.setViewName("user/addUser");
		}else {
			log.info("添加用户============================="+user);
			userService.addUser(user);
//			跳转到查询用户请求
			modelAndView.setViewName("redirect:findUser");
		}
		return modelAndView;
	}
	
	/**
	 * 删除用户
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteUser")
	public String deleteuser(String ids) {
		if(ids!=null&&!ids.equals("")) {
			log.info("deleteUser执行了============================="+ids);
			String[] strings = ids.split(",");
			for (String string : strings) {
				userService.deleteUserById(Integer.valueOf(string));
			}
//		跳转到查询用户请求
			return "redirect:findUser";
		}else {
//			若为空重定向到主页面
			return "redirect:main.jsp";
		}
	}
	
	@RequestMapping("/updateUser")
	public String updateUser(String flag,User user,Model model) {
//		若是1跳转到修改页面
		if(flag.equals("1")) {
			User user2 = userService.findUserById(user.getId());
			model.addAttribute("user", user2);
			return "user/updateUser";
		}else {
			log.info("修改了用户============================="+user);
//			否则执行修改操作，跳转到查询用户请求
			userService.updateUser(user);
			return "redirect:findUser";
		}
	}
}
