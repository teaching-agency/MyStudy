package com.teacher.dao;

import com.teacher.dto.UserInfoDTO;
import com.teacher.dto.UserSearch;
import com.teacher.dto.Where;
import com.teacher.entity.UserInfo;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import static com.teacher.utils.DBUtil.*;

public class UserInfoDAO implements DAOSupport<UserInfo> {

	@Override
	public void add(UserInfo entity) throws SQLException {
		insertSelective(entity);
	}

	@Override
	public void updateById(UserInfo entity) throws SQLException {
		updateSelective(entity);
	}

	@Override
	public void deleteById(Serializable id) throws SQLException {
		delete(id, UserInfo.class);
	}

	@Override
	public UserInfo selectById(Serializable id) throws SQLException {
		String sql = "select * from user_info where user_name=?";
		return get(sql,UserInfo.class,id);
	}

	@Override
	public List<UserInfo> select(int index, int len) throws SQLException {
		String sql = "select * from user_info order by USER_REGISTER_TIME desc limit ?,?";
		return list(sql,UserInfo.class,index,len);
	}

	@Override
	public long count() throws SQLException {
		String sql = "select count(*) from user_info";
		return getLong(sql);
	}
	
	public UserInfo selectByUserName(String userName) throws SQLException{
		String sql = "select * from user_info where user_Name=?";
		return get(sql,UserInfo.class,userName);
	}
	
	public List<UserInfoDTO> select1(UserSearch search) throws SQLException {
		Where where = getWhere(search);
		String sql = "SELECT user_info.*,role_info.ROLE_NAME from user_info INNER JOIN role_info ON user_info.ROLE_ID=role_info.ROLE_ID where 1=1 "+where.getWhereSql()+" order by user_info.USER_REGISTER_TIME desc limit ?,?";
		where.addParams(search.getIndex(),search.getLen());
		return list(sql,UserInfoDTO.class,where.getParams());
	}

	public long count1(UserSearch search) throws SQLException {
		Where where = getWhere(search);
		String sql = "SELECT count(*) as c from user_info INNER JOIN role_info ON user_info.ROLE_ID=role_info.ROLE_ID where 1=1 "+where.getWhereSql();
		return getLong(sql,where.getParams());
	}
	
	public Where getWhere(UserSearch search){
		Where where = new Where();
		if(search.getRoleId()!=null){
			where.addSql(" AND user_info.ROLE_ID=?")
				.addParams(search.getRoleId());
		}
		if(search.getKey()!=null&&!"".equals(search.getKey())){
			where.addSql(" and (user_info.user_name like CONCAT('%',?,'%') or user_info.USER_REAL_NAME  like CONCAT('%',?,'%'))")
				.addParams(search.getKey(),search.getKey());
		}
		return where;
	}
}
