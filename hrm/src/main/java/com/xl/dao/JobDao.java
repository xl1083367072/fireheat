package com.xl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import com.xl.domain.Job;
import com.xl.provider.JobDynaSqlProvider;

import static com.xl.utils.HrmConstants.JOBTABLE;

/**
 * 操作工作表
 * @author xuelong
 *
 */
public interface JobDao {

//	根据id查询
	@Select("select * from " + JOBTABLE + " where id = #{id}")
	Job selectById(Integer id);
	
//	查询所有
	@Select("select * from " + JOBTABLE + " ")
	List<Job> selectAll();
	
//	动态查询
	@SelectProvider(type=JobDynaSqlProvider.class,method="selectWithParam")
	List<Job> selectByPage(Map<String , Object> params);
	
//	查询记录总数
	@SelectProvider(type=JobDynaSqlProvider.class,method="count")
	Integer count(Map<String , Object> params);
	
//	根据id删除
	@Delete("delete from " + JOBTABLE + " where id = #{id}")
	void deleteById(Integer id);
	
//	动态插入
	@InsertProvider(type=JobDynaSqlProvider.class,method="insertJob")
	void save(Job job);
	
//	动态修改
	@UpdateProvider(type=JobDynaSqlProvider.class,method="updateJob")
	void update(Job job);
}

