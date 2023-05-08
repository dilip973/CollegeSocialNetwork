package com.socialnetwork.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Staff {
	@Id
	@GeneratedValue
	private Integer staffId;
	private String staffName;
	private String staffBranch;
	private String staffEmailId;
	
	@ManyToOne
	private EventManagement eventManagement;
	
	public Staff() {
		super();
	}

	public Staff(Integer staffId, String staffName, String staffBranch, String staffEmailId,
			EventManagement eventManagement) {
		super();
		this.staffId = staffId;
		this.staffName = staffName;
		this.staffBranch = staffBranch;
		this.staffEmailId = staffEmailId;
		this.eventManagement = eventManagement;
	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffBranch() {
		return staffBranch;
	}

	public void setStaffBranch(String staffBranch) {
		this.staffBranch = staffBranch;
	}

	public String getStaffEmailId() {
		return staffEmailId;
	}

	public void setStaffEmailId(String staffEmailId) {
		this.staffEmailId = staffEmailId;
	}

	public EventManagement getEventManagement() {
		return eventManagement;
	}

	public void setEventManagement(EventManagement eventManagement) {
		this.eventManagement = eventManagement;
	}

	
}


