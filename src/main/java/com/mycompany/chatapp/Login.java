/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author matha
 */

public class Login {
    //These are my variables. They will hold the user details and save their data 
    String username; 
    String password;
    String phoneNumber;
    String firstName;
    String lastName;
            
    
    //This is the method
   
    public boolean checkUserName(String username)  {
            
            //Check the underscore and the length fall into the instructions
            
            return username.contains("_") && username.length() <=5;
    
     }       
     //The password validation
     public boolean checkPasswordComplexity (String password) {
         
         boolean hasCapital = false;
         boolean hasNumber = false;
         boolean hasSpecial = false;
               
        // Now I create a loop for the password
        for (int i = 0; i < password.length();i++)   {
            
            // This retreives the character at hand
            char c = password.charAt(i); 
            
            // Check if it has a capital letter
            if (Character.isUpperCase(c)) {
                hasCapital = true;
               
            }
            //Check if it has a number
            else if (Character.isDigit(c))  {
                hasNumber = true;
                
            }
            
            //Check if it has a special character
            else if (!Character.isLetterOrDigit(c))   {
                hasSpecial = true; 
            }
        }
        
        return password.length() >=8 && hasCapital && hasNumber && hasSpecial;
     }
        
     //The cellphone number validation  
     //This is the method
     public boolean checkCellPhoneNumber(String phone) {
         return phone.startsWith("+27") && phone.length() <=12;
         
     }
     
     //Register Username
     //This is the method
     public String registerUser(String username, String password, String phoneNumber)  {
         
         //Check the username
         if (!checkUserName(username)){
             return "Username is not correctly formatted; "
                     + "please ensure that your username contains an "
                     + "underscore and is not more than five characters in length.";
         }
        //Check password complexity 
        if (!checkPasswordComplexity(password))  {
            return "Password is not correctly formatted; please ensure that the password"
                    + " contains at least eight characters, a capital letter, a number, and a special character,";
        }
        
        //Check cellphone number
        if (!checkCellPhoneNumber (phoneNumber))  {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        //The output on the screen will show the customer a space to put in their details
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        
        //  Show a message to customer to show their details are registered
        return "User registered successfully.";
   
     }
                 
     //Login feature
     public boolean loginUser(String username, String password) {
         return this.username !=null &&
         this.password != null &&
         this.username.equals(username) &&
         this.password.equals(password);
         
     }
     
    public String returnLoginStatus(boolean success)  {
        if (success){
            return "Welcome " + username + ", it is great to see you again.";
            
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

   
}
             
             
    
             
            
            
            
            
    


