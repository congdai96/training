package com.dainc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dainc.model.MstRoleModel;

public class MstRoleMapper implements RowMapper<MstRoleModel> {

	@Override
	public MstRoleModel mapRow(ResultSet resultSet) {
		try {
			MstRoleModel roleModel = new MstRoleModel();
			roleModel.setAuthorityId(resultSet.getInt("authority_id"));
			roleModel.setAuthorityName(resultSet.getString("authority_name"));
			return roleModel;
		} catch (SQLException e) {
			return null;
		}
	}
}
