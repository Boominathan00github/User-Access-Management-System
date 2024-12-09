package com.myproject.dao;

public class Software {

	private int softWareId;
	private String softwareName;
	private String description;
	private String accessLevels;
	
	
	
	public Software(int softWareId, String softwareName, String description, String accessLevels) {
		
		this.softWareId = softWareId;
		this.softwareName = softwareName;
		this.description = description;
		this.accessLevels = accessLevels;
	}
	
	
	
	@Override
	public String toString() {
		return "Software [softWareId=" + softWareId + ", softwareName=" + softwareName + ", description=" + description
				+ ", accessLevels=" + accessLevels + "]";
	}



	public int getSoftWareId() {
		return softWareId;
	}
	public void setSoftWareId(int softWareId) {
		this.softWareId = softWareId;
	}
	public String getSoftwareName() {
		return softwareName;
	}
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAccessLevels() {
		return accessLevels;
	}
	public void setAccessLevels(String accessLevels) {
		this.accessLevels = accessLevels;
	}
	
	
}
