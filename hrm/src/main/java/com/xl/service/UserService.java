package com.xl.service;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import com.xl.domain.User;
import com.xl.utils.PageModel;

public interface UserService {

	/**
	 * 登录验证
	 * @param loginname 用户名
	 * @param password	密码
	 * @return
	 */
	User login(String loginname,String password);
	
	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> findAll();
	
	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	User findUserById(Integer id);
	
	/**
	 * 条件查询所有用户
	 */
	List<User> findAll(User user,PageModel pageModel);
	
	/**
	 * 根据id删除用户
	 * @param id
	 */
	void deleteUserById(Integer id);
	
	/**
	 * 修改用户
	 */
	void updateUser(User user);
	
	/**
	 * 添加用户
	 */
	void addUser(User user);
}
