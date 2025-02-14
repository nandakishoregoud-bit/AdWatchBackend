package com.AdWatch.AdWatch.Rewards.App.Dto;

public class UserProfileDTO {

    private Long id;
    private String name;
    private String email;
    private double coins;
    private int adsWatched;
    private String referralCode;
    private Long referredBy;
    private String profilePicUrl;
    private boolean blocked;  // New field for blocked status

    public UserProfileDTO(Long id, String name, String email, double coins, int adsWatched, String referralCode, Long referredBy, String profilePicUrl, boolean blocked) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.coins = coins;
        this.adsWatched = adsWatched;
        this.referralCode = referralCode;
        this.referredBy = referredBy;
        this.profilePicUrl = profilePicUrl;
        this.blocked = blocked; // Assigning blocked status
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public double getCoins() { return coins; }
    public void setCoins(double coins) { this.coins = coins; }

    public int getAdsWatched() { return adsWatched; }
    public void setAdsWatched(int adsWatched) { this.adsWatched = adsWatched; }

    public String getReferralCode() { return referralCode; }
    public void setReferralCode(String referralCode) { this.referralCode = referralCode; }

    public Long getReferredBy() { return referredBy; }
    public void setReferredBy(Long referredBy) { this.referredBy = referredBy; }

    public String getProfilePicUrl() { return profilePicUrl; }
    public void setProfilePicUrl(String profilePicUrl) { this.profilePicUrl = profilePicUrl; }

    public boolean isBlocked() { return blocked; }
    public void setBlocked(boolean blocked) { this.blocked = blocked; }
}
