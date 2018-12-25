package com.xl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.xl.domain.Dept;
import com.xl.provider.DeptDynaSqlProvider;

import static com.xl.utils.HrmConstants.DEPTTABLE;

/**
 * 操作部门表
 * @author xuelong
 *
 */
public interface DeptDao {

//	动态查询
	@SelectProvider(type=DeptDynaSqlProvider.class,method="selectWithParam")
	List<Dept> selectByPage(Map<String , Object> params);
	
//	查询记录总数
	@SelectProvider(type=DeptDynaSqlProvider.class,method="count")
	Integer count(Map<String , Object> params);
	
//	查询所有
	@Select("select * from " + DEPTTABLE + " ")
	List<Dept> selectAllDept();
	
//	根据id查询
	@Select("select * from " + DEPTTABLE + " where id = #{id}")
	Dept selectById(Integer id);
	
//	根据id删除
	@Delete("delete from " + DEPTTABLE + " where id = #{id}")
	void deleteById(Integer id);
	
//	动态插入
	@InsertProvider(type=DeptDynaSqlProvider.class,method="insertDept")
	void save(Dept dept);
	
//	动态修改
	@UpdateProvider(type=DeptDynaSqlProvider.class,method="updateDept")
	void update(Dept dept);
}
