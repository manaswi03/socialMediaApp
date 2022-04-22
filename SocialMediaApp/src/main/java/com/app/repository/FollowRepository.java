package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.FollowData;

public interface FollowRepository extends JpaRepository<FollowData,Integer> {
	
	/*
	 * @Query("From FollowData where user.userId = :userId") public List<FollowData>
	 * follower(int userId);
	 */
	
	//to find user by userid
	public List<FollowData> findByUserId(int uid);
	
	//to get the list of following  
	@Query("From FollowData f  where f.userId = ?1 and f.following = 1")
	public List<FollowData> getFollowing(int uid);
	
	
	//to get the might known people
	@Query("from FollowData f where user.userId = ?1 or userId = ?1 and following = 1")
	public List<FollowData> mightKnown(int uid);
	
	

}
