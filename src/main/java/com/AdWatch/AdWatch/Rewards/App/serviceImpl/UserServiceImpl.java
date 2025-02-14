package com.AdWatch.AdWatch.Rewards.App.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.AdWatch.AdWatch.Rewards.App.Dto.UserLoginDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserLoginResponseDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserProfileDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserRegistrationDTO;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.repository.UserRepository;
import com.AdWatch.AdWatch.Rewards.App.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${profile.pic.upload-dir}")
	private String uploadDir;


    @Autowired
    private UserRepository userRepository;
    
	/*
	 * @Autowired private BCryptPasswordEncoder passwordEncoder;
	 */
    @Override
    public User registerUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.findByEmail(userRegistrationDTO.getEmail()) != null) {
            throw new IllegalArgumentException("Email already in use");
        }
        
		/*
		 * String hashedPassword =
		 * passwordEncoder.encode(userRegistrationDTO.getPassword()); User user = new
		 * User(userRegistrationDTO.getName(), userRegistrationDTO.getEmail(),
		 * hashedPassword);
		 */
        
     // Generate a unique referral code (you can use UUID or other custom logic)
        String referralCode = generateReferralCode();
        
        User user = new User(userRegistrationDTO.getName(), 
                userRegistrationDTO.getEmail(), 
                userRegistrationDTO.getPassword(),
                referralCode,
                userRegistrationDTO.getReferredBy());
                userRegistrationDTO.getProfilePicUrl();
        
        return userRepository.save(user);
    }

    @Override
    public UserLoginResponseDTO loginUser(UserLoginDTO userLoginDTO) {
        String email = userLoginDTO.getEmail();
        String password = userLoginDTO.getPassword();
        
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        
        // Define Static Admin Credentials
        String adminEmail = "admin@example.com";
        String adminPassword = "Admin@123";
        
        // Check if the user is the admin
        if (email.equals(adminEmail) && password.equals(adminPassword)) {
            return new UserLoginResponseDTO(adminEmail, "Admin", -1L, true); // -1 as dummy ID, true for isAdmin
        }

        User user = userRepository.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // On successful login, return the user data with isAdmin = false
        return new UserLoginResponseDTO(user.getEmail(), user.getName(), user.getId(), false);
    }


    @Override
    public UserProfileDTO getUserProfile(Long userId) {
        User user =  userRepository.findById(userId)
                             .orElseThrow(() -> new IllegalArgumentException("User not found"));
     // Convert the User entity to a UserProfileDTO
        
        return new UserProfileDTO(user.getId(), user.getName(), user.getEmail(), user.getCoins(), user.getAdsWatched(), user.getReferralCode(), user.getReferredBy(), user.getProfilePicUrl(),user.isBlocked());

    }
    
 // Referral code generation method
    private String generateReferralCode() {
        return UUID.randomUUID().toString().substring(0, 8); // Creates a random 8-character referral code
    }

	@Override
	public Optional<User> getUserById(Long userId) {
		
		return userRepository.findById(userId);
		
	}
	
	
	@Override
	public String updateProfilePic(Long userId, MultipartFile file) {
	    try {
	        // Use a relative path within the static folder
	        Path uploadPath = Paths.get("src/main/resources/static/profile-pics/");
	        
	        // Create the directory if it doesn't exist
	        if (!Files.exists(uploadPath)) {
	            Files.createDirectories(uploadPath);
	        }

	        // Generate a unique file name and store it
	        String fileName = userId + "_" + file.getOriginalFilename();
	        Path filePath = uploadPath.resolve(fileName);

	        // Save the file
	        Files.copy(file.getInputStream(), filePath);

	        // Save the relative file path to the database
	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found"));
	        user.setProfilePicUrl("/profile-pics/" + fileName);
	        userRepository.save(user);

	        return user.getProfilePicUrl(); // Return the relative URL for serving
	    } catch (IOException e) {
	        throw new RuntimeException("Failed to upload profile picture", e);
	    }
	}

	
	@Override
	public String getUserProfilePicUrl(Long userId) {
	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));
	    
	    // Make sure the path is correctly formatted. Use Paths.get() to handle relative paths.
	    String profilePicPath = user.getProfilePicUrl();
	    Path path = Paths.get(profilePicPath);  // This resolves the file path correctly
	    
	    // Ensure the file exists and can be served
	    if (Files.exists(path)) {
	        return path.toUri().toString(); // Return the valid URI for serving the image
	    } else {
	        throw new RuntimeException("Profile picture not found");
	    }
	}

	

}
