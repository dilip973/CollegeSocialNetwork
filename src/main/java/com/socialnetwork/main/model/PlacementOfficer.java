package com.socialnetwork.main.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PlacementOfficer {
	
	@Id
	@GeneratedValue
	private Integer studentId;
	private String requirements;
	private String placeStudent;
	
	
	public PlacementOfficer() {
		super();
	}


	public PlacementOfficer(Integer studentId, String requirements, String placeStudent) {
		super();
		this.studentId = studentId;
		this.requirements = requirements;
		this.placeStudent = placeStudent;
	}


	public Integer getStudentId() {
		return studentId;
	}


	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}


	public String getRequirements() {
		return requirements;
	}


	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}


	public String getPlaceStudent() {
		return placeStudent;
	}


	public void setPlaceStudent(String placeStudent) {
		this.placeStudent = placeStudent;
	}


}