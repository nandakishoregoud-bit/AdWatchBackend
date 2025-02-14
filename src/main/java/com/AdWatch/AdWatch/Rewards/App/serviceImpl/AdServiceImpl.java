package com.AdWatch.AdWatch.Rewards.App.serviceImpl;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdWatch.AdWatch.Rewards.App.entity.Ad;
import com.AdWatch.AdWatch.Rewards.App.entity.Transaction;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.repository.AdRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.TransactionRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.UserRepository;
import com.AdWatch.AdWatch.Rewards.App.service.AdService;

@Service
public class AdServiceImpl implements AdService {
	
	private static final long MIN_TIME_GAP_SECONDS = 5;

    @Autowired
    private AdRepository adRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository; // Make sure this is injected

    
    @Override
    public List<Ad> getAllAds() {
        return adRepository.findAll();
    }

    @Override
    public Ad getAdById(Long adId) {
        return adRepository.findById(adId)
                           .orElseThrow(() -> new IllegalArgumentException("Ad not found"));
    }

    @Override
    public void recordAdWatch(Long userId, int adDuration) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        user.setAdsWatched(user.getAdsWatched() + 1);
        
        // Calculate points based on the ad duration (e.g., 1 point per second)
        int pointsEarned = adDuration /3 ;
        
     // ✅ Convert points to dollars (10 points = $0.001)
        double dollarsEarned = pointsEarned / 10000.0;
        
        
        user.setCoins(user.getCoins() + dollarsEarned);
        
        userRepository.save(user);
        
     // Create a new transaction record
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setType("WATCH_AD"); // Transaction type: WATCH_AD
        transaction.setAmount(dollarsEarned); // Coins rewarded for watching the ad
        transaction.setTimestamp(LocalDateTime.now());

        // Save transaction
        transactionRepository.save(transaction);
    }
    
    @Override
    public boolean hasReachedDailyLimit(Long userId) {
        LocalDate today = LocalDate.now();

        // Count how many ads the user has watched today
        long adCount = transactionRepository.countByUserIdAndTypeAndTimestampBetween(
            userId,
            today.atStartOfDay(),  // Start of the day
            today.plusDays(1).atStartOfDay()  // Start of the next day
        );

        return adCount >= 50;
    }
    
    @Override
    public boolean isWatchingTooFast(Long userId) {
        // Get the most recent ad watch timestamp for the user
        LocalDateTime lastAdTime = transactionRepository.findLastAdWatchTime(userId);

        if (lastAdTime == null) {
            return false; // No previous ads watched, allow the request
        }

        // Calculate the time difference
        long secondsSinceLastAd = Duration.between(lastAdTime, LocalDateTime.now()).getSeconds();
        return secondsSinceLastAd < MIN_TIME_GAP_SECONDS;
    }
    
}

