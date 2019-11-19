package com.dainc.dao.impl;

import java.util.List;

import com.dainc.dao.IMstGenderDAO;
import com.dainc.mapper.MstGenderMapper;
import com.dainc.model.MstGenderModel;

public class MstGenderDAO extends AbstractDAO<MstGenderModel> implements IMstGenderDAO {

	@Override
	public List<MstGenderModel> findAll() {
		String sql = "SELECT * FROM mst_gender";
		return query(sql, new MstGenderMapper());
	}

	@Override
	public MstGenderModel findOne(int genderId) {
		String sql = "SELECT * FROM mst_gender WHERE gender_id = ?";
		List<MstGenderModel> genderList = query(sql, new MstGenderMapper(), genderId);
		return genderList.isEmpty() ? null : genderList.get(0);
	}



}
