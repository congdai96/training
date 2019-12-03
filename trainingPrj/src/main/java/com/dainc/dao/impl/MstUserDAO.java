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
		sql.append(" ORDER BY first_name desc");
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

	@Override
	public void update(MstUserModel updateUserModel) {
		int genderId = -1,age= -1,authorityId = -1;
		if (updateUserModel.getAuthorityId()!=0) {
			 authorityId = updateUserModel.getAuthorityId();
			}
		if (updateUserModel.getGenderId()!=0) {
			 genderId = updateUserModel.getGenderId();
			}
		if (updateUserModel.getAge()!=0) {
			 age = updateUserModel.getAge();
			}
		StringBuilder sql = new StringBuilder("UPDATE mst_user SET password = ?, family_name = ?,");
		sql.append(" gender_id = ?, authority_id = ?, first_name = ?, age = ?, admin = ?,");
		sql.append(" create_user_id = ?, update_user_id = ?, create_date = ?, update_date = ? WHERE user_id = ?");				
		update(sql.toString(), updateUserModel.getPassword(), updateUserModel.getFamilyName(),genderId,
		authorityId,updateUserModel.getFirstName(),age,
		updateUserModel.getAdmin(), updateUserModel.getCreatedBy(), updateUserModel.getModifiedBy(), 
		updateUserModel.getCreatedDate(),updateUserModel.getModifiedDate(),updateUserModel.getUserId());
	}

	@Override
	public void delete(String userId) {
		String sql = "DELETE FROM mst_user WHERE user_id = ?";
		update(sql, userId);	
	}

	@Override
	public void save(MstUserModel saveUserModel) {
		int genderId = -1,age= -1,authorityId = -1;
		if (saveUserModel.getAuthorityId()!=0) {
			 authorityId = saveUserModel.getAuthorityId();
			}
		if (saveUserModel.getGenderId()!=0) {
			 genderId = saveUserModel.getGenderId();
			}
		if (saveUserModel.getAge()!=0) {
			 age = saveUserModel.getAge();
			}
		StringBuilder sql = new StringBuilder("INSERT INTO mst_user (user_id, password, family_name, first_name,");
		sql.append(" admin, create_user_id, update_user_id, create_date, update_date, age, authority_id, gender_id)");
		sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		update(sql.toString(), saveUserModel.getUserId(),saveUserModel.getPassword(), saveUserModel.getFamilyName(), saveUserModel.getFirstName(),
		saveUserModel.getAdmin(), saveUserModel.getCreatedBy(), 
		saveUserModel.getModifiedBy(), saveUserModel.getCreatedDate(),saveUserModel.getModifiedDate(),age,authorityId,genderId);
	}


	@Override
	public List<MstUserModel> search(MstUserModel mstUserModel) {
		String familyName = " is not null",firstName = " is not null",authorityId = " true";
		if(mstUserModel.getFamilyName()!="") {
			familyName = "='"+ mstUserModel.getFamilyName() +"'";
		}
		if(mstUserModel.getFirstName()!="") {
			firstName = "='"+ mstUserModel.getFirstName() +"'";
		}
		if(mstUserModel.getAuthorityId()!=0) {
			authorityId = "  u.authority_id="+ Integer.toString(mstUserModel.getAuthorityId());
		}
		StringBuilder sql = new StringBuilder("SELECT u.user_id,u.password,u.family_name,u.first_name,u.gender_id,u.age,u.authority_id,u.\"admin\",u.create_user_id,u.update_user_id,u.create_date,u.update_date,e.gender_name,r.authority_name FROM mst_user as u");
		sql.append(" left join mst_gender as e on u.gender_id = e.gender_id");
		sql.append(" left join mst_role as r on u.authority_id=r.authority_id");
		sql.append(" WHERE family_name"+familyName+" and first_name"+firstName+" and"+authorityId+" ORDER BY first_name desc");
		List<MstUserModel> users = query(sql.toString(), new MstUserMapper());
		return users.isEmpty() ? null : users;

	}


}
	
