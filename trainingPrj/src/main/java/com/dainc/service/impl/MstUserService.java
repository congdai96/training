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
	public boolean update(MstUserModel mstUserModel) {
		if(checkValidate(mstUserModel)) {
		MstUserModel oldUserModel = mstUserDAO.findOne(mstUserModel.getUserId());
		mstUserModel.setCreatedDate(oldUserModel.getCreatedDate());
		mstUserModel.setCreatedBy(oldUserModel.getCreatedBy());
		mstUserModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		mstUserDAO.update(mstUserModel);
		return true;
		}
		return false;
	}

	@Override
	public void delete(String userId) {
		mstUserDAO.delete(userId);		
	}

	@Override
	public boolean save(MstUserModel mstUserModel) {
		MstUserModel oldUserModel = mstUserDAO.findOne(mstUserModel.getUserId());
		if(oldUserModel == null && checkValidate(mstUserModel)) {
			mstUserModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			mstUserModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
			mstUserModel.setModifiedBy(mstUserModel.getCreatedBy());
			mstUserDAO.save(mstUserModel);
			return true;
		}
		return false;
		
	}

	@Override
	public List<MstUserModel> search(MstUserModel mstUserModel) {
		return mstUserDAO.search(mstUserModel);
	}

	@Override
	public boolean checkValidate(MstUserModel mstUserModel) {
		if ( mstUserModel.getUserId()==null || 
			mstUserModel.getFamilyName()== null || 
			mstUserModel.getFirstName()==null || 
			mstUserModel.getPassword()==null)
		return false;
		return true;
	}
	
}
