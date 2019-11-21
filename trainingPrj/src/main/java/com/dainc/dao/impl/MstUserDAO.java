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
		StringBuilder sql = new StringBuilder("UPDATE mst_user SET password = ?, family_name = ?,");
		if (updateUserModel.getAuthorityId()==0 && updateUserModel.getGenderId()==0) {
				sql.append(" gender_id = null, authority_id = null, first_name = ?, age = ?, admin = ?,");
				sql.append(" create_user_id = ?, update_user_id = ?, create_date = ?, update_date = ? WHERE user_id = ?");				
				update(sql.toString(), updateUserModel.getPassword(), updateUserModel.getFamilyName(), updateUserModel.getFirstName(),
				updateUserModel.getAge(),
				updateUserModel.getAdmin(), updateUserModel.getCreatedBy(), 
				updateUserModel.getModifiedBy(), updateUserModel.getCreatedDate(),updateUserModel.getModifiedDate(),updateUserModel.getUserId());
				}
		else if (updateUserModel.getAuthorityId()==0 && updateUserModel.getGenderId()!=0) {
				sql.append("authority_id = null, first_name = ?, gender_id = ?, age = ?,  admin = ?,");
				sql.append(" create_user_id = ?, update_user_id = ?, create_date = ?, update_date = ? WHERE user_id = ?");			
				update(sql.toString(), updateUserModel.getPassword(), updateUserModel.getFamilyName(), updateUserModel.getFirstName(),
				updateUserModel.getGenderId(), updateUserModel.getAge(), 
				updateUserModel.getAdmin(), updateUserModel.getCreatedBy(), 
				updateUserModel.getModifiedBy(), updateUserModel.getCreatedDate(),updateUserModel.getModifiedDate(),updateUserModel.getUserId());
				}
		else if (updateUserModel.getAuthorityId()!=0 && updateUserModel.getGenderId()==0) {
				sql.append("gender_id = null, first_name = ?, age = ?, authority_id = ?, admin = ?,");
				sql.append(" create_user_id = ?, update_user_id = ?, create_date = ?, update_date = ? WHERE user_id = ?");
				update(sql.toString(), updateUserModel.getPassword(), updateUserModel.getFamilyName(), updateUserModel.getFirstName(),
				updateUserModel.getAge(), updateUserModel.getAuthorityId(), 
				updateUserModel.getAdmin(), updateUserModel.getCreatedBy(), 
				updateUserModel.getModifiedBy(), updateUserModel.getCreatedDate(),updateUserModel.getModifiedDate(),updateUserModel.getUserId());
				}
		else	{
				sql.append(" first_name = ?, gender_id = ?, age = ?, authority_id = ?, admin = ?,");
				sql.append(" create_user_id = ?, update_user_id = ?, create_date = ?, update_date = ? WHERE user_id = ?");
				update(sql.toString(), updateUserModel.getPassword(), updateUserModel.getFamilyName(), updateUserModel.getFirstName(),
				updateUserModel.getGenderId(), updateUserModel.getAge(), updateUserModel.getAuthorityId(), 
				updateUserModel.getAdmin(), updateUserModel.getCreatedBy(), 
				updateUserModel.getModifiedBy(), updateUserModel.getCreatedDate(),updateUserModel.getModifiedDate(),updateUserModel.getUserId());
		}
	}

	@Override
	public void delete(String userId) {
		String sql = "DELETE FROM mst_user WHERE user_id = ?";
		update(sql, userId);
		
	}

	@Override
	public void save(MstUserModel saveUserModel) {
		StringBuilder sql = new StringBuilder("INSERT INTO mst_user (user_id, password, family_name, first_name,");
		sql.append(" age, admin, create_user_id, update_user_id, create_date, update_date");
		if (saveUserModel.getAuthorityId()==0 && saveUserModel.getGenderId()==0) {
			sql.append(")");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			update(sql.toString(), saveUserModel.getUserId(),saveUserModel.getPassword(), saveUserModel.getFamilyName(), saveUserModel.getFirstName(),
					saveUserModel.getAge(),saveUserModel.getAdmin(), saveUserModel.getCreatedBy(), 
					saveUserModel.getModifiedBy(), saveUserModel.getCreatedDate(),saveUserModel.getModifiedDate());
		}
		else if (saveUserModel.getAuthorityId()==0 && saveUserModel.getGenderId()!=0) {
			sql.append(",gender_id)");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			update(sql.toString(), saveUserModel.getUserId(),saveUserModel.getPassword(), saveUserModel.getFamilyName(), saveUserModel.getFirstName(),
					saveUserModel.getAge(),saveUserModel.getAdmin(), saveUserModel.getCreatedBy(), 
					saveUserModel.getModifiedBy(), saveUserModel.getCreatedDate(),saveUserModel.getModifiedDate(),
					saveUserModel.getGenderId());
		}
		else if (saveUserModel.getAuthorityId()!=0 && saveUserModel.getGenderId()==0) {
			sql.append(",authority_id)");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			update(sql.toString(), saveUserModel.getUserId(),saveUserModel.getPassword(), saveUserModel.getFamilyName(), saveUserModel.getFirstName(),
					saveUserModel.getAge(),saveUserModel.getAdmin(), saveUserModel.getCreatedBy(), 
					saveUserModel.getModifiedBy(), saveUserModel.getCreatedDate(),saveUserModel.getModifiedDate(),
					saveUserModel.getAuthorityId());
		}
		else {
			sql.append(",authority_id, gender_id)");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			update(sql.toString(), saveUserModel.getUserId(),saveUserModel.getPassword(), saveUserModel.getFamilyName(), saveUserModel.getFirstName(),
					saveUserModel.getAge(),saveUserModel.getAdmin(), saveUserModel.getCreatedBy(), 
					saveUserModel.getModifiedBy(), saveUserModel.getCreatedDate(),saveUserModel.getModifiedDate(),
					saveUserModel.getAuthorityId(),saveUserModel.getGenderId());
		}
		
	}

	@Override
	public List<MstUserModel> search(MstUserModel mstUserModel) {
		StringBuilder sql = new StringBuilder("SELECT u.user_id,u.password,u.family_name,u.first_name,u.gender_id,u.age,u.authority_id,u.\"admin\",u.create_user_id,u.update_user_id,u.create_date,u.update_date,e.gender_name,r.authority_name FROM mst_user as u");
		sql.append(" left join mst_gender as e on u.gender_id = e.gender_id");
		sql.append(" left join mst_role as r on u.authority_id=r.authority_id");
		if (mstUserModel.getFamilyName()!="" && mstUserModel.getFirstName()!="" && mstUserModel.getAuthorityId()!=0) {
			sql.append(" WHERE family_name= ? and first_name=? and u.authority_id=? ORDER BY first_name desc");
			return query(sql.toString(), new MstUserMapper(),mstUserModel.getFamilyName(),mstUserModel.getFirstName(),mstUserModel.getAuthorityId());
			}
		else if (mstUserModel.getFamilyName()!="" && mstUserModel.getFirstName()!="") {
			sql.append(" WHERE family_name= ? and first_name=? ORDER BY first_name desc");
			return query(sql.toString(), new MstUserMapper(),mstUserModel.getFamilyName(),mstUserModel.getFirstName());
			}
		else if (mstUserModel.getFamilyName()!="" && mstUserModel.getAuthorityId()!=0) {
			sql.append(" WHERE family_name= ? and u.authority_id=? ORDER BY first_name desc");
			return query(sql.toString(), new MstUserMapper(),mstUserModel.getFamilyName(),mstUserModel.getAuthorityId());
			}
		else if (mstUserModel.getFirstName()!="" && mstUserModel.getAuthorityId()!=0) {
			sql.append(" WHERE first_name=? and u.authority_id=? ORDER BY first_name desc");
			return query(sql.toString(), new MstUserMapper(),mstUserModel.getFirstName(),mstUserModel.getAuthorityId());
			}
		else if (mstUserModel.getFamilyName()!="") {
			sql.append(" WHERE family_name= ? ORDER BY first_name desc");
			return query(sql.toString(), new MstUserMapper(),mstUserModel.getFamilyName());
			}
		else if (mstUserModel.getFirstName()!="") {
			sql.append(" WHERE first_name= ? ORDER BY first_name desc");
			return query(sql.toString(), new MstUserMapper(),mstUserModel.getFirstName());
			}
		else if (mstUserModel.getAuthorityId()!=0) {
			sql.append(" WHERE u.authority_id=? ORDER BY first_name desc");
			return query(sql.toString(), new MstUserMapper(),mstUserModel.getAuthorityId());
			}
		return null;
	}
}
	
