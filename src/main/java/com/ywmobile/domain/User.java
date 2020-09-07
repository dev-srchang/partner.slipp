package com.ywmobile.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
public class User {
	@Column(nullable = false)
	@Setter
	@JsonProperty
	String email;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty
	private Long id;

	@Column(nullable = false, length = 20, unique = true)
	@Getter
	@Setter
	@JsonProperty
	String partnerId;

	@Column(nullable = false)
	@Getter
	@JsonProperty
	String partnerName;

	@Column(nullable = false)
	@Setter
	String pwd;

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		User other = (User) obj;

		if (id == null) {

			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
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

	@Override
	public String toString() {
		return "Partner [id = " + id + ", partnerId = " + partnerId + ", pwd = " + pwd + ", partnerName = " + partnerName + ", email = " + email
				+ "]";
	}

	public void update(User newUser) {
		this.partnerId = newUser.partnerId;
		this.pwd = newUser.pwd;
		this.partnerName = newUser.partnerName;
		this.email = newUser.email;
	}
}
