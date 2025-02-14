package com.AdWatch.AdWatch.Rewards.App.service;

import java.util.List;

import com.AdWatch.AdWatch.Rewards.App.entity.Ad;

public interface AdService {
	
    List<Ad> getAllAds();
    Ad getAdById(Long adId);
    void recordAdWatch(Long userId, int adDuration);
    
    public boolean hasReachedDailyLimit(Long userId);
    
    public boolean isWatchingTooFast(Long userId);
    
}