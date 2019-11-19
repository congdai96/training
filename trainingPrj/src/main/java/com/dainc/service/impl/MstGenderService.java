package com.dainc.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.dainc.dao.IMstGenderDAO;
import com.dainc.model.MstGenderModel;
import com.dainc.service.IMstGenderService;

public class MstGenderService implements IMstGenderService {
	
	@Inject
	private IMstGenderDAO mstGenderDAO;

	@Override
	public List<MstGenderModel> findAll() {
		return mstGenderDAO.findAll();
	}

	@Override
	public MstGenderModel findOne(int genderId) {
		return mstGenderDAO.findOne(genderId);
	}


}
