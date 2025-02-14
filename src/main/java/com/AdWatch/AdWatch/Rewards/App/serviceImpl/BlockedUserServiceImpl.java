package com.AdWatch.AdWatch.Rewards.App.serviceImpl;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.stereotype.Service;

import com.AdWatch.AdWatch.Rewards.App.entity.BlockedUser;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.repository.BlockedUserRepository;
import com.AdWatch.AdWatch.Rewards.App.service.BlockedUserService;

@Service
public class BlockedUserServiceImpl implements BlockedUserService {

	private  BlockedUserRepository blockedUserRepository;

	public BlockedUserServiceImpl(BlockedUserRepository blockedUserRepository) {
        this.blockedUserRepository = blockedUserRepository;
    }
    
    
	@Override
	 @Transactional
	public void blockUser(User owner, User user) {
		if (!blockedUserRepository.existsByOwnerAndBlockedUser(owner, user)) {
            BlockedUser blockedUser = new BlockedUser();
            blockedUser.setOwner(owner);
            blockedUser.setBlockedUser(user);
            blockedUserRepository.save(blockedUser);
        }
	}
	@Override
	 @Transactional
	public void unblockUser(User owner, User user) {
        if (blockedUserRepository.existsByOwnerAndBlockedUser(owner, user)) {
            blockedUserRepository.deleteByOwnerAndBlockedUser(owner, user);
        }
    }

}
