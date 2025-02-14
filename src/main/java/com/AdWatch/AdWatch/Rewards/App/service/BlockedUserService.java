package com.AdWatch.AdWatch.Rewards.App.service;

import com.AdWatch.AdWatch.Rewards.App.entity.User;

public interface BlockedUserService {

	public void blockUser(User owner, User user);
	
	public void unblockUser(User owner, User user);
}
