package com.dainc.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.dainc.dao.IMstRoleDAO;
import com.dainc.model.MstRoleModel;
import com.dainc.service.IMstRoleService;

public class MstRoleService implements IMstRoleService {
	
	@Inject
	private IMstRoleDAO mstRoleDAO;

	@Override
	public List<MstRoleModel> findAll() {
		return mstRoleDAO.findAll();
	}

	@Override
	public MstRoleModel findOne(int authorityId) {
		return mstRoleDAO.findOne(authorityId);
	}


}
