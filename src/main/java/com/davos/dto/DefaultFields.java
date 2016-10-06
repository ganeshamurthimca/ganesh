/**
 * 
 */
package com.davos.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @author vigneshwaran.b
 *
 */
public class DefaultFields implements Serializable {
	private static final long serialVersionUID = 1L;
	protected UserDTO actionBy;
	protected Date actionOn;
	protected Boolean isEnabled = Boolean.TRUE;

	public UserDTO getActionBy() {
		return actionBy;
	}

	public void setActionBy(UserDTO actionBy) {
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
