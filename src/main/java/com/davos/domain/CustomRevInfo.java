/**
 * 
 */
package com.davos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import com.davos.listener.CustomRevisionListener;

/**
 * @author vigneshwaran.b
 *
 */
@Entity(name = "revinfo")
@RevisionEntity(value = CustomRevisionListener.class)
public class CustomRevInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@RevisionNumber
	Long id;
	@RevisionTimestamp
	Long timestamp;
	String userName;
	String tableName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public Date getRevisionDate() {
		return new Date(timestamp);
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getUserName() {
		return userName;
	}

	@Override
	public String toString() {
		return "CustomRevInfo [id=" + id + ", timestamp=" + timestamp + ", userName=" + userName + ",  tableName="
				+ tableName + "]";
	}

}
