package run;

import businesslayer.RecipientsBusinessLogic;
import java.util.List;
import transferobjects.RecipientDTO;

/**
 * This class implements a console application to explore the DAO software pattern and Singleton Design Pattern.
 * 
 * It calls RecipientsBusinessLogic class on business layer in which it calls RecipientsDaoImpl class on data access layer, 
 * processes RecipientDTO as data transfer objects and displays the data in a formatted tabular form preceded by column headings.
 * At first, this console application outputs the contents of recipients table.
 * Then, it inserts a new row in that table and print the contents again.
 * Next, it deletes the newly inserted row and print the contents third times.
 * Finally, it displays the column headings in a formatted tabular form.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see businesslayer.RecipientsBusinessLogic
 * @see java.util.List
 * @see transferobjects.RecipientDTO
 * @since JDK 21 (Default)
 */
public class Launcher {
    
    /**
     * This is the default constructor for Launcher.
     * It can be used to initialize a new instance of Launcher.
     */
    public Launcher() {
    
    }
    
    /**
     * The main method that serves as the entry point for 
     * running a console application to explore the DAO software pattern and Singleton Design Pattern.
     * 
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        
        // call a new RecipientsBusinessLogic instance
        RecipientsBusinessLogic logic = new RecipientsBusinessLogic();
        
        List<RecipientDTO> list = null;
        RecipientDTO recipient = null;
        List<String[]> metaDataStrings = null;
        
        // print all recipients by using business logic and calling printing method
        System.out.println("Printing all Recipients");
        
        // print first times
        list = logic.getAllRecipients();
        printRecipients(list);
        
        // insert a new recipient by preparing a new instance of RecipientDTO class in advance
        System.out.println("Inserting One New Recipient");
        
        recipient = new RecipientDTO();
        recipient.setAwardID(72);
        recipient.setName("TestNameADD");
        recipient.setYear(9999);
        recipient.setCity("Ottawa");
        recipient.setCategory("Test Category");
        
        // use business logic to insert such new recipient
        logic.addRecipient(recipient);
        
        // print second times
        list = logic.getAllRecipients();
        printRecipients(list);
        
        // delete the newly inserted row by cutting the size of recipients list minus 1
        System.out.println("Deleting the Last Recipient");
        
        recipient = list.get(list.size() - 1);
        
        // use business logic to delete the newly inserted row
        logic.deleteRecipient(recipient);
        
        // print third times
        list = logic.getAllRecipients();
        printRecipients(list);
        
        // display the column headings in a formatted tabular form
        System.out.println("Printing Metadata for Recipients Table");
        
        metaDataStrings = logic.getMetaData();
        printMetaData(metaDataStrings);
        
    }
    
    /**
     * This method prints a recipient data in a formatted tabular form.
     * 
     * @param recipient The RecipientDTO received to be printed
     */
    private static void printRecipient(RecipientDTO recipient) {
        
        String output = String.format("%-6s%-29s%6s  %-21s%-21s",
                recipient.getAwardID().toString(),
                recipient.getName(),
                recipient.getYear(),
                recipient.getCity(),
                recipient.getCategory());
        
        System.out.println(output);
    }
    
    /**
     * This method prints a list of recipients data in a formatted tabular form.
     * 
     * @param recipients The list including recipients data received to be printed
     */
    private static void printRecipients(List<RecipientDTO> recipients) {
        
        for (RecipientDTO recipient : recipients) {
            
            printRecipient(recipient);
            
        }
        
        System.out.println();
        
    }
    
    /**
     * This method displays the column headings in a formatted tabular form.
     * 
     * @param metaDataStrings The list containing string arrays recording metadata
     */
    private static void printMetaData(List<String[]> metaDataStrings) {
    
        int numberOfColumns = metaDataStrings.size();
        
        // use metaDataStrings to print column headings
        for (String[] strings : metaDataStrings) {
                
            String output = String.format("%-10s%-10s%-10s", 
                    strings[0],
                    strings[1],
                    strings[2]);
            
            System.out.println(output);
                
        } // end for loop
    
    }
    
}
