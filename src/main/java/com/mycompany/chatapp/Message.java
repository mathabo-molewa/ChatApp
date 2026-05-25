/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Random;
import org.json.JSONObject;
import java.util.Scanner;

 //part 2 of the project - creating message.java as required

public class Message {
    //The fields
    //Store the generated digits, message number, cellphone number, message text and message hash
    public String messageID; 
    private int messageNumber;
    public String recipient;
    public String messageText;
    private String messageHash;
    
    public static int messageCount = 0;
    public String setMessageID; //using a setter method to store message ID
    
    public Message()   {
        //Initialise the fields
        this.messageID = "";
        this.messageNumber = 0;
        this.recipient = "";
        this.messageText = "";
        this.messageHash = "";
    }
    //Use a parameter constructor to make a message object
    /**
     *
     * @param messageID
     * @param messageNumber
     * @param recipient
     * @param messageText
     * @param messageHash */

    
public Message(String messageID, int messageNumber, String recipient, String messageText, String messageHash)   {
    //initialise fields
    this.messageID = messageID;
    this.messageNumber = messageNumber;
    this.recipient = recipient;
    this.messageText = messageText;
    this.messageHash = messageHash;
    
}
//Use the setter method to assign a message ID
public void setMessageID(String messageID)    {
    this.messageID = messageID;
    
}
//Use the setter method to assign a message ID
public void setMessageNumber(int messageNumber)   {
    this.messageNumber = messageNumber;
}

//Generating 10-digit number randomly
    public String generateID()  {
        
    Random rnd = new Random();
    String id = ""; //stores the ID that was generated here
    
    //Loops 10 times so that the generated digits can be 10
    for (int i = 0; i < 10; i++)    {
      id += rnd.nextInt(10);
}
    this.messageID = id; //Save message ID.
    return id;
    
    }

public boolean checkMessageID()  {
    return messageID.length() <=10; // Check that message ID does not exceed 10 characters
    
}

//The cellphone number validation  
     //This is the method
     public String checkRecipientCellNumber(String phone) {
    
      if (phone.startsWith("+27") && phone.length() <=13) {
          this.recipient = phone;
          return "Cellphone number successfully captured"; //Meets the requirements, success message
            
        } else {
            return "Cell phone number is incorrectly formatted or does not contain international code. "
                    + "Please correct the number and try again."; //Requirements not met message
      }
     }
     
     //Return a success or failure message
     public String returnRecipient(boolean success)  {
        if (success){
            return "Cellphone number successfully captured";
            
        } else {
            return "Cell phone number is incorrectly formatted or does not contain international code. "
                    + "Please correct the number and try again, please try again.";
        }
     }
     
     //Validate the message length
     public String checkMessageLength()  {
         //length should not go above 250 characters
         if (messageText.length() <= 250)   {
             return "Message ready to send";
             
         }
         else{
             //Calculate the overflow of characters above 250
             int over = messageText.length() - 250;
             return"Message exceeds 250 characters by " + over + ", please reduce the size.";
         }
     }
     
//MessageHash
public String createMessageHash()     {

String idPart = messageID.length() >= 2? messageID.substring(0, 2): messageID;


String[] words = messageText.split(" "); //Split the message test


String firstWord = words [0];  //get the first word   

String lastWord = words[words.length - 1]; //get the last word

String hash = idPart + ":" + messageNumber  + ":" + firstWord + lastWord; //The hash should have this format
        return hash.toUpperCase(); 
}


public String sentMessage()  {
    Scanner input = new Scanner(System.in);
     //Display the options for the user to choose from
    System.out.println("what would you like to do with this message?");
    System.out.println("1) Send Message");
    System.out.println("2) Disregard Message");
    System.out.println("3) Store message to send later");

    int option = input.nextInt(); 

    switch (option) {
            case 1: 
                return "Message successfully sent."; //Send message
            
            case 2: 
                return "Press 0 to delete message."; //Discard of the message
            
            case 3: 
                storeMessage();
                return "Message successfully stored."; //Store the message (Json)
            
            default: 
                return "Invalid input";  //handle invalid input
            

    }
}   
//Validate recipient cellphone number
 public boolean recipient(String phone) {
         return phone.startsWith("+27") && phone.length() <=12; //Only if valid then return
         
     }
public String printMessages()  {

return "Messages printed"; //Display the messages and return the confirmation message
}

 //return the all the messages  
public int returnTotalMessages()     {
    return messageCount; 

}

public void storeMessage()   {
    JSONObject obj = new JSONObject();//Create Json objecr
    
    //Add message details
    obj.put("MessageID", messageID);
    obj.put("Recipient", recipient);
    obj.put("MessageText", messageText);
    
    System.out.println("Stored JSON message: " +obj.toString()); //Display the stored Json object
    

} 

}
