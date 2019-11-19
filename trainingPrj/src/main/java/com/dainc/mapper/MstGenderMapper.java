package com.dainc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dainc.model.MstGenderModel;

public class MstGenderMapper implements RowMapper<MstGenderModel> {

	@Override
	public MstGenderModel mapRow(ResultSet resultSet) {
		try {
			MstGenderModel genderModel = new MstGenderModel();
			genderModel.setGenderId(resultSet.getInt("gender_id"));
			genderModel.setGenderName(resultSet.getString("gender_name"));
			return genderModel;
		} catch (SQLException e) {
			return null;
		}
	}
}
