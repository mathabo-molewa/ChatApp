/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.chatapp.Login;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author matha
 */

public class LoginTest {
    
   Login login = new Login();
    
    
    // ================= USERNAME TESTS ================= //
    
    // Test a correct username
    @Test
    public void testValidUsername() {
        assertTrue(login.checkUserName("kyl_1"));
    }
    
    // Test username without underscore
    @Test
    public void testInvalidUsername_NoUnderscore() {
        assertFalse(login.checkUserName("kyl1"));
    }
    
    // Test username that is too long
    @Test
    public void testInvalidUsername_TooLong() {
        assertFalse(login.checkUserName("kyl_123"));
    }
    
    
    // ================= PASSWORD TESTS ================= //
    
    // Test a correct password
    @Test
    public void testValidPassword() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }
    
    // Test password with no capital letter
    @Test
    public void testInvalidPassword_NoCapital() {
        assertFalse(login.checkPasswordComplexity("ch&&sec@ke99!"));
    }
    
    // Test password with no number
    @Test
    public void testInvalidPassword_NoNumber() {
        assertFalse(login.checkPasswordComplexity("Ch&&sec@ke"));
    }
    
    
    // ================= PHONE NUMBER TESTS ================= //
    
    // Test correct phone number
    @Test
    public void testValidPhoneNumber() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }
    
    // Test wrong phone number format
    @Test
    public void testInvalidPhoneNumber() {
        assertFalse(login.checkCellPhoneNumber("0896653"));
    }
    
    
    // ================= REGISTER USER TEST ================= //
    
    // Test successful registration
    @Test
    public void testRegisterUserSuccess() {
        String result = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals("User registered successfully.", result);
    }
    
    // Test registration fails due to bad username
    @Test
    public void testRegisterUser_FailUsername() {
        String result = login.registerUser("kyl1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(result.contains("Username is not correctly formatted"));
    }
    
    
    // ================= LOGIN TESTS ================= //
    
    // Test successful login
    @Test
    public void testLoginSuccess() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }
    
    // Test failed login (wrong password)
    @Test
    public void testLoginFail() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(login.loginUser("kyl_1", "wrongPass"));
    }
    
    
    // ================= LOGIN STATUS MESSAGE TEST ================= //
    
    // Test success message
    @Test
    public void testReturnLoginStatus_Success() {
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976");
        String message = login.returnLoginStatus(true);
        assertTrue(message.contains("Welcome"));
    }
    
    // Test failure message
    @Test
    public void testReturnLoginStatus_Fail() {
        String message = login.returnLoginStatus(false);
        assertEquals("Username or password incorrect, please try again.", message);
    }
}