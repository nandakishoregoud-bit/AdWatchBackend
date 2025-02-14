package com.AdWatch.AdWatch.Rewards.App.Dto;

public class UserLoginResponseDTO {
    private String email;
    private String name;
    private Long id;
    private Boolean isAdmin;
    
 // Constructor for Admin
    public UserLoginResponseDTO(String email, String name, long l, Boolean isAdmin) {
        this.email = email;
        this.name = name;
        this.id =  l;
        this.isAdmin = isAdmin;
    }

    // Constructor
    public UserLoginResponseDTO(String email, String name, Long id) {
        
    	this.id = id;
    	this.email = email;
        this.name = name;
        this.isAdmin = false; 
        
    }

 
	// Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}

