package com.dainc.model;

public class MstRoleModel extends AbstractModel<MstRoleModel> {
	private int authorityId;
	private String authorityName;
	public int getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(int authorityId) {
		this.authorityId = authorityId;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

}
