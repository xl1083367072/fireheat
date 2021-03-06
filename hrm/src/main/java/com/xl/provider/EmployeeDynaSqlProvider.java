package com.xl.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

import com.xl.domain.Employee;

import static com.xl.utils.HrmConstants.EMPLOYEETABLE;

/**
 * 拼接用户表sql语句
 * @author xuelong
 *
 */
public class EmployeeDynaSqlProvider {

//	分页动态查询
	public String selectWithParam(Map<String , Object> params) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM(EMPLOYEETABLE);
				if(params.get("employee")!=null) {
					Employee employee = (Employee)params.get("employee");
					if(employee.getDept()!=null&&employee.getDept().getId()!=null&&employee.getDept().getId()!=0) {
						WHERE(" dept_id = #{employee.dept.id} ");
					}
					if(employee.getJob()!=null&&employee.getJob().getId()!=null&&employee.getJob().getId()!=0) {
						WHERE(" job_id = #{employee.job.id} ");
					}
					if(employee.getName()!=null&&!employee.getName().equals("")) {
						WHERE(" name like concat ('%',#{employee.name},'%') ");
					}
					if(employee.getPhone()!=null&&!employee.getPhone().equals("")) {
						WHERE(" name like concat ('%',#{employee.phone},'%') ");
					}
					if(employee.getCardId()!=null&&!employee.getCardId().equals("")) {
						WHERE(" card_id like concat ('%',#{employee.cardId},'%') ");
					}
					if(employee.getSex()!=null&&employee.getSex()!=0) {
						WHERE(" sex = #{employee.sex} ");
					}
				}
			}
		}.toString();
		if(params.get("pageModel")!=null) {
			sql +=" limit #{pageModel.firstLimitParam},#{pageModel.pageSize}";
		}
		return sql;
	}
	
//	动态查询总数量
	public String count(Map<String , Object> params) {
		return new SQL() {
			{
				SELECT("count(*)");
				FROM(EMPLOYEETABLE);
				if(params.get("employee")!=null) {
					Employee employee = (Employee)params.get("employee");
					if(employee.getDept()!=null&&employee.getDept().getId()!=null&&employee.getDept().getId()!=0) {
						WHERE(" dept_id = #{employee.dept.id} ");
					}
					if(employee.getJob()!=null&&employee.getJob().getId()!=null&&employee.getJob().getId()!=0) {
						WHERE(" job_id = #{employee.job.id} ");
					}
					if(employee.getName()!=null&&!employee.getName().equals("")) {
						WHERE(" name like concat ('%',#{employee.name},'%') ");
					}
					if(employee.getPhone()!=null&&!employee.getPhone().equals("")) {
						WHERE(" name like concat ('%',#{employee.phone},'%') ");
					}
					if(employee.getCardId()!=null&&!employee.getCardId().equals("")) {
						WHERE(" card_id like concat ('%',#{employee.cardId},'%') ");
					}
					if(employee.getSex()!=null&&employee.getSex()!=0) {
						WHERE(" sex = #{employee.sex} ");
					}
				}
			}
		}.toString();
	}
	
//	动态插入
	public String insertEmployee(Employee employee) {
		return new SQL() {
			{
				INSERT_INTO(EMPLOYEETABLE);
				if(employee.getDept()!=null) {
					VALUES("dept_id", "#{dept.id}");
				}
				if(employee.getJob()!=null) {
					VALUES("job_id", "#{job.id}");
				}
				if(employee.getName()!=null) {
					VALUES("name", "#{name}");
				}
				if(employee.getCardId()!=null) {
					VALUES("card_id", "#{cardId}");
				}
				if(employee.getAddress()!=null) {
					VALUES("address", "#{address}");
				}
				if(employee.getPostCode()!=null) {
					VALUES("post_code", "#{postCode}");
				}
				if(employee.getTel()!=null) {
					VALUES("tel", "#{tel}");
				}
				if(employee.getPhone()!=null) {
					VALUES("phone", "#{phone}");
				}
				if(employee.getQqNum()!=null) {
					VALUES("qq_num", "#{qqNum}");
				}
				if(employee.getEmail()!=null) {
					VALUES("email", "#{email}");
				}
				if(employee.getSex()!=null) {
					VALUES("sex", "#{sex}");
				}
				if(employee.getParty()!=null) {
					VALUES("party", "#{party}");
				}
				if(employee.getBirthday()!=null) {
					VALUES("birthday", "#{birthday}");
				}
				if(employee.getRace()!=null) {
					VALUES("race", "#{race}");
				}
				if(employee.getEducation()!=null) {
					VALUES("education", "#{education}");
				}
				if(employee.getSpeciality()!=null) {
					VALUES("speciality", "#{speciality}");
				}
				if(employee.getHobby()!=null) {
					VALUES("hobby", "#{hobby}");
				}
				if(employee.getRemark()!=null) {
					VALUES("remark", "#{remark}");
				}
				if(employee.getCreateDate()!=null) {
					VALUES("createDate", "#{createDate}");
				}
			}
		}.toString();
	}
	
//	动态更新
	public String updateEmployee(Employee employee) {
		return new SQL() {
			{
				UPDATE(EMPLOYEETABLE);
				if(employee.getDept()!=null) {
					SET("dept_id = #{dept.id}");
				}
				if(employee.getJob()!=null) {
					SET("job_id = #{job_id}");
				}
				if(employee.getName()!=null) {
					SET("name = #{name}");
				}
				if(employee.getCardId()!=null) {
					SET("card_id = #{cardId");
				}
				if(employee.getAddress()!=null) {
					SET("address = #{address}");
				}
				if(employee.getPostCode()!=null) {
					SET("post_code = #{postCode}");
				}
				if(employee.getTel()!=null) {
					SET("tel = #{tel}");
				}
				if(employee.getPhone()!=null) {
					SET("phone = #{phone}");
				}
				if(employee.getQqNum()!=null) {
					SET("qq_num = #{qqNum}");
				}
				if(employee.getEmail()!=null) {
					SET("email = #{email}");
				}
				if(employee.getSex()!=null) {
					SET("sex = #{sex}");
				}
				if(employee.getParty()!=null) {
					SET("party = #{party}");
				}
				if(employee.getBirthday()!=null) {
					SET("birthday = #{birthday}");
				}
				if(employee.getRace()!=null) {
					SET("race = #{race}");
				}
				if(employee.getEducation()!=null) {
					SET("education = #{education");
				}
				if(employee.getSpeciality()!=null) {
					SET("speciality = #{speciality}");
				}
				if(employee.getHobby()!=null) {
					SET("hobby = #{hobby}");
				}
				if(employee.getRemark()!=null) {
					SET("remark = #{remark}");
				}
				if(employee.getCreateDate()!=null) {
					SET("createDate = #{createDate}");
				}
				WHERE(" id = #{id} ");
			}
		}.toString();
	}
}
