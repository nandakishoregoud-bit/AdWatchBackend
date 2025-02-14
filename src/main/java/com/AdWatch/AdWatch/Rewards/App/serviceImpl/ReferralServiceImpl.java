package com.AdWatch.AdWatch.Rewards.App.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdWatch.AdWatch.Rewards.App.entity.Referral;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.repository.ReferralRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.UserRepository;
import com.AdWatch.AdWatch.Rewards.App.service.ReferralService;

@Service
public class ReferralServiceImpl implements ReferralService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReferralRepository referralRepository;


    public void trackReferral(Long referrerId, Long referredUserId) {
        User referrer = userRepository.findById(referrerId)
                                      .orElseThrow(() -> new IllegalArgumentException("Referrer not found"));
        User referredUser = userRepository.findById(referredUserId)
                                          .orElseThrow(() -> new IllegalArgumentException("Referred user not found"));

        Referral referral = new Referral(referrer, referredUser);
        referral.setBonusPoints(100); // Set the bonus points for the referral

        referralRepository.save(referral);  // Save the referral
}
}