/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.chatapp.Message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {
    
    @Test
    public void testMessageLengthValid() {
        Message message = new Message();
        message.messageText = "Hello";
        
        String result = message.checkMessageLength();
        assertEquals("Message ready to send", result);  
    }
    
    @Test
    public void testMessageLengthInValid() {
        Message message = new Message();
        message.messageText ="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
                String result = message.checkMessageLength();
        int over = message.messageText.length() - 250;
        
        assertEquals("Message exceeds 250 characters by " + over + ", please reduce the size.", result);  
    }
    
    @Test
    public void testRecipientValid() {
         Message message = new Message();
         
         String result = message.checkRecipientCellNumber("+27627983643");
        assertEquals("Cellphone number successfully captured", result); 
    }
    
    @Test 
    public void testRecipientInValid() {
         Message message = new Message();
         String result = message.checkRecipientCellNumber("032643");
        assertEquals("Cell phone number is incorrectly formatted or does not contain international code. "
                + "Please correct the number and try again.", result); 
    }

    
    @Test
    public void testCheckMessageID() {
        Message message = new Message();
        message.messageID = message.generateID();
        boolean result = message.checkMessageID();   //recheck!!!!!!!!!
        assertTrue(result);
    }
     
    
    @Test
    public void testCreateMessageHash() {
        Message message = new Message();
        
        //logic
        message.setMessageID("0012345678");
        message.setMessageNumber(0);
        
        message.messageText = "Hi Mike, can you join us for dinner tonight";
        
        String result = message.createMessageHash();
        assertEquals("00:0:HITONIGHT", result);
    }
    
    @Test
    public void testReturnTotalMessages() {
       Message instance = new Message();
       int expResult = 0;
               int result = instance.returnTotalMessages();
       assertEquals(expResult, result);
    }
    
    @Test
    public void testStoreMessage()    {
        Message instance = new Message();
        
        instance.setMessageID("1234567890");
        instance.recipient("+27627983643");
        instance.messageText = "Hello";
        
        instance.storeMessage();
        assertNotNull(instance);
    }

}

    
