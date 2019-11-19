package com.dainc.service;

import java.util.List;

import com.dainc.model.MstRoleModel;

public interface IMstRoleService {
	List<MstRoleModel> findAll();
	MstRoleModel findOne(int authorityId);
}
