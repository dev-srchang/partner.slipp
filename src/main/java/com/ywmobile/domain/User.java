package com.ywmobile.domain;

public class User {
	String partnerId;
	String pwd;
	String partnerName;
	String email;

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [partnerId = " + partnerId + ", pwd = " + pwd + ", partnerName = " + partnerName + ", email = " + email + "]";
	}
}
