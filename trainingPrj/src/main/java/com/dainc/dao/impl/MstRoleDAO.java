package com.dainc.dao.impl;

import java.util.List;

import com.dainc.dao.IMstRoleDAO;
import com.dainc.mapper.MstRoleMapper;
import com.dainc.model.MstRoleModel;

public class MstRoleDAO extends AbstractDAO<MstRoleModel> implements IMstRoleDAO {

	@Override
	public List<MstRoleModel> findAll() {
		String sql = "SELECT * FROM mst_role";
		return query(sql, new MstRoleMapper());
	}

	@Override
	public MstRoleModel findOne(int authorityId) {
		String sql = "SELECT * FROM mst_role WHERE authority_id = ?";
		List<MstRoleModel> roleList = query(sql, new MstRoleMapper(), authorityId);
		return roleList.isEmpty() ? null : roleList.get(0);
	}



}
