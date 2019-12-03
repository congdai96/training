package com.dainc.service.impl;

import java.sql.Timestamp;

import java.util.List;
import java.util.regex.Pattern;

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
		MstUserModel oldUserModel = mstUserDAO.findOne(mstUserModel.getUserId());	//ユーザーIDはあったかどうか検査
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
        String pHiragana = "[\\u3041-\\u3096\\u309D-\\u309F]|\\uD82C\\uDC01|\\uD83C\\uDE00";
        String pKatakana = "[\\u30A1-\\u30FA\\u30FD-\\u30FF\\u31F0-\\u31FF\\u32D0-\\u32FE\\u3300-\\u3357\\uFF66-\\uFF6F\\uFF71-\\uFF9D]|\\uD82C\\uDC00";
        String pHan = "[\\u2E80-\\u2E99\\u2E9B-\\u2EF3\\u2F00-\\u2FD5\\u3005\\u3007\\u3021-\\u3029\\u3038-\\u303B\\u3400-\\u4DB5\\u4E00-\\u9FD5\\uF900-\\uFA6D\\uFA70-\\uFAD9]|[\\uD840-\\uD868\\uD86A-\\uD86C\\uD86F-\\uD872][\\uDC00-\\uDFFF]|\\uD869[\\uDC00-\\uDED6\\uDF00-\\uDFFF]|\\uD86D[\\uDC00-\\uDF34\\uDF40-\\uDFFF]|\\uD86E[\\uDC00-\\uDC1D\\uDC20-\\uDFFF]|\\uD873[\\uDC00-\\uDEA1]|\\uD87E[\\uDC00-\\uDE1D]";
        String NAME_PATTERN = "^(([a-zA-Z0-9]|" + pHiragana + "|" + pKatakana + "|" + pHan + "){1,10})$";
        String USERPASS_PATTERN = "^([a-zA-Z0-9]{1,8})$";
		if (mstUserModel.getAdmin()<0 || mstUserModel.getAdmin()>1 ||
			Pattern.matches(USERPASS_PATTERN, mstUserModel.getUserId())==false ||
			Pattern.matches(NAME_PATTERN, mstUserModel.getFamilyName())==false ||
			Pattern.matches(NAME_PATTERN, mstUserModel.getFirstName())==false ||
			Pattern.matches(USERPASS_PATTERN, mstUserModel.getPassword())==false
			)
		return false;
		return true;
	}

	
}
