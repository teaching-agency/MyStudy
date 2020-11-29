package com.teacher.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * DAO常见的方法
 * @author J.L.Zhou
 *
 */
public interface DAOSupport<E> {

	/**
	 * 添加就一个实体
	 * @param entity
	 * @throws SQLException
	 */
	void add(E entity)throws SQLException;
	
	/**
	 * 根据主键来更新其它的列的数据
	 * @param entity
	 * @throws SQLException
	 */
	void updateById(E entity)throws SQLException;
	
	/**
	 * 根据主键来删除一条记录
	 * @param id
	 * @throws SQLException
	 */
	void deleteById(Serializable id)throws SQLException;
	
	/**
	 * 根据主键查询一条记录
	 * @param id 
	 * @return 实体对象
	 * @throws SQLException
	 */
	E selectById(Serializable id)throws SQLException;
	
	/**
	 * 返回索引index开始的len条记录
	 * @param index
	 * @param len
	 * @return
	 * @throws SQLException
	 */
	List<E> select(int index, int len)throws SQLException;
	
	/**
	 * 统计所有记录数
	 * @return
	 * @throws SQLException
	 */
	long count()throws SQLException;
}
