package com.app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class FollowData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fid;
	
	private int userId;
	
	@ManyToOne
    @JoinColumn(name = "follwer_id")
	private User user;
	
	
	private int following;


	public int getFid() {
		return fid;
	}


	public void setFid(int fid) {
		this.fid = fid;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public int getFollowing() {
		return following;
	}


	public void setFollowing(int following) {
		this.following = following;
	}


	@Override
	public String toString() {
		return "FollowData [fid=" + fid + ", userId=" + userId + ", user=" + user + ", following=" + following + "]";
	}

	

	
	
	

}
