package com.ywmobile.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false, length = 20)
	@Getter
	@Setter
	String partnerId;

	@Column(nullable = false)
	@Getter
	@Setter
	String pwd;

	@Column(nullable = false)
	@Getter
	@Setter
	String partnerName;

	@Getter
	@Setter
	String email;

	public void update(User newUser) {
		this.partnerId = newUser.partnerId;
		this.pwd = newUser.pwd;
		this.partnerName = newUser.partnerName;
		this.email = newUser.email;
	}

	@Override
	public String toString() {
		return "User [partnerId = " + partnerId + ", pwd = " + pwd + ", partnerName = " + partnerName + ", email = " + email + "]";
	}

}
