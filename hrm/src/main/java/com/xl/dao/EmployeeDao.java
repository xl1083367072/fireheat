package com.xl.dao;

import static com.xl.utils.HrmConstants.EMPLOYEETABLE;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.mapping.FetchType;

import com.xl.domain.Employee;
import com.xl.provider.EmployeeDynaSqlProvider;

/**
 * 操作员工表
 * @author xuelong
 *
 */
public interface EmployeeDao {

//	查询员工总数
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="count")
	Integer count(Map<String , Object> params);
	
//	动态查询员工,需要把名称不一致的做映射
	@SelectProvider(type=EmployeeDynaSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="card_id",property="cardId"),
		@Result(column="post_code",property="postCode"),
		@Result(column="qq_num",property="qqNum"),
		@Result(column="birthday",property="birthday",javaType=Date.class),
		@Result(column="create_date",property="createDate",javaType=Date.class),
		@Result(column="dept_id",property="dept",one=@One(select="com.xl.dao.DeptDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="job_id",property="job",one=@One(select="com.xl.dao.JobDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Employee> selectByPage(Map<String , Object> params);
	
//	查询所有员工
	@Select("select * from "+ EMPLOYEETABLE +"")
	List<Employee> selectAll();
	
//	动态插入
	@InsertProvider(type=EmployeeDynaSqlProvider.class,method="insertEmployee")
	void save(Employee employee);
	
//	根据id删除
	@Delete("delete from " + EMPLOYEETABLE + " where id = #{id}")
	void deleteById(Integer id);
	
//	根据id查询
	@Select("select * from " + EMPLOYEETABLE + " where id = #{id}")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="card_id",property="cardId"),
		@Result(column="post_code",property="postCode"),
		@Result(column="qq_num",property="qqNum"),
		@Result(column="birthday",property="birthday",javaType=Date.class),
		@Result(column="create_date",property="createDate",javaType=Date.class),
		@Result(column="dept_id",property="dept",one=@One(select="com.xl.dao.DeptDao.selectById",fetchType=FetchType.EAGER)),
		@Result(column="job_id",property="job",one=@One(select="com.xl.dao.JobDao.selectById",fetchType=FetchType.EAGER))
	})
	Employee selectById(Integer id);
	
	
//	动态修改
	@UpdateProvider(type=EmployeeDynaSqlProvider.class,method="updateEmployee")
	void update(Employee employee);
	
}
