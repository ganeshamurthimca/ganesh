/**
 * 
 */
package com.davos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author vigneshwaran.b
 *
 */
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@Audited
public class DefaultFields implements Serializable {
	private static final long serialVersionUID = 1L;

	@CreatedBy
	@JoinColumn(name = "actionBy", referencedColumnName = "userId")
	@ManyToOne
	@JsonProperty("actionBy")
	protected User actionBy;

	@CreatedDate
	@Column(name = "actionOn", length = 10)
	@Temporal(TemporalType.DATE)
	@JsonProperty("actionOn")
	protected Date actionOn = new Date();

	@Column(name = "isEnabled")
	@JsonProperty("isEnabled")
	protected Boolean isEnabled = Boolean.TRUE;

	public User getActionBy() {
		return actionBy;
	}

	public void setActionBy(User actionBy) {
		this.actionBy = actionBy;
	}

	public Date getActionOn() {
		return actionOn;
	}

	public void setActionOn(Date actionOn) {
		this.actionOn = actionOn;
	}

	public Boolean getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}
