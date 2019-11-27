package com.dainc.dao;

import java.util.List;

import com.dainc.model.ShuukeiModel;

public interface IShuukeiDAO extends GenericDAO<ShuukeiModel> {
	List<ShuukeiModel> roleShuukei();
	String shuukeiSql(String columnName, String where,String tableName);
}
