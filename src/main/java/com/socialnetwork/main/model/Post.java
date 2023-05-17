package com.socialnetwork.main.model;


import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;




@Entity
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	 @Column(name = "title")
	    private String title;
	 @Column(name = "username")
	 private String userName;

		@Column(name = "content")
	    private String content;

	 @Lob
	    @Column(name = "image_data")
	    private byte[] imageData;

	
	private LocalDateTime postedDate;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(long id, String title, String userName, String content, byte[] imageData, LocalDateTime postedDate) {
		super();
		this.id = id;
		this.title = title;
		this.userName = userName;
		this.content = content;
		this.imageData = imageData;
		this.postedDate = postedDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public LocalDateTime getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(LocalDateTime postedDate) {
		this.postedDate = postedDate;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", userName=" + userName + ", content=" + content
				+ ", imageData=" + Arrays.toString(imageData) + ", postedDate=" + postedDate + "]";
	}

	

}