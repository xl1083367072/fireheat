package com.xl.dao;

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

import com.xl.domain.Document;
import com.xl.provider.DocumentDynaSqlProvider;

import static com.xl.utils.HrmConstants.DOCUMENTTABLE;

/**
 * 操作文件表
 * @author xuelong
 *
 */
public interface DocumentDao {

//	动态查询
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="create_date",property="createDate"),
		@Result(column="user_id",property="user",one=@One(select="com.xl.dao.UserDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Document> selectByPage(Map<String , Object> params);
	
//	查询记录总数
	@SelectProvider(type=DocumentDynaSqlProvider.class,method="count")
	Integer count(Map<String , Object> params);

//	根据id查询
	@Select("select * from " + DOCUMENTTABLE + " where id = #{id}")
	Document selectById(Integer id);
	
//	根据id删除
	@Delete("delete from " + DOCUMENTTABLE + " where id = #{id}")
	void deleteById(Integer id);
	
//	动态更新
	@UpdateProvider(type=DocumentDynaSqlProvider.class,method="updateDocument")
	void update(Document document);
	
//	动态插入
	@InsertProvider(type=DocumentDynaSqlProvider.class,method="insertDocument")
	void save(Document document);
	
	
}