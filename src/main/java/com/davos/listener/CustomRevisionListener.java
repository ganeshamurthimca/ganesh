/**
 * 
 */
package com.davos.listener;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.davos.domain.CustomRevInfo;

/**
 * @author vigneshwaran.b
 *
 */
public class CustomRevisionListener implements EntityTrackingRevisionListener {

	@Override
	public void newRevision(Object revisionEntity) {
		CustomRevInfo customRevInfo = (CustomRevInfo) revisionEntity;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			customRevInfo.setUserName(authentication.getName());
		} else {
			customRevInfo.setUserName("admin");
		}
	}

	@Override
	public void entityChanged(@SuppressWarnings("rawtypes") Class entityClass, String entityName, Serializable entityId,
			RevisionType revisionType, Object revisionEntity) {
		((CustomRevInfo) revisionEntity).setTableName(entityName);
	}

}
