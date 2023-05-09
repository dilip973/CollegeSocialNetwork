package com.socialnetwork.main.model;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Posts {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "post_id")
private Long id;

private String title;

private String content;

@Column(name = "posted_date")
private Date postedDate;

@Column(name = "username")
private String username;

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public String getTitle() {
	return title;
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

public Date getPostedDate() {
	return postedDate;
}

public void setPostedDate(Date postedDate) {
	this.postedDate = postedDate;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

}