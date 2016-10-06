package com.davos.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "passwordResetInDays")
public class PasswordResetInDays extends DefaultFields {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	private Long passwordResetInDaysId;

	@Column(name = "passwordResetInDays")
	private Long passwordResetInDays;

	public PasswordResetInDays() {

	}

	public PasswordResetInDays(Long passwordResetInDaysId) {
		this.passwordResetInDaysId = passwordResetInDaysId;
	}

	public Long getPasswordResetInDaysId() {
		return passwordResetInDaysId;
	}

	public void setPasswordResetInDaysId(Long passwordResetInDaysId) {
		this.passwordResetInDaysId = passwordResetInDaysId;
	}

	public Long getPasswordResetInDays() {
		return passwordResetInDays;
	}

	public void setPasswordResetInDays(Long passwordResetInDays) {
		this.passwordResetInDays = passwordResetInDays;
	}

}
