/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;


/**
 *
 * @author matha
 */

import java.util.Scanner;

//Main.java - is updated for Part 2 of the project
public class MainApp {
    public static void main(String[] args) {
        
        
        // Create Scanner object to get input from user
            Scanner input = new Scanner(System.in);
            
            String firstName;
            System.out.println("Please enter your first name: ");
            firstName = input.next();
            
            String lastName;
            System.out.println("Please enter your last name: ");
            lastName = input.next();
        input.nextLine();
            
            //***Part 1 is for registration and login***
            
            // Create Login object to use its methods
            Login login = new Login();
            //Call loginUser() and store the boolean result
            
            // REGISTRATION SECTION

            System.out.println("=== USER REGISTRATION ===");
            
            String username;
            
            // Keep asking for username until it is correct
            while (true)  {
                System.out.print("Enter a username: ");
                username = input.nextLine();
                
                // Check if username is valid
                if (!login.checkUserName(username)) {
                    System.out.println("Username is not correct");
                    // exit loop if correct
                    continue;
                    
                }
                    // Show error if username is wrong
                    System.out.println("Username successfully captured");
                    break;
                    
            }
            
            
            // Ask user for password
            System.out.print("Enter a password: ");
            String password = input.nextLine();
            
            // Ask user for phone number
            System.out.print("Enter your South African phone number (+27...): ");
            String phone = input.nextLine();
            
            // Register the user and store message
            String response = login.registerUser(username, password, phone);
            
            // Show registration result
            System.out.println(response);
            
            
            // LOGIN SECTION
            
            System.out.println("=== USER LOGIN ===");
            
            // Ask for username
            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();
            
            // Ask for password
            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();
            
            // Check if login details are correct
            boolean loggedIn = login.loginUser(loginUsername, loginPassword);
            
            // Get login message
            String loginMessage = login.returnLoginStatus(loggedIn);
            
            // Show login result
            System.out.println(loginMessage);
            
            
            //***Part 2 - is for messages only if the user is logged in***
            // only if logged in
            
            if (loggedIn) {
                
                System.out.println("Welcome to ChatApp."); //Login successful and user is welcomed
                //Launch the menu
                boolean running = true;
                while (running) {
                    
                    System.out.println("1. Send Messages");
                    System.out.println("2. Coming soon");
                    System.out.println("3. Exit");
                    
                    System.out.println("Pick a number: ");//Ask user to pick an option
                    
                    int choice = input.nextInt();
                    input.nextLine();
                    
                    
                    switch (choice)     {
                        case 1: {
                            System.out.println("Send messages"); //if user picks option 1
                            
                            //Ask how many messages the user would like to send
                            System.out.println("How many messages would you like to send?");
                            
                              
                            int numMessages = input.nextInt(); //cannot be zero as loop won't run
                            input.nextLine();
                            
                            for (int i = 0; i < numMessages; i++) {
                                Message message = new Message();
                                String recipient;
                                numMessages = i + 1;
                                System.out.println("You are sending " + numMessages + "messages");
                                
                                
                                //Recipient
                                System.out.println("Enter the recipient: ");
                                recipient = input.nextLine();
                                System.out.println(message.checkRecipientCellNumber(recipient));
                                
                                //Message Text
                                System.out.println("Enter your message (250 chars max): ");
                                message.messageText = input.nextLine();
                                
                                //Check message length
                                String lengthMessage = message.checkMessageLength();
                                System.out.println(lengthMessage);
                                
                                if (message.messageText.length() > 250)   {
                                    i--;
                                    continue;
                                }
                                
                                //Generate ID and hash
                                String messageID = message.generateID();
                                String messageHash = message.createMessageHash();
                                
                                System.out.println("Message ID: " + message.generateID());
                                    
                                System.out.println("Message Hash: " + message.createMessageHash());
                                    
                                System.out.println("Recipient : " + message.checkRecipientCellNumber(phone));    
                                    
                                System.out.println("Message: " + message.messageText);
                                 
                                System.out.println(message.sentMessage());
                                    Message.messageCount++;
                            }
                                String messageCount = null;
                                
                          
                               System.out.println("Total Messages sent: " + Message.messageCount); 
                               break;
                        }
                        
                            
                            
                        case 2: System.out.println("Coming Soon"); //if user picks option 2
                            break;
                        
                            
                        case 3: running = false; //if user picks option 3 - quits and exits the while loop
                            System.out.println("Exiting ChatApp");
                        break;
                        
                        
                        default: //If user eneters an invalid option
                            System.out.println("That wasn't an option. Please try again.");
                            
                            
                    }
                }
            }
            else    {
                System.out.println("Login failed, closing app");
                
            }
            input.close();
        }
        }        

        
        


        
     








