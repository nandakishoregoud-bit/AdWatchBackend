package com.AdWatch.AdWatch.Rewards.App.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AdWatch.AdWatch.Rewards.App.Dto.RewardDto;
import com.AdWatch.AdWatch.Rewards.App.service.WalletService;



@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    // Get Wallet Balance
    @GetMapping("/{userId}")
    public ResponseEntity<Double> getWalletBalance(@PathVariable Long userId) {
    	System.out.println("request recived");
    	double balance = walletService.getWalletBalance(userId);
    	System.out.println("b : " +balance);
        return ResponseEntity.ok(balance);
        
    }

    // Redeem Points
    
    
    @PostMapping("/le/redeem")
    public ResponseEntity<String> redeemPoints(
            @RequestParam Long userId,
            @RequestParam Double points,
            @RequestParam String rewardType,
            @RequestParam(required = false) String upiId,
            @RequestParam(required = false) String bankAccountNumber,
            @RequestParam(required = false) String bankIFSC,
            @RequestParam(required = false) String paypalEmail,
            @RequestParam(required = false) String amazonPayNumber) {
        try {
            // Call the service and pass individual parameters
            walletService.redeemPoint(userId, points, rewardType, upiId, bankAccountNumber, bankIFSC, paypalEmail, amazonPayNumber);
            return ResponseEntity.ok("Redemption request submitted successfully. Payment details saved.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during redemption.");
        }
    }


    
    // Get Reward History
    @GetMapping("/reward-history/{userId}")
    public ResponseEntity<List<RewardDto>> getRewardHistory(@PathVariable Long userId) {
        List<RewardDto> rewardHistory = walletService.getRewardHistory(userId);
        return ResponseEntity.ok(rewardHistory);
    }
}

