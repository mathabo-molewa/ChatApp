/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Random;
import org.json.JSONObject;
import java.util.Scanner;

 //part 2 of the project - creating message.java 
public class Message {
    //The fields
    public String messageID; //
    private int messageNumber;
    public String recipient;
    public String messageText;
    private String messageHash;
    
    public static int messageCount = 0;
    public String setMessageID;
    
    public Message()   {
        this.messageID = "";
        this.messageNumber = 0;
        this.recipient = "";
        this.messageText = "";
        this.messageHash = "";
    }
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
public void setMessageID(String messageID)    {
    this.messageID = messageID;
    
}
public void setMessageNumber(int messageNumber)   {
    this.messageNumber = messageNumber;
}

//Generating 10-digit number
    public String generateID()  {
        
    Random rnd = new Random();
    String id = "";
    
    for (int i = 0; i < 10; i++)    {
      id += rnd.nextInt(10);
}
    this.messageID = id;
    return id;
    
    }

public boolean checkMessageID()  {
    return messageID.length() <=10;
    
}

//The cellphone number validation  
     //This is the method
     public String checkRecipientCellNumber(String phone) {
    
      if (phone.startsWith("+27") && phone.length() <=13) {
          this.recipient = phone;
          return "Cellphone number successfully captured";
            
        } else {
            return "Cell phone number is incorrectly formatted or does not contain international code. "
                    + "Please correct the number and try again.";
      }
     }
     
     
     public String returnRecipient(boolean success)  {
        if (success){
            return "Cellphone number successfully captured";
            
        } else {
            return "Cell phone number is incorrectly formatted or does not contain international code. "
                    + "Please correct the number and try again, please try again.";
        }
     }
     
     //message length
     public String checkMessageLength()  {
         if (messageText.length() <= 250)   {
             return "Message ready to send";
             
         }
         else{
             int over = messageText.length() - 250;
             return"Message exceeds 250 characters by " + over + ", please reduce the size.";
         }
     }
     
     //MessageHash
public String createMessageHash()     {

String idPart = messageID.length() >= 2? messageID.substring(0, 2): messageID;


String[] words = messageText.split(" ");


String firstWord = words [0];    

String lastWord = words[words.length - 1]; 

String hash = idPart + ":" + messageNumber  + ":" + firstWord + lastWord;
        return hash.toUpperCase();
}

public String sentMessage()  {
    Scanner input = new Scanner(System.in);

    System.out.println("what would you like to do with this message?");
    System.out.println("1) Send Message");
    System.out.println("2) Disregard Message");
    System.out.println("3) Store message to send later");

    int option = input.nextInt(); //logic goes here

    switch (option) {
            case 1: 
                return "Message successfully sent.";
            
            case 2: 
                return "Press 0 to delete message.";
            
            case 3: 
                storeMessage();
                return "Message successfully stored.";
            
            default: 
                return "Invalid input";
            

    }
}   

 public boolean recipient(String phone) {
         return phone.startsWith("+27") && phone.length() <=12;
         
     }
public String printMessages()  {

return "Messages printed";
}

    
public int returnTotalMessages()     {
    return messageCount;

}

public void storeMessage()   {
    JSONObject obj = new JSONObject();
    
    obj.put("MessageID", messageID);
    obj.put("Recipient", recipient);
    obj.put("MessageText", messageText);
    
    System.out.println("Stored JSON message: " +obj.toString());
    

} 

}
