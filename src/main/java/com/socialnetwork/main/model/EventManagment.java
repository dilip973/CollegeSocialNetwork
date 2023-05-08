package com.socialnetwork.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EventManagment {
	@Id
	@GeneratedValue
	private Integer eventID;
	private String eventName;
	private String eventSponser;
	private String venue;
	
	
	
	public EventManagement() {
		super();
	}



	public EventManagement(Integer eventID, String eventName, String eventSponser, String venue) {
		super();
		this.eventID = eventID;
		this.eventName = eventName;
		this.eventSponser = eventSponser;
		this.venue = venue;
	}



	public Integer getEventID() {
		return eventID;
	}



	public void setEventID(Integer eventID) {
		this.eventID = eventID;
	}



	public String getEventName() {
		return eventName;
	}



	public void setEventName(String eventName) {
		this.eventName = eventName;
	}



	public String getEventSponser() {
		return eventSponser;
	}



	public void setEventSponser(String eventSponser) {
		this.eventSponser = eventSponser;
	}



	public String getVenue() {
		return venue;
	}



	public void setVenue(String venue) {
		this.venue = venue;
	}
	

}


