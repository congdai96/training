package com.dainc.dao.impl;

import java.util.List;

import com.dainc.dao.IMstRoleDAO;
import com.dainc.dao.IShuukeiDAO;
import com.dainc.mapper.MstRoleMapper;
import com.dainc.mapper.MstUserMapper;
import com.dainc.mapper.ShuukeiMapper;
import com.dainc.model.MstRoleModel;
import com.dainc.model.ShuukeiModel;

public class ShuukeiDAO extends AbstractDAO<ShuukeiModel> implements IShuukeiDAO {


	@Override
	public String shuukeiSql(String columnName, String where, String tableName) {
		StringBuilder sql = new StringBuilder();		
		sql.append(" LEFT JOIN (");
		sql.append(" 	SELECT authority_id,count(*) as "+columnName);
		sql.append(" 	FROM mst_user WHERE "+where+" GROUP BY authority_id) AS "+tableName);
		sql.append(" ON mst_role.authority_id = "+tableName+".authority_id");
		return sql.toString();
	}

	@Override
	public List<ShuukeiModel> roleShuukei() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT authority_name,male,female,max19,min20,notfull,notage");
		sql.append(" FROM mst_role");
		sql.append(shuukeiSql("male","gender_id=1", "tbMale"));
		sql.append(shuukeiSql("female","gender_id=2", "tbFemale"));
		sql.append(shuukeiSql("max19","age<=19", "tbMax19"));
		sql.append(shuukeiSql("min20","age>19", "tbMin20"));
		sql.append(shuukeiSql("notfull","authority_id IS NULL OR gender_id IS NULL OR age IS NULL", "tbNotFull"));
		sql.append(shuukeiSql("notage","age IS NULL", "tbNotAge"));
		return query(sql.toString(), new ShuukeiMapper());
		
	}




}
