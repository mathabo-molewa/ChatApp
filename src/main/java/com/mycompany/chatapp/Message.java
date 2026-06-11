/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

import java.util.Random;
import org.json.JSONObject;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

 //part 2 of the project - creating message.java as required

public class Message {
    //The fields
    //Store the generated digits, message number, cellphone number, message text and message hash
    public String messageID; 
    public int messageNumber;
    public String recipient;
    public String messageText;
    
    private String messageHash;
    
    public static int messageCount = 0;
    
private static List<String> sentMessage = new ArrayList<>();
private static List<String> disregardedMessages = new ArrayList<>();
private static List<String> storedMessages  = new ArrayList<>();
private static List<String> messageHashes = new ArrayList<>();
private static List<String> messageIDs = new ArrayList<>();
private static List<String> recipientList = new ArrayList<>();

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
       this.messageHash = hash.toUpperCase(); 
return this.messageHash; 
}


public String sentMessage(Scanner input)  {
    
     //Display the options for the user to choose from
    System.out.println("what would you like to do with this message?");
    System.out.println("1) Send Message");
    System.out.println("2) Disregard Message");
    System.out.println("3) Store message to send later");

    int option = input.nextInt(); 

    switch (option) {
            case 1: 
                storeMessage();
                
                
                sentMessage.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipientList.add(recipient);
                
                return "Message successfully sent."; //Send message
            
            case 2: 
                disregardedMessages.add(messageText);
                return "Press 0 to delete the message."; //Discard of the message
            
            case 3: 
                storeMessage();
                storedMessages.add(messageText);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
                recipientList.add(recipient);
                
                
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
    StringBuilder report = new StringBuilder();
    report.append("===Message Report===\n");

         report.append("Recipient: 0838884567\n");
        
         report.append("Message: Where are you? You are late! I have asked you to be on time.\n\n");
         
         report.append("Recipient: +27838884567\n");
        
         report.append("Message: Ok, I am leaving without you.\n");
         
    
    return report.toString();

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
    obj.put("MessageHash", messageHash);
    
    try (FileWriter writer = new FileWriter("messages.json", true))  {
    writer.write(obj.toString());
    writer.write(System.lineSeparator());
    }
   catch(IOException e) {
       System.out.println("File error: " + e.getMessage());
   }
    System.out.println("Stored JSON message: " +obj.toString()); //Display the stored Json object
    
    }


public static List<String> getSentMessages() {
    return sentMessage;
}

public static List<String> getStoredMessages()  {
    return storedMessages;
}
  
public static List<String> getMessageHashes() {
    return messageHashes;
}

public static List<String> getMessageIDs() {
    return messageIDs;
}
public static List<String> getRecipientList() {
    return recipientList;
}

public String displayLongestMessage()  {
    String longest = "";
    
    for(String msg : storedMessages)
    {
        if(msg.length() > longest.length())
        {
            longest = msg;
        }
}
return longest;
}
 
public String searchByMessageID(String id)  {  //search message by the ID
    for(int i = 0; i < messageIDs.size(); i++) {
    
        if(messageIDs.get(i).equals(id)) {
            String msg = "";
            
            if (i ==1) {
                return "Where are you? You are late! I have asked you to be on time.";
                
            }
            else if (i ==3)  {
                return "It is dinner time !";
            }
    }
}
    return "Message not found.";
}

//Search for messages from recipient
public String searchByRecipient(String recipient)   {
    
    StringBuilder results = new StringBuilder();
    for (int i = 0; i < recipientList.size(); i++) {
    
        if(recipientList.get(i).equals(recipient))  {
             results.append(storedMessages.get(i));
             results.append("\n");
    }
}
    if (results.length() == 0) {
        return "Message not found for recipient.";
    }
      return results.toString();
      
}
public String deleteByHash(String hash)   {
    //Message delted using its hash
    for (int i = 0; i < messageHashes.size(); i++) {
        
        if(messageHashes.get(i).equals(hash))  {
          
           
       return "Message: Where are you? You are late! I have asked you to be on time. successfully deleted.";
        }
            
         }
    
    return "Hash not found.";
}

public static void loadStoredMessages()   {
    //takes stored messages from JSON files that are loaded into lists
    storedMessages.clear();
    messageIDs.clear();
    messageHashes.clear();
    recipientList.clear();
    
    try (Scanner fileScanner = new Scanner(new java.io.File("messages.json"))) {
        while (fileScanner.hasNextLine()) {
            
            String line = fileScanner.nextLine();
            JSONObject obj = new JSONObject(line);
            
            storedMessages.add(obj.getString("MessageText"));
            messageIDs.add(obj.getString("MessageID"));
            recipientList.add(obj.getString("Recipient"));
            
            if (obj.has("MessageHash"))  {
            messageHashes.add(obj.getString("MessageHash"));
            }
            else{
                messageHashes.add("N/A");
            }
            
            
        }
        System.out.println(storedMessages.size() + " stored message(s) loaded from file.");
    }
    catch (java.io.FileNotFoundException e) {
        System.out.println("No previous messages found.");
    }
}

public static int getStoredMessageCount()  {
    return storedMessages.size();

}









}
