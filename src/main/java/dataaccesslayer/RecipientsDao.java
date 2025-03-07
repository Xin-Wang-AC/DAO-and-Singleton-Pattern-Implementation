package dataaccesslayer;

import java.util.List;
import transferobjects.RecipientDTO;

/**
 * This interface defines the methods that DAO class is supposed to implement.
 * It contains six(6) methods including returning string arrays recording metadata, 
 * retrieving all recipients and conventional CRUD. 
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.util.List
 * @see transferobjects.RecipientDTO
 * @since JDK 21 (Default)
 */
public interface RecipientsDao {
    
    /**
     * This method is defined to return a list containing string arrays recording metadata.
     * 
     * @return The list includes string arrays recording metadata.
     */
    List<String[]> getMetaData();
    
    /**
     * This method is defined to return a list including all recipients data.
     * 
     * @return The list contains all recipients data.
     */
    List<RecipientDTO> getAllRecipients();
    
    /**
     * This method is defined to retrieve a recipient data in the kind of DTO 
     * according to AwardID received.
     * 
     * @param awardID The AwardID received to retrieve a specific recipient
     * @return The RecipientDTO based on AwardID derived
     */
    RecipientDTO getRecipientByAwardID(Integer awardID);
    
    /**
     * This method is defined to insert a recipient data in the kind of DTO to data source.
     * 
     * @param recipient The RecipientDTO received to be inserted to data source
     */
    void addRecipient(RecipientDTO recipient);
    
    /**
     * This method is defined to update a recipient data in the kind of DTO to data source.
     * 
     * @param recipient The RecipientDTO received to be updated to data source
     */
    void updateRecipient(RecipientDTO recipient);
    
    /**
     * This method is defined to delete a recipient data in the kind of DTO to data source.
     * 
     * @param recipient The RecipientDTO received to be deleted in data source
     */
    void deleteRecipient(RecipientDTO recipient);

}
