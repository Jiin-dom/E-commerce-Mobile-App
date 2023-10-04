//package com.mow;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import com.example.registration.RegistrationService; // Assuming RegistrationService is the class responsible for registration logic
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class LoginRegistrationTest {
//
//  @Autowired
//  private RegistrationService registrationService;
//
//  @Test
//  public void testMemberRegistration() {
//    // Test the member registration logic
//    boolean registrationSuccess = registrationService.registerUser("john@example.com", "password", "member");
//    Assert.assertTrue(registrationSuccess);
//  }
//
//  @Test
//  public void testPartnerRegistration() {
//    // Test the partner registration logic
//    boolean registrationSuccess = registrationService.registerUser("jane@example.com", "password", "partner");
//    Assert.assertTrue(registrationSuccess);
//  }
//
//  @Test
//  public void testRiderRegistration() {
//    // Test the rider registration logic
//    boolean registrationSuccess = registrationService.registerUser("alex@example.com", "password", "rider");
//    Assert.assertTrue(registrationSuccess);
//  }
//
//  @Test
//  public void testDonorRegistration() {
//    // Test the donor registration logic
//    boolean registrationSuccess = registrationService.registerUser("emma@example.com", "password", "donor");
//    Assert.assertTrue(registrationSuccess);
//  }
//
//  @Test
//  public void testInvalidLoginAttempt() {
//    // Test the invalid login attempt logic
//    boolean loginSuccess = registrationService.login("wrong@example.com", "password");
//    Assert.assertFalse(loginSuccess);
//  }
//}
//
