package com.dainc.model;

public class MstUserModel extends AbstractModel<MstUserModel> {
	private String userId;
	private String familyName;
	private String firstName;
	private int age;
	private int genderId;
	private int authorityId;
	private int admin;
	private String password;
	private MstRoleModel mstRoleModel = new MstRoleModel();
	private MstGenderModel mstGenderModel = new MstGenderModel();
	
	public MstRoleModel getMstRoleModel() {
		return mstRoleModel;
	}
	public void setMstRoleModel(MstRoleModel mstRoleModel) {
		this.mstRoleModel = mstRoleModel;
	}
	public MstGenderModel getMstGenderModel() {
		return mstGenderModel;
	}
	public void setMstGenderModel(MstGenderModel mstGenderModel) {
		this.mstGenderModel = mstGenderModel;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getGenderId() {
		return genderId;
	}
	public void setGenderId(int genderId) {
		this.genderId = genderId;
	}
	public int getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
