package com.processor.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * when a domain object is persistent then most extends this base object
 * 
 * @author carlosmontoya
 *
 */
public class BaseObject {

	private String id;
	private Map<String, Object> mapProperties = new HashMap<String, Object>();
	private Date dbCreation; 
	private Date dbUpdate; 
	private int dateOffset;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void put(String key, Object value){
		mapProperties.put(key, value);
	}
	
	public Object get(String key){
		return mapProperties.get(key);
	}

	public Map<String, Object> getMapProperties() {
		return mapProperties;
	}

	public void setMapProperties(Map<String, Object> mapProperties) {
		this.mapProperties = mapProperties;
	}

	public Date getDbCreation() {
		return dbCreation;
	}

	public void setDbCreation(Date dbCreationDate) {
		this.dbCreation = dbCreationDate;
	}

	public Date getDbUpdate() {
		return dbUpdate;
	}

	public void setDbUpdate(Date dbUpdateDate) {
		this.dbUpdate = dbUpdateDate;
	}

	public int getDateOffset() {
		return dateOffset;
	}

	public void setDateOffset(int dateOffset) {
		this.dateOffset = dateOffset;
	}
	
}
