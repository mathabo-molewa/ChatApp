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
       System.out.println("Welcome to ChatApp");
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
            
            boolean loggedIn = false;
            String loginUsername = "";
            String loginPassword = "";
            
            while (!loggedIn)   {
                

            System.out.print("Enter your username: ");
            loginUsername = input.nextLine();

            System.out.print("Enter your password: ");
            loginPassword = input.nextLine();

            loggedIn = login.loginUser(loginUsername, loginPassword);
            System.out.println(login.returnLoginStatus(loggedIn));
             if (!loggedIn) {
                System.out.println("Login failed. please try again.\n");
            }
            }
             System.out.println("Login successful!"); //Only if login is success then it continues
             Message.loadStoredMessages();
             

            // *** Part 2: Messaging (only if logged in) ***
          
    System.out.println("Welcome to ChatApp, " + firstName + " " + lastName + "!"); //Part 2 - add user's name and username along with a welcome message after they logged in.

                boolean running = true;
                while (running) {
                    //display the menu for the user to choose from
                    System.out.println("\n--- MENU ---");
                    System.out.println("1. Send Messages");
                    System.out.println("2. Show recently sent messages");
                    System.out.println("3. Exit");
                    System.out.println("4. Stored Messages");
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
                                message.setMessageNumber(i + 1);

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
                                System.out.println("Recipient: " + message.recipient);
                                System.out.println("Message: " + message.messageText);
                                
                                // Ask the user what to do with their message
                                String sendResult = message.sentMessage(input);
                                System.out.println(sendResult);

                                // Only count it if the user actually sent it
                                if (sendResult.equals("Message successfully sent.")) {
                                    Message.messageCount++;
                                }
                            }

                            System.out.println("Total messages sent this session: " + Message.messageCount);
                            break;
                        }

                        case 2:
                            if(Message.getSentMessages().isEmpty()) {
                            System.out.println("No sent messages."); //display message if option 2 is chosen
                            }
                            else {
                                for(String msg : Message.getSentMessages())  {
                                    System.out.println(msg);
                                }
                            }
                            
                            
                            break;

                        case 3:
                            running = false;
                            System.out.println("Exiting ChatApp. Goodbye, " + firstName + "!"); // display message if option 3 is chosen
                            break;
                            
                        case 4:
                            storedMessagesMenu(input);
                            break;

                        default:
                            System.out.println("Invalid option. Please choose 1, 2, 3 or 4."); // display message if user enters an unavailable options
                    }
                }
        } 
      else {
            System.out.println("Registration failed. Closing app."); // displays if user fails to register, the app will automatically close
        }
        //Close scanner
        input.close();
    }
     
                            //PART 3//
    
    //Stored messages sub menu
        public static void storedMessagesMenu(Scanner input) {
            
           Message message = new Message();
           boolean backToMain = false;
           
           while(!backToMain)  {
               
           //Menu options
           System.out.println("\nStored Messages Menu");
           
           System.out.println("a) Display all stored messages");
           System.out.println("b) Display longest message");
           System.out.println("c) Search by message ID");
           System.out.println("d) Search by recipient");
           System.out.println("e) Delete by hash");
           System.out.println("f) Display report");
           System.out.println("g) Return to main menu");
           
           System.out.print("Choose an option: "); //Ask user for input
           String option = input.nextLine().toLowerCase();
            
         
           
           switch(option)   {
               
               case "a":
                   if (Message.getStoredMessages().isEmpty()) {
                   System.out.println("No stored messages.");
               } 
                   else {
                       for (int i = 0; i < Message.getStoredMessages().size(); i++)  {
                      System.out.println("Message " + (i + 1) + ": " + Message.getStoredMessages().get(i));
                      System.out.println("---------------------------------------------");
                   }
                   
                }
                   break;
                       
               
               case "b": 
                   System.out.println("Longest message: " + message.displayLongestMessage());
                   break;
                   
               case "c":
                   System.out.print("Enter Message ID: ");
                   String id = input.nextLine();
                   
                   System.out.println(message.searchByMessageID(id));
                   break;
                   
               case "d":
                   System.out.print("Enter recipient: ");
                   String recipient = input.nextLine();
                   
                   System.out.println(message.searchByRecipient(recipient));
                   break;
                   
               case "e":
                   System.out.print("Enter hash: ");
                   String hash = input.nextLine();
                   System.out.println(message.deleteByHash(hash));
                   break;
                   
               case "f":
                   System.out.println(message.printMessages());
                   break;
                   
               case "g":
                   backToMain = true;
                   break;
                   
               default:
                   //Invalid message output
                   System.out.println("Invalid option. Please enter a, b, c, d, e, f or g.");
           }  
        }
        }
       
}
