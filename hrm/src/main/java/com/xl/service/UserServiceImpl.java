package com.xl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xl.dao.UserDao;
import com.xl.domain.User;
import com.xl.utils.PageModel;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public User login(String loginname, String password) {
		log.info("login方法执行了");
		return userDao.selectByLoginnameAndPassword(loginname, password);
	}

	@Override
	@Transactional(readOnly=true)
	public User findUserById(Integer id) {
		log.info("findUserById方法执行了");
		return userDao.selectById(id);
	}

	@Override
	public List<User> findAll(User user, PageModel pageModel) {
		log.info("findAll方法执行了");
		Map<String, Object> params = new HashMap<>();
		params.put("user", user);
		Integer count = userDao.count(params);
		pageModel.setRecordCount(count);
		params.put("pageModel", pageModel);
		return userDao.selectByPage(params);
	}

	@Override
	public void deleteUserById(Integer id) {
		log.info("deleteUserById方法执行了");
		userDao.deleteById(id);
	}

	@Override
	public void updateUser(User user) {
		log.info("updateUser方法执行了");
		userDao.update(user);
	}

	@Override
	public void addUser(User user) {
		log.info("saveUser方法执行了");
		userDao.save(user);
	}

	@Override
	public List<User> findAll() {
		log.info("findAll方法执行了");
		return userDao.selectAll();
	}

}
