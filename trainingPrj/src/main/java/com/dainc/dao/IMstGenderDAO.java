package com.dainc.dao;

import java.util.List;

import com.dainc.model.MstGenderModel;

public interface IMstGenderDAO extends GenericDAO<MstGenderModel> {
	List<MstGenderModel> findAll();
	MstGenderModel findOne(int genderId);
}
