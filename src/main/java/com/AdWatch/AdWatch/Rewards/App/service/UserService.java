package com.AdWatch.AdWatch.Rewards.App.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.AdWatch.AdWatch.Rewards.App.Dto.UserLoginDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserLoginResponseDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserProfileDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserRegistrationDTO;
import com.AdWatch.AdWatch.Rewards.App.entity.User;

public interface UserService {
	
    User registerUser(UserRegistrationDTO userRegistrationDTO);
    
    UserProfileDTO getUserProfile(Long userId);
	UserLoginResponseDTO loginUser(UserLoginDTO userLoginDTO);
	
	public Optional<User> getUserById(Long userId);
	
	public String updateProfilePic(Long userId, MultipartFile file);
	
	public String getUserProfilePicUrl(Long userId);

    
}
