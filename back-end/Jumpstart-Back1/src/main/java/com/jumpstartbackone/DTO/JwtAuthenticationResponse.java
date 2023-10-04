package com.jumpstartbackone.DTO;

public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String message;
    private String roleName; // New field for role name
    private String firstName; // New field for first name
    private Long userId; // New field for user ID
    private String email;
    private String phoneNumber;
	public JwtAuthenticationResponse(String accessToken, String message, String roleName,
			String firstName, Long userId, String email, String phoneNumber) {
		super();
		this.accessToken = accessToken;
		this.message = message;
		this.roleName = roleName;
		this.firstName = firstName;
		this.userId = userId;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

    
}