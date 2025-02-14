package com.AdWatch.AdWatch.Rewards.App.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AdWatch.AdWatch.Rewards.App.service.ReferralService;

@RestController
@RequestMapping("/api/referrals")
public class ReferralController {

    @Autowired
    private ReferralService referralService;

    // Track Referral
    @PostMapping("/track")
    public ResponseEntity<String> trackReferral(@RequestParam Long referrerId, @RequestParam Long referredUserId) {
        referralService.trackReferral(referrerId, referredUserId);
        return ResponseEntity.ok("Referral tracked successfully");
    }
}
