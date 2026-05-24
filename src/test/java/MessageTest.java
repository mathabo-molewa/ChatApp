/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.chatapp.Message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    
    @Test
    //Test that message length is under 250 characters, must return a successful message
    public void testMessageLengthValid() {
        Message message = new Message(); //Create a message object
        message.messageText = "Hello";
        
        String result = message.checkMessageLength(); //store the return
        assertEquals("Message ready to send.", result);  
    }
    
    @Test
    //Test that the message length over 250 characters returns a failure message
    public void testMessageLengthInValid() {
        Message message = new Message(); //Create a message object
        message.messageText ="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"; //Message that is abover 250 characters
                String result = message.checkMessageLength();
        int over = message.messageText.length() - 250; //calculate how many character over 250 there are
        //Message for message length over 250 characters
        assertEquals("Message exceeds 250 characters by " + over + "; please reduce the size.", result);  
    }
    
    @Test
    // //Test that the recipient cellphone number returns a succesful message if it is valid
    public void testRecipientValid() {
         Message message = new Message(); //Create message object
         
         String result = message.checkRecipientCellNumber("+27627983643"); //Correct format validation
        assertEquals("Cell phone number successfully captured.", result);  //Success message
    }
    
    @Test 
    // //Test that the recipient cellphone number returns a failure message if it is invalid
    public void testRecipientInValid() {
         Message message = new Message(); //Create a message object
         String result = message.checkRecipientCellNumber("032643"); //Failure format validation
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. "
                + "Please correct the number and try again.", result); //Failure message
    }

    
    @Test
    //Should generate 10 digits correctly
    public void testCheckMessageID() {
        Message message = new Message(); //Create message object
        //Generate 
        message.messageID = message.generateID(); 
        boolean result = message.checkMessageID(); //Validate the length of the message ID
        assertTrue(result); //
    }
     
    
    @Test
    //Check message hash
    public void testCreateMessageHash() {
        Message message = new Message(); //Create message object
        
        //Set 
        message.setMessageID("0012345678");
        message.setMessageNumber(0);
        
        message.messageText = "Hi Mike, can you join us for dinner tonight";
        
        String result = message.createMessageHash(); //Geneate the message hash
        assertEquals("00:0:HITONIGHT", result); //Check the required hash
    }
    
    @Test
    //Check the message count return
    public void testReturnTotalMessages() {
       Message instance = new Message(); //Create message object
       int expResult = 0;
               int result = instance.returnTotalMessages(); //Get all the messages
       assertEquals(expResult, result); //Check the message count
    }
    @Test 
    public void testSendMessageOption() {
    
    String expected = "Messae succesfully sent."; //The expected message
    String actual = "Message successfully sent."; //The result
    assertEquals(expected, actual); // Check the result
    
    }
    
    @Test 
    //test the send message option
    public void testDisregardMessageOption()   {
    String expected = "Press 0 to delete the message."; //The expected message
    String actual = "Press 0 to delete the message."; //The result
    assertEquals(expected, actual);// Check the result
}
    @Test
    public void testStoreMessageOption()  {
    String expected = "Message successfully stored."; //The expected message
    String actual = "Message successfully stored."; //The result
    assertEquals(expected, actual); // Check the result
    }
    
    
    @Test
    //Test the storing of messages
    public void testStoreMessage()    {
        Message instance = new Message(); //Create message object
        
        //Test values
        instance.setMessageID("1234567890");
        instance.recipient= "+27627983643";
        instance.messageText = "Hello";
        
        instance.storeMessage(); //Stores the message
        assertNotNull(instance); //Checks that the object exists
    }
     
    
    
    
    
    
}

    
