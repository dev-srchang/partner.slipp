package com.ywmobile.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
public class User extends AbstractEntity {
	@Column(nullable = false)
	@Setter
	@JsonProperty
	String email;

	@Column(nullable = false)
	@Setter
	@Getter
	@JsonProperty
	String partnerName;

	@Column(nullable = false)
	@Setter
	String pwd;
	
	@Column(nullable = false, length = 20, unique = true)
	@Getter
	@Setter
	@JsonProperty
	String partnerId;

	public boolean matchId(Long newId) {
		if (newId == null) {
			return false;
		}

		return getId().equals(newId);
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
	
	@Override
	public String toString() {
		return "Partner [" + super.toString() + ", partnerId = " + partnerId + ",  partnerName = " + partnerName + ", email = " + email + "]";
	}
}
