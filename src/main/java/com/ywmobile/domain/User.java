package com.ywmobile.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 20)
	String partnerId;

	@Column(nullable = false)
	String pwd;

	@Column(nullable = false)
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
