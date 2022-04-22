package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.FollowData;
import com.app.repository.FollowRepository;
import com.app.service.FollowDataService;


//to get follow , followers and might know
@RestController
@RequestMapping("/user")
public class FollowDataController {
	
	 @Autowired
	 private FollowDataService followDataService ;
	
	 
	 //to get a follower of user
	@GetMapping("{userId}/follower")
	public ResponseEntity<List<FollowData>> follower(@PathVariable("userId") int userId){
		List<FollowData> list = followDataService.findByUserId(userId);
		return ResponseEntity.ok(list);
		
	}
	
	
	//to get following of user
	@GetMapping("{userId}/following")
	public ResponseEntity<?> following(@PathVariable("userId") int userId){
		List<FollowData> following = followDataService.getFollowing(userId);
		return ResponseEntity.ok(following);
	}
	
	
	//to get mightKnown of the user
	@GetMapping("{userId}/mightKnown")
	public ResponseEntity<?> mightKnown(@PathVariable("userId") int userId){
		
		//1st get a all following of user
		List<FollowData> following = followDataService.getFollowing(userId);
		List<FollowData> data = new ArrayList<FollowData>();
		
		//then the get the following list of user  following 
		for (FollowData followData : following) {
				//might known
				List<FollowData> getknown = followDataService.mightKnown(followData.getUser().getUserId());
				
				//to get the list but not a user details bcoz its may following the user back 
				for (FollowData followData2 : getknown ) {
					if(followData2.getUserId() == userId) {
						
					}else {
						data.add(followData2);
					}
				}
		}
		
		return ResponseEntity.ok(data);
	}
}
