package com.dainc.dao;

import java.util.List;

import com.dainc.model.MstRoleModel;

public interface IMstRoleDAO extends GenericDAO<MstRoleModel> {
	List<MstRoleModel> findAll();
	MstRoleModel findOne(int authorityId);
}
