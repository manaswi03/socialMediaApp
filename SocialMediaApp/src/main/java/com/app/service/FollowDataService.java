package com.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.model.FollowData;

@Service
public interface FollowDataService {
	
	public List<FollowData> findByUserId(int uid);
	
	
	public List<FollowData> getFollowing(int uid);
	
	
	public List<FollowData> mightKnown(int uid);

}
