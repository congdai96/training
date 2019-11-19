package com.dainc.mapper;

import java.sql.ResultSet;

import java.sql.SQLException;

import com.dainc.model.MstGenderModel;
import com.dainc.model.MstRoleModel;
import com.dainc.model.MstUserModel;

public class MstUserMapper implements RowMapper<MstUserModel> {

	@Override
	public MstUserModel mapRow(ResultSet resultSet) {
		try {
			MstUserModel user = new MstUserModel();
			user.setUserId(resultSet.getString("user_id"));
			user.setPassword(resultSet.getString("password"));
			user.setFamilyName(resultSet.getString("family_name"));
			user.setFirstName(resultSet.getString("first_name"));
			user.setGenderId(resultSet.getInt("gender_id"));
			user.setAge(resultSet.getInt("age"));
			user.setAuthorityId(resultSet.getInt("authority_id"));
			user.setAdmin(resultSet.getInt("admin"));
			user.setCreatedDate(resultSet.getTimestamp("create_date"));
			user.setCreatedBy(resultSet.getString("create_user_id"));
			user.setModifiedDate(resultSet.getTimestamp("update_date"));
			user.setModifiedBy(resultSet.getString("update_user_id"));
			try {
				MstRoleModel mstRoleModel = new MstRoleModel();
				mstRoleModel.setAuthorityName(resultSet.getString("authority_name"));
				user.setMstRoleModel(mstRoleModel);
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			try {
				MstGenderModel mstGenderModel = new MstGenderModel();
				mstGenderModel.setGenderName(resultSet.getString("gender_name"));
				user.setMstGenderModel(mstGenderModel);
			} catch (Exception e) {
				System.out.print(e.getMessage());
			}
			return user;
		} catch (SQLException e) {
			return null;
		}	
	}
}
