package com.dainc.service.impl;

import java.sql.Timestamp;
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

	@Override
	public int update(MstUserModel mstUserModel) {
		if(mstUserModel.getUserId()=="") return 6;
		else if(mstUserModel.getFamilyName()=="") return 2;
		else if(mstUserModel.getFirstName()=="") return 3;
		else if(mstUserModel.getPassword()=="") return 4;
		MstUserModel oldUserModel = mstUserDAO.findOne(mstUserModel.getUserId());
		mstUserModel.setCreatedDate(oldUserModel.getCreatedDate());
		mstUserModel.setCreatedBy(oldUserModel.getCreatedBy());
		mstUserModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		mstUserDAO.update(mstUserModel);
		return 1;
	}

	@Override
	public void delete(String userId) {
		mstUserDAO.delete(userId);		
	}

	@Override
	public int save(MstUserModel mstUserModel) {
		if(mstUserModel.getUserId()=="") return 6;
		else if(mstUserModel.getFamilyName()=="") return 2;
		else if(mstUserModel.getFirstName()=="") return 3;
		else if(mstUserModel.getPassword()=="") return 4;
		MstUserModel oldUserModel = mstUserDAO.findOne(mstUserModel.getUserId());
		if(oldUserModel == null) {
			mstUserModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			mstUserModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			mstUserModel.setModifiedBy(mstUserModel.getCreatedBy());
			mstUserDAO.save(mstUserModel);
			return 1;
		}
		return 5;
		
	}

	@Override
	public List<MstUserModel> search(MstUserModel mstUserModel) {
		return mstUserDAO.search(mstUserModel);
	}
	
}
