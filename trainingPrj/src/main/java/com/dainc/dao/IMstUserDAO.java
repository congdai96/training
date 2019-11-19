package com.dainc.dao;

import java.util.List;

import com.dainc.model.MstUserModel;

public interface IMstUserDAO extends GenericDAO<MstUserModel> {
	MstUserModel findByUserNameAndPassword(String userName, String password);
	List<MstUserModel> findAll();
	MstUserModel findOne(String userId);
	int getTotalItem();
}
