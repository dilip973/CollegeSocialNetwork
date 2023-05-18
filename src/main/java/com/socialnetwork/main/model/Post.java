package com.socialnetwork.main.model;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

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
	 private String username;

		@Column(name = "content")
	    private String content;

	 @Lob
	    @Column(name = "image_data")
	    private byte[] imageData;

	
	private Date postedDate;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(long id, String title, String username, String content, byte[] imageData, Date postedDate) {
		super();
		this.id = id;
		this.title = title;
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", username=" + username + ", content=" + content
				+ ", imageData=" + Arrays.toString(imageData) + ", postedDate=" + postedDate + "]";
	}

	

	

}

//@Entity
//@Table(name = "posts")
//public class Post {
//
//@Id
//@GeneratedValue(strategy = GenerationType.IDENTITY)
//@Column(name = "post_id")
//private Long id;
//
//private String title;
//
//private String content;
//
//@Column(name = "posted_date")
//private Date postedDate;
//
//@Column(name = "username")
//private String username;
//
//public Long getId() {
//	return id;
//}
//
//public void setId(Long id) {
//	this.id = id;
//}
//
//public String getTitle() {
//	return title;
//}
//
//public void setTitle(String title) {
//	this.title = title;
//}
//
//public String getContent() {
//	return content;
//}
//
//public void setContent(String content) {
//	this.content = content;
//}
//
//public Date getPostedDate() {
//	return postedDate;
//}
//
//public void setPostedDate(Date postedDate) {
//	this.postedDate = postedDate;
//}
//
//public String getUsername() {
//	return username;
//}
//
//public void setUsername(String username) {
//	this.username = username;
//}
//
//}