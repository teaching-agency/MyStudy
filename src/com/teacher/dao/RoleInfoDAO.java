package com.teacher.dao;

import com.teacher.entity.RoleInfo;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import static com.teacher.utils.DBUtil.*;

public class RoleInfoDAO implements DAOSupport<RoleInfo> {

	@Override
	public void add(RoleInfo entity) throws SQLException {
		insertSelective(entity);
	}

	@Override
	public void updateById(RoleInfo entity) throws SQLException {
		updateSelective(entity);
		
	}

	@Override
	public void deleteById(Serializable id) throws SQLException {
		delete(id, RoleInfo.class);
		
	}

	@Override
	public RoleInfo selectById(Serializable id) throws SQLException {
		String sql = "select * from role_info where role_id=?";
		return get(sql, RoleInfo.class, id);
	}

	@Override
	public List<RoleInfo> select(int index, int len) throws SQLException {
		String sql = "select * from role_info order by role_id limit ?,?";
		return list(sql, RoleInfo.class, index,len);
	}

	@Override
	public long count() throws SQLException {
		return getLong("select count(*) as c from role_info");
	}

}
