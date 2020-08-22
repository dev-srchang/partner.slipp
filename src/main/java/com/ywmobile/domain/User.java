package com.ywmobile.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Setter;

@Entity
@Table(name = "USER")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20, unique = true)
	@Setter
	String partnerId;

	@Column(nullable = false)
	@Setter
	String pwd;

	@Column(nullable = false)
	@Setter
	String partnerName;

	@Column(nullable = false)
	@Setter
	String email;

	@Override
	public String toString() {
		return "User [partnerId = " + partnerId + ", pwd = " + pwd + ", partnerName = " + partnerName + ", email = " + email + "]";
	}
	
	public boolean matchId(Long newId) {
		if (newId == null) {
			return false;
		}
		
		return id.equals(newId);
	}
	
	public boolean matchPwd(String newPwd) {
		if (newPwd == null) {
			return false;
		}
		
		return pwd.equals(newPwd);
	}
	
	public void update(User newUser) {
		this.partnerId = newUser.partnerId;
		this.pwd = newUser.pwd;
		this.partnerName = newUser.partnerName;
		this.email = newUser.email;
	}
}
