package com.AdWatch.AdWatch.Rewards.App.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.service.BlockedUserService;
import com.AdWatch.AdWatch.Rewards.App.service.UserService;

@RestController
@RequestMapping("/api")
public class BlockController {
    private final BlockedUserService blockedUserService;
    private final UserService userService;

    public BlockController(BlockedUserService blockedUserService, UserService userService) {
        this.blockedUserService = blockedUserService;
        this.userService = userService;
    }

    @PostMapping("/block/user/{ownerId}/{blockedUserId}")
    public ResponseEntity<String> blockUser(@PathVariable Long ownerId, @PathVariable Long blockedUserId) {
        User owner = userService.getUserById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        User blockedUser = userService.getUserById(blockedUserId)
                .orElseThrow(() -> new RuntimeException("User to be blocked not found"));

        blockedUserService.blockUser(owner, blockedUser);
        return ResponseEntity.ok("User has been blocked.");
    }
    
    @DeleteMapping("/unblock/user/{ownerId}/{blockedUserId}")
    public ResponseEntity<String> unblockUser(@PathVariable Long ownerId, @PathVariable Long blockedUserId) {
        User owner = userService.getUserById(ownerId)
                .orElseThrow(() -> new RuntimeException("Owner not found"));
        User blockedUser = userService.getUserById(blockedUserId)
                .orElseThrow(() -> new RuntimeException("User to be unblocked not found"));

        blockedUserService.unblockUser(owner, blockedUser);
        return ResponseEntity.ok("User has been unblocked.");
    }
}
