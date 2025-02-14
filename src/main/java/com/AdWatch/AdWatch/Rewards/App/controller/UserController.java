package com.AdWatch.AdWatch.Rewards.App.controller;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.AdWatch.AdWatch.Rewards.App.Dto.UserLoginDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserLoginResponseDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserProfileDTO;
import com.AdWatch.AdWatch.Rewards.App.Dto.UserRegistrationDTO;
import com.AdWatch.AdWatch.Rewards.App.entity.User;
import com.AdWatch.AdWatch.Rewards.App.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
    private UserService userService;

    // Register User
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        userService.registerUser(userRegistrationDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    // Login User
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> loginUser(@RequestBody UserLoginDTO userLoginDTO) {
        UserLoginResponseDTO userLoginResponseDTO = userService.loginUser(userLoginDTO);
        
        // Log the response for debugging
        System.out.println("Login Response: " + userLoginResponseDTO.getEmail() + ", isAdmin: " + userLoginResponseDTO.getIsAdmin());
        
        return ResponseEntity.ok(userLoginResponseDTO);
    }


    // Get User Profile
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getUserProfile(@PathVariable Long id) {
        UserProfileDTO userDto = userService.getUserProfile(id);
        return ResponseEntity.ok(userDto);
    }


    // Upload Profile Picture
    @PostMapping("/{id}/profile-pic")
    public ResponseEntity<String> uploadProfilePic(
        @PathVariable Long id, 
        @RequestParam("file") MultipartFile file) {
        userService.updateProfilePic(id, file);
        return ResponseEntity.ok("Profile picture uploaded successfully");
    }
    
 // Get User Profile Picture
    @GetMapping("/{id}/profile-pic")
    public ResponseEntity<Resource> getProfilePic(@PathVariable Long id) {
        try {
            // Retrieve the file path stored in the database
            String profilePicUrl = userService.getUserProfilePicUrl(id);

            // Convert the URL into a valid file path
            Path path = Paths.get(profilePicUrl);
            
            // Load the file as a resource
            Resource resource = new UrlResource(path.toUri());
            
            // Return the file resource as an image with the appropriate content type
            return ResponseEntity.ok()
                                 .header("Content-Type", "image/jpeg") // Change based on your image type
                                 .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body(null);  // In case of error, return a 500 status
        }
    }


}
