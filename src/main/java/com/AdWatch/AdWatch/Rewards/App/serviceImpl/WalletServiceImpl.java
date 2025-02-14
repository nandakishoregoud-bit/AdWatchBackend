package com.AdWatch.AdWatch.Rewards.App.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AdWatch.AdWatch.Rewards.App.Dto.RewardDto;
import com.AdWatch.AdWatch.Rewards.App.entity.PaymentDetails;
import com.AdWatch.AdWatch.Rewards.App.entity.Reward;
import com.AdWatch.AdWatch.Rewards.App.entity.Transaction;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.repository.PaymentDetailsRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.RewardRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.TransactionRepository;
import com.AdWatch.AdWatch.Rewards.App.repository.UserRepository;
import com.AdWatch.AdWatch.Rewards.App.service.EmailService;
import com.AdWatch.AdWatch.Rewards.App.service.WalletService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardRepository rewardRepository;
    
    @Autowired
    private PaymentDetailsRepository paymentDetailsRepository;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private TransactionRepository transactionRepository; // Make sure this is injected

    private static final Logger log = LoggerFactory.getLogger(WalletServiceImpl.class);

    
    @Override
    public double getWalletBalance(Long userId) {
        return userRepository.findById(userId)
                             .orElseThrow(() -> new IllegalArgumentException("User not found"))
                             .getCoins();
    }

	/*
	 * @Override public void redeemPoints(Long userId, int points, String
	 * rewardType) { try { // Fetch the user from the database User user =
	 * userRepository.findById(userId) .orElseThrow(() -> new
	 * IllegalArgumentException("User not found")); log.info("User found: {}",
	 * user.getName());
	 * 
	 * // Check if the user has at least 1000 coins before redeeming if
	 * (user.getCoins() < 1000) {
	 * log.warn("Insufficient coins: User has {} coins, trying to redeem {} coins",
	 * user.getCoins(), points); throw new
	 * IllegalArgumentException("You need at least 1000 coins to redeem rewards.");
	 * }
	 * 
	 * // Deduct points from user wallet user.setCoins(user.getCoins() - points);
	 * userRepository.save(user); log.info("User's new balance: {}",
	 * user.getCoins());
	 * 
	 * // Create and save the reward Reward reward = new Reward(user, rewardType,
	 * points, LocalDateTime.now(), "PENDING"); rewardRepository.save(reward);
	 * 
	 * // Create and save the transaction Transaction transaction = new
	 * Transaction(); transaction.setUser(user); transaction.setType("REDEEM");
	 * transaction.setAmount(-points);
	 * transaction.setTimestamp(LocalDateTime.now());
	 * transactionRepository.save(transaction);
	 * 
	 * // Send an email based on the reward type String emailSubject =
	 * "Reward Redemption Request"; String emailBody;
	 * 
	 * if ("PayPal".equalsIgnoreCase(rewardType)) { emailBody = String.format(
	 * "User %s (%s) has requested a PayPal redemption.\nPoints Redeemed: %d\nCurrent Balance: %d\nTimestamp: %s"
	 * , user.getName(), user.getEmail(), points, user.getCoins(),
	 * LocalDateTime.now() ); } else { emailBody = String.format(
	 * "User %s (%s) has requested a %s redemption.\nPoints Redeemed: %d\nCurrent Balance: %d\nTimestamp: %s"
	 * , user.getName(), user.getEmail(), rewardType, points, user.getCoins(),
	 * LocalDateTime.now() ); }
	 * 
	 * // Send email to the administrator
	 * emailService.sendEmail("krishoregoud@gmail.com", emailSubject, emailBody);
	 * log.info("{} redemption email sent for user: {}", rewardType,
	 * user.getName());
	 * 
	 * } catch (Exception e) { log.error("Error during points redemption process",
	 * e); throw e; // Re-throw to let the controller handle the error } }
	 * 
	 */
    

    @Override
    public List<RewardDto> getRewardHistory(Long userId) {
        List<Reward> rewards = rewardRepository.findAllByUserId(userId);

        // Convert the List<Reward> to List<RewardDto>
        List<RewardDto> rewardDtos = rewards.stream()
                                            .map(reward -> new RewardDto(reward))
                                            .collect(Collectors.toList());

        return rewardDtos;
    }

    @Override
    public void redeemPoint(
            Long userId,
            Double points,
            String rewardType,
            String upiId,
            String bankAccountNumber,
            String bankIFSC,
            String paypalEmail,
            String amazonPayNumber) {
        try {
            // Fetch the user
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            // Check if user has enough coins
            if (user.getCoins() < 1) {
                throw new IllegalArgumentException("You need at least 1$  to redeem rewards.");
            }

            // Fetch existing payment details
            PaymentDetails existingDetails = paymentDetailsRepository.findByUser(user);

            if (existingDetails == null) {
                // If no existing payment details, create new
                existingDetails = new PaymentDetails();
                existingDetails.setUser(user);
            }

            // Merge new details with existing details
            if (upiId != null && !upiId.isEmpty()) {
                existingDetails.setUpiId(upiId);
            }
            if (bankAccountNumber != null && !bankAccountNumber.isEmpty()) {
                existingDetails.setBankAccountNumber(bankAccountNumber);
            }
            if (bankIFSC != null && !bankIFSC.isEmpty()) {
                existingDetails.setBankIFSC(bankIFSC);
            }
            if (paypalEmail != null && !paypalEmail.isEmpty()) {
                existingDetails.setPaypalEmail(paypalEmail);
            }
            if (amazonPayNumber != null && !amazonPayNumber.isEmpty()) {
                existingDetails.setAmazonPayNumber(amazonPayNumber);
            }

            existingDetails.setRewardType(rewardType);

            // Save the updated or new payment details
            paymentDetailsRepository.save(existingDetails);

            // Deduct points
            user.setCoins(user.getCoins() - points);
            userRepository.save(user);

            // Create reward and transaction records
            Reward reward = new Reward(user, rewardType, points, LocalDateTime.now(), "PENDING");
            rewardRepository.save(reward);

            Transaction transaction = new Transaction();
            transaction.setUser(user);
            transaction.setType("REDEEM");
            transaction.setAmount(-points);
            transaction.setTimestamp(LocalDateTime.now());
            transactionRepository.save(transaction);

            // Prepare payment details string for email
            String paymentDetailsInfo = String.format(
                    "UPI ID: %s\nBank Account: %s\nIFSC Code: %s\nPayPal Email: %s\nAmazon Pay Number: %s\nReward Type: %s",
                    existingDetails.getUpiId() != null ? existingDetails.getUpiId() : "N/A",
                    existingDetails.getBankAccountNumber() != null ? existingDetails.getBankAccountNumber() : "N/A",
                    existingDetails.getBankIFSC() != null ? existingDetails.getBankIFSC() : "N/A",
                    existingDetails.getPaypalEmail() != null ? existingDetails.getPaypalEmail() : "N/A",
                    existingDetails.getAmazonPayNumber() != null ? existingDetails.getAmazonPayNumber() : "N/A",
                    rewardType
            );

            // Send email notification
            String emailBody = String.format(
                    "User %s (%s) has requested a redemption.\n\nReward Type: %s\nPoints Redeemed: %d\nTimestamp: %s\n\nPayment Details:\n%s",
                    user.getName(),
                    user.getEmail(),
                    rewardType,
                    points,
                    LocalDateTime.now(),
                    paymentDetailsInfo
            );

            emailService.sendEmail("krishoregoud@gmail.com", "Redemption Request", emailBody);

        } catch (Exception e) {
            throw new RuntimeException("Error during redemption process", e);
        }
    }


}
