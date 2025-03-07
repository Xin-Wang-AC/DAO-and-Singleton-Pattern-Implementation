package businesslayer;

import java.util.List;
import dataaccesslayer.RecipientsDao;
import dataaccesslayer.RecipientsDaoImpl;
import transferobjects.RecipientDTO;

/**
 * This class as the business logic calls the methods implemented in RecipientsDaoImpl class on data access layer.
 * It contains a default constructor and six(6) methods including calling each method in RecipientsDaoImpl class.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.util.List
 * @see dataaccesslayer.RecipientsDao
 * @see dataaccesslayer.RecipientsDaoImpl
 * @see transferobjects.RecipientDTO
 * @since JDK 21 (Default)
 */
public class RecipientsBusinessLogic {
    
    // declare a null RecipientsDao to store the reference to recipientsDao instance
    private RecipientsDao recipientsDao = null;
    
    /**
     * This is the default constructor for RecipientsBusinessLogic.
     * It is used to initialize a new instance of RecipientsDaoImpl.
     */
    public RecipientsBusinessLogic() {
        recipientsDao = new RecipientsDaoImpl();
    }
    
    /**
     * This method calls recipientsDao instance to return a list containing string arrays recording metadata.
     * 
     * @return The list includes string arrays recording metadata.
     */
    public List<String[]> getMetaData() {
        return recipientsDao.getMetaData();
    }
    
    /**
     * This method calls recipientsDao instance to return a list including all recipients data.
     * 
     * @return The list contains all recipients data.
     */
    public List<RecipientDTO> getAllRecipients() {
        return recipientsDao.getAllRecipients();
    }
    
    /**
     * This method calls recipientsDao instance to retrieve a recipient data in the kind of DTO 
     * according to AwardID received.
     * 
     * @param awardID The AwardID received to retrieve a specific recipient
     * @return The RecipientDTO based on AwardID derived
     */
    public RecipientDTO getRecipient(Integer awardID) {
        return recipientsDao.getRecipientByAwardID(awardID);
    }
    
    /**
     * This method calls recipientsDao instance to insert a recipient data in the kind of DTO to data source.
     * 
     * @param recipient The RecipientDTO received to be inserted to data source
     */
    public void addRecipient(RecipientDTO recipient) {
        recipientsDao.addRecipient(recipient);
    }
    
    /**
     * This method calls recipientsDao instance to update a recipient data in the kind of DTO to data source.
     * 
     * @param recipient The RecipientDTO received to be updated in data source
     */
    public void updateRecipient(RecipientDTO recipient) {
        recipientsDao.updateRecipient(recipient);
    }
    
    /**
     * This method calls recipientsDao instance to delete a recipient data in the kind of DTO to data source.
     * 
     * @param recipient The RecipientDTO received to be deleted in data source
     */
    public void deleteRecipient(RecipientDTO recipient) {
        recipientsDao.deleteRecipient(recipient);
    }
    
}
