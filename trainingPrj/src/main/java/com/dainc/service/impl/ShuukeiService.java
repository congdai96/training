package com.dainc.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.dainc.dao.IShuukeiDAO;
import com.dainc.model.ShuukeiModel;
import com.dainc.service.IShuukeiService;

public class ShuukeiService implements IShuukeiService {
	
	@Inject
	private IShuukeiDAO shuukeiDAO;



	@Override
	public List<ShuukeiModel> roleShuukei() {
		return shuukeiDAO.roleShuukei();
	}


}
