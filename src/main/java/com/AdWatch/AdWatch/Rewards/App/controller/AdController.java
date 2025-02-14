package com.AdWatch.AdWatch.Rewards.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AdWatch.AdWatch.Rewards.App.service.AdService;

@RestController
@RequestMapping("/api/ads")
public class AdController {

    @Autowired
    private AdService adService;

    // Record Ad Watch
    @PostMapping("/watch")
    public ResponseEntity<String> recordAdWatch(@RequestParam Long userId, @RequestParam int adDuration) {
        System.out.println("Request received at /api/ads/watch");
        System.out.println("Received userId: " + userId + ", adDuration: " + adDuration);

        if (userId == null || adDuration <= 0) {
            return ResponseEntity.badRequest().body("Invalid input parameters");
        }

        if (adService.hasReachedDailyLimit(userId)) {
            return ResponseEntity.badRequest().body("Daily ad watch limit reached (50 ads per day).");
        }

        if (adService.isWatchingTooFast(userId)) {
            return ResponseEntity.badRequest().body("You must wait before watching another ad.");
        }

        try {
            adService.recordAdWatch(userId, adDuration);
            return ResponseEntity.ok("Ad watch recorded and points credited");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to record ad watch.");
        }
    }




    
	/*
	 * @PostMapping("/api/ads/reward") public ResponseEntity<String>
	 * rewardAdWatch(@RequestParam Long userId, @RequestParam int coinsEarned) { //
	 * Logic to add coins to the user account userService.addCoinsToUser(userId,
	 * coinsEarned); return ResponseEntity.ok("Coins added successfully"); }
	 */
    
    private void sendRewardToBackend(long userId, int coinsEarned) {
        String url = "http://your-spring-boot-api/api/ads/reward?userId=" + userId + "&coinsEarned=" + coinsEarned;
        // Use a network library (like Retrofit or OkHttp) to send the request to the backend
    }
    
}
