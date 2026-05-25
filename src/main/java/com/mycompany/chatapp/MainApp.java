/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Scanner;

// MainApp.java - updated for Part 2 of the project
public class MainApp {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        
        //Part 2- add user's name
        System.out.println("Please enter your first name: ");
        String firstName = input.nextLine();
        //Part 2- add user's surname
        System.out.println("Please enter your last name: ");
        String lastName = input.nextLine();

        // *** Part 1: Registration and Login ***

        Login login = new Login();

        System.out.println("=== USER REGISTRATION ===");

        // Keep asking for a valid username
        String username;
        while (true) {
            System.out.print("Enter a username (must contain '_' and be 5 chars or fewer): ");
            username = input.nextLine();

            if (!login.checkUserName(username)) {
                System.out.println("Username is not correctly formatted. Please try again.");
                continue; //loops if incorrect details are added
            }
            System.out.println("Username successfully captured.");
            break;
        }

        // Keep asking for a valid password
        String password;
        while (true) {
            System.out.print("Enter a password (8+ chars, 1 capital, 1 number, 1 special character): ");
            password = input.nextLine();

            if (!login.checkPasswordComplexity(password)) {
                System.out.println("Password is not correctly formatted. Please try again.");
                continue; //loops if incorrect details are added
            }
            System.out.println("Password successfully captured.");
            break;
        }

        // Keep asking for a valid phone number
        String phone;
        while (true) {
            System.out.print("Enter your South African phone number (+27...): ");
            phone = input.nextLine();

            if (!login.checkCellPhoneNumber(phone)) {
                System.out.println("Phone number incorrectly formatted. Please try again.");
                continue; //loops if incorrect details are added
            }
            System.out.println("Phone number successfully captured.");
            break;
        }

        // Register the user
        String response = login.registerUser(username, password, phone);
        System.out.println(response);

        // FIX: string now matches what registerUser() actually returns
        if (response.equals("User registered successfully.")) {

            // *** LOGIN SECTION ***
            //User inputs their login details
            System.out.println("\n=== USER LOGIN ===");

            System.out.print("Enter your username: ");
            String loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            String loginPassword = input.nextLine();

            boolean loggedIn = login.loginUser(loginUsername, loginPassword);
            String loginMessage = login.returnLoginStatus(loggedIn);
            System.out.println(loginMessage);

            // *** Part 2: Messaging (only if logged in) ***
            if (loggedIn) {

                System.out.println("Welcome to ChatApp, " + firstName + lastName + "!"); //Part 2 - add user's name and username along with a welcome message after they logged in.

                boolean running = true;
                while (running) {
                    //display the menu for the user to choose from
                    System.out.println("\n--- MENU ---");
                    System.out.println("1. Send Messages");
                    System.out.println("2. Show recently sent messages");
                    System.out.println("3. Exit");
                    System.out.print("Pick a number: ");

                    int choice = input.nextInt();
                    input.nextLine();

                    switch (choice) {
                        case 1: {
                            System.out.println("\n--- SEND MESSAGES ---");
                            //Ask user to input number of messages
                            System.out.print("How many messages would you like to send? "); 
                            int numMessages = input.nextInt();
                            input.nextLine();

                            for (int i = 0; i < numMessages; i++) {
                                Message message = new Message();

                                // FIX: removed the line that overwrote numMessages
                                System.out.println("\nComposing message " + (i + 1) + " of " + numMessages);

                                // Recipient
                                String recipientResult = "";
                                while (!recipientResult.equals("Cellphone number successfully captured")) {
                                    System.out.print("Enter the recipient's number (+27...): "); // ask user to input their cellphone number 
                                    String recipient = input.nextLine();
                                    recipientResult = message.checkRecipientCellNumber(recipient);
                                    System.out.println(recipientResult);
                                }

                                // Message text
                                while (true) {
                                    System.out.print("Enter your message (250 chars max): ");  //Maximum message characters is 250.
                                    message.messageText = input.nextLine();
                                    String lengthMessage = message.checkMessageLength();
                                    System.out.println(lengthMessage);

                                    if (message.messageText.length() <= 250) {
                                        break; // message is valid, move on
                                    }
                                    // Otherwise loop again and ask for a shorter message
                                }

                                // Generate ID and hash
                                String messageID = message.generateID();
                                String messageHash = message.createMessageHash();

                                System.out.println("Message ID: " + messageID);
                                System.out.println("Message Hash: " + messageHash);
                                System.out.println("Message: " + message.messageText);

                                // Ask the user what to do with their message
                                String sendResult = message.sentMessage();
                                System.out.println(sendResult);

                                // Only count it if the user actually sent it
                                if (sendResult.equals("Message successfully sent.")) {
                                    Message.messageCount++;
                                }
                            }

                            System.out.println("\nTotal messages sent this session: " + Message.messageCount);
                            break;
                        }

                        case 2:
                            System.out.println("Coming Soon!"); //display message if option 2 is chosen
                            break;

                        case 3:
                            running = false;
                            System.out.println("Exiting ChatApp. Goodbye, " + firstName + "!"); // display message if option 3 is chosen
                            break;

                        default:
                            System.out.println("Invalid option. Please choose 1, 2, or 3."); // display message if user enters an unavailable options
                    }
                }

            } else {
                System.out.println("Login failed. Closing app."); //displays if user fails to login, the app will automatically close.
            }

        } else {
            System.out.println("Registration failed. Closing app."); // displays if user fails to register, the app will automatically close
        }
       //Close scanner
        input.close();
    }
}