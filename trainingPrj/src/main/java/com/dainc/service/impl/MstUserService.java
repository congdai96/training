package com.dainc.service.impl;

import java.util.List;


import javax.inject.Inject;

import com.dainc.dao.IMstGenderDAO;
import com.dainc.dao.IMstRoleDAO;
import com.dainc.dao.IMstUserDAO;
import com.dainc.model.MstGenderModel;
import com.dainc.model.MstRoleModel;
import com.dainc.model.MstUserModel;
import com.dainc.service.IMstUserService;

public class MstUserService implements IMstUserService {
	
	@Inject
	private IMstUserDAO mstUserDAO;
	
	@Inject
	private IMstRoleDAO mstRoleDAO;
	
	@Inject
	private IMstGenderDAO mstGenderDAO;
//	private IUserDAO userDAO;
//
//	public UserService() {
//		userDAO = new UserDAO();
//	}
	
	@Override
	public MstUserModel findByUserNameAndPassword(String userName, String password) {
		return mstUserDAO.findByUserNameAndPassword(userName, password);
	}

	@Override
	public List<MstUserModel> findAll() {
		return mstUserDAO.findAll();
	}

	@Override
	public int getTotalItem() {
		return mstUserDAO.getTotalItem();
	}

	@Override
	public MstUserModel findOne(String userId) {
		MstUserModel mstUserModel = mstUserDAO.findOne(userId);
		MstRoleModel mstRoleModel = mstRoleDAO.findOne(mstUserModel.getAuthorityId());
		MstGenderModel mstGenderModel = mstGenderDAO.findOne(mstUserModel.getGenderId());
		mstUserModel.setMstRoleModel(mstRoleModel);
		mstUserModel.setMstGenderModel(mstGenderModel);
        return mstUserModel;
	}
	
}
