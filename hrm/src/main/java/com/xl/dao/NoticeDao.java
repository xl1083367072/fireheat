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
import com.xl.domain.Notice;
import com.xl.provider.NoticeDynaSqlProvider;

import static com.xl.utils.HrmConstants.NOTICETABLE;

/**
 * 操作公告表
 * @author xuelong
 *
 */
public interface NoticeDao {

//	动态查询
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="selectWithParam")
	@Results({
		@Result(id=true,column="id",property="id"),
		@Result(column="create_date",property="createDate"),
		@Result(column="user_id",property="user",one=@One(select="com.xl.dao.UserDao.selectById",fetchType=FetchType.EAGER))
	})
	List<Notice> selectByPage(Map<String , Object> params);
	
//	查询总数
	@SelectProvider(type=NoticeDynaSqlProvider.class,method="count")
	Integer count(Map<String , Object> params);
	
//	根据id查询
	@Select("select * from " + NOTICETABLE + " where id = #{id}")
	Notice selectById(Integer id);
	
//	根据id删除
	@Delete("delete from " + NOTICETABLE + " where id = #{id}")
	void deleteById(Integer id);
	
//	动态更新
	@UpdateProvider(type=NoticeDynaSqlProvider.class,method="updateNotice")
	void update(Notice notice);
	
//	动态插入
	@InsertProvider(type=NoticeDynaSqlProvider.class,method="insertNotice")
	void save(Notice notice);
}
