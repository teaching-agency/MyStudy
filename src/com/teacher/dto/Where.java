package com.teacher.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Where {

	private StringBuffer where = new StringBuffer();
	private List<Object> params = new ArrayList<>();

	
	public Where addSql(String sql){
		where.append(sql);
		return this;
	}
	
	public Where addParams(Object... params) {
		Collections.addAll(this.params, params);
		return this;
	}
	
	public String getWhereSql(){
		return where.toString();
	}
	
	
	public Object[] getParams() {
		return params.toArray();
	}

	
	
}
