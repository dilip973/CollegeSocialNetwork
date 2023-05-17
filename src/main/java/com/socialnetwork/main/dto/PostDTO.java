package com.socialnetwork.main.dto;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class PostDTO {
    private Long id;
    private String title;
    private String content;
    private String imageData;
   
	private Date postedDate;

    public PostDTO(Long id, String title, String content, String imageData, Date postedDate) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.imageData = imageData;
		this.postedDate = postedDate;
	}

	public PostDTO() {
        // Default constructor
    }

   

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageData() {
        return imageData;
    }

    public void setImageData(String imageData) {
        this.imageData = imageData;
    }
}
