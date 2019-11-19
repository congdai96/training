package com.dainc.dao.impl;

import java.util.List;

import com.dainc.dao.IMstUserDAO;
import com.dainc.mapper.MstUserMapper;
import com.dainc.model.MstUserModel;

public class MstUserDAO extends AbstractDAO<MstUserModel> implements IMstUserDAO  {

	@Override
	public MstUserModel findByUserNameAndPassword(String userName, String password) {
		StringBuilder sql = new StringBuilder("SELECT * FROM public.mst_user");
		sql.append(" WHERE user_id = ? AND password = ?");
		List<MstUserModel> users = query(sql.toString(), new MstUserMapper(), userName, password);
		return users.isEmpty() ? null : users.get(0);
	}
	
	@Override
	public List<MstUserModel> findAll() {
		StringBuilder sql = new StringBuilder("SELECT u.user_id,u.password,u.family_name,u.first_name,u.gender_id,u.age,u.authority_id,u.\"admin\",u.create_user_id,u.update_user_id,u.create_date,u.update_date,e.gender_name,r.authority_name FROM mst_user as u");
		sql.append(" left join mst_gender as e on u.gender_id = e.gender_id");
		sql.append(" left join mst_role as r on u.authority_id=r.authority_id");
		
		
//		if (pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName())
//				&& StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
//			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy() + "");
//		}
		sql.append(" ORDER BY first_name desc");
//		if (pageble.getOffset() != null && pageble.getLimit() != null) {
//			sql.append(" LIMIT " + pageble.getLimit() + " OFFSET " + pageble.getOffset() + "");
//		}
		return query(sql.toString(), new MstUserMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM mst_user";
		return count(sql);
	}

	@Override
	public MstUserModel findOne(String userId) {
		String sql = "SELECT * FROM mst_user WHERE user_id = ?";
		List<MstUserModel> user = query(sql, new MstUserMapper(), userId);
		return user.isEmpty() ? null : user.get(0);
	}
}
