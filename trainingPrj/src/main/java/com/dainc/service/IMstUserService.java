package com.dainc.service;

import java.util.List;

import com.dainc.model.MstUserModel;

public interface IMstUserService {
	MstUserModel findByUserNameAndPassword(String userName, String password);
	List<MstUserModel> findAll();
	MstUserModel findOne(String userId);
	int getTotalItem();
	int update(MstUserModel mstUserModel);
	void delete(String userId);
	int save(MstUserModel mstUserModel);
	List<MstUserModel> search(MstUserModel mstUserModel);
}
