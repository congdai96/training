package com.dainc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.dainc.model.ShuukeiModel;

public class ShuukeiMapper implements RowMapper<ShuukeiModel> {

	@Override
	public ShuukeiModel mapRow(ResultSet resultSet) {
		try {
			ShuukeiModel shuukeiModel = new ShuukeiModel();
			shuukeiModel.setRoleName(resultSet.getString("authority_name"));
			shuukeiModel.setMale(resultSet.getInt("male"));
			shuukeiModel.setFemale(resultSet.getInt("female"));
			shuukeiModel.setAgeMax19(resultSet.getInt("max19"));
			shuukeiModel.setAgeMin20(resultSet.getInt("min20"));
			shuukeiModel.setNotFull(resultSet.getInt("notfull"));
			shuukeiModel.setNotAge(resultSet.getInt("notage"));
			return shuukeiModel;
		} catch (SQLException e) {
			return null;
		}
	}
}
