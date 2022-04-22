package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.FollowData;
import com.app.repository.FollowRepository;

@Service
public class FollowDataServiceimpl implements FollowDataService {
	
	 @Autowired
	 private FollowRepository followRepo;

	@Override
	public List<FollowData> findByUserId(int uid) {
		List<FollowData> findByUserId = followRepo.findByUserId(uid);
		return findByUserId;
	}

	@Override
	public List<FollowData> getFollowing(int uid) {
		List<FollowData> following = followRepo.getFollowing(uid);
		return following;
	}

	@Override
	public List<FollowData> mightKnown(int uid) {
		List<FollowData> mightKnown = followRepo.mightKnown(uid);
		return mightKnown;
	}

}
