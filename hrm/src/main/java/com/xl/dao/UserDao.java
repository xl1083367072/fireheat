package com.xl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.xl.domain.User;
import com.xl.provider.UserDynaSqlProvider;

/**
 * 操作用户表
 * @author xuelong
 *
 */
public interface UserDao {
	
//	根据登录名和密码查询用户
	@Select("select * from user_inf where loginname = #{loginname} and password = #{password}")
	User selectByLoginnameAndPassword(@Param("loginname") String loginname,@Param("password") String password);
	
//	查询所有用户
	@Select("select * from user_inf")
	List<User> selectAll();
	
//	根据id查询用户
	@Select("select * from user_inf where id = #{id}")
	User selectById(Integer id);
	
//	根据id删除用户
	@Delete("delete from user_inf where id = #{id}")
	void deleteById(Integer id);
	
//	动态修改用户
	@UpdateProvider(type=UserDynaSqlProvider.class,method="updateUser")
	void update(User user);
	
//	动态查询
	@SelectProvider(type=UserDynaSqlProvider.class,method="selectWithParam")
	List<User> selectByPage(Map<String , Object> params);
	
//	查询用户总数
	@SelectProvider(type=UserDynaSqlProvider.class,method="count")
	Integer count(Map<String , Object> params);
	
//	动态插入用户
	@InsertProvider(type=UserDynaSqlProvider.class,method="insertUser")
	void save(User user);
	
}
