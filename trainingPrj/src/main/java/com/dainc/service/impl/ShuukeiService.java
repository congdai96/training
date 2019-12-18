package com.dainc.service.impl;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import com.dainc.dao.IMstUserDAO;
import com.dainc.dao.IShuukeiDAO;
import com.dainc.model.MstUserModel;
import com.dainc.model.ShuukeiModel;
import com.dainc.service.IShuukeiService;

public class ShuukeiService implements IShuukeiService {
	
	@Inject
	private IShuukeiDAO shuukeiDAO;
	
	@Inject
	private IMstUserDAO mstUserDAO;
	
	@Override
	public List<ShuukeiModel> roleShuukei() {
		List<ShuukeiModel> result = shuukeiDAO.roleShuukei();
		ShuukeiModel roleNull = new ShuukeiModel();
		roleNull.setRoleName("未登録");
		for (Iterator i = mstUserDAO.findRoleNull().iterator(); i.hasNext();) {
			MstUserModel dataModel = (MstUserModel) i.next();
			if (dataModel.getGenderId() == 1)
				roleNull.setMale(roleNull.getMale() + 1);
			if (dataModel.getGenderId() == 2)
				roleNull.setFemale(roleNull.getFemale() + 1);
			if (dataModel.getAge() < 20 && dataModel.getAge() > 0)
				roleNull.setAgeMax19(roleNull.getAgeMax19() + 1);
			if (dataModel.getAge() >= 20)
				roleNull.setAgeMin20(roleNull.getAgeMin20() + 1);
			if (dataModel.getAge() == 0 || dataModel.getGenderId() == 0)
				roleNull.setNotFull(roleNull.getNotFull() + 1);
			if (dataModel.getAge() == 0)
				roleNull.setNotAge(roleNull.getNotAge() + 1);
		}
		result.add(roleNull);
		return result;
	}


}
