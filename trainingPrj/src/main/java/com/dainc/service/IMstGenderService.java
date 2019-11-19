package com.dainc.service;

import java.util.List;

import com.dainc.model.MstGenderModel;

public interface IMstGenderService {
	List<MstGenderModel> findAll();
	MstGenderModel findOne(int genderId);
}
