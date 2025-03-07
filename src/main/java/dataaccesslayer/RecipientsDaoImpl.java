package dataaccesslayer;

import java.util.List;

import transferobjects.RecipientDTO;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * This class as the data access object implements the methods defined by its interface.
 * It contains six(6) methods including returning string arrays recoding metadata, 
 * retrieving all recipients and conventional CRUD.
 * Each method handles the SQLException by convention.
 * 
 * @author Xin Wang
 * @version 1.0
 * @see java.util.List
 * @see transferobjects.RecipientDTO
 * @see java.util.ArrayList
 * @see java.sql.PreparedStatement
 * @see java.sql.Connection
 * @see java.sql.ResultSet
 * @see java.sql.ResultSetMetaData
 * @see java.sql.SQLException
 * @since JDK 21 (Default)
 */
public class RecipientsDaoImpl implements RecipientsDao{
    
    /**
     * This is the default constructor for RecipientsDaoImpl.
     * 
     * It can be used to initialize a new instance of RecipientsDaoImpl.
     */
    public RecipientsDaoImpl() {
    
    }
    
    /**
     * This method is implemented to return a list containing string arrays recording metadata.
     * 
     * @return The list includes string arrays recording metadata.
     */
    @Override
    public List<String[]> getMetaData() {
        
        // declare a null ResultSetMetaData to store the metadata
        ResultSetMetaData metaData = null;
        
        // declare a integer to record the number of columns in the metadata
        int numberOfColumns = 0;
        
        // declare a null ArrayList to keep string arrays recoding metadata
        ArrayList<String[]> metaDataStrings = null;
        
        // prepare querying statement to access ResultSetMetaData
        String query = "SELECT * FROM Recipients LIMIT 1";
        
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                // retrieve data by executing
                ResultSet resultSet = prepQuery.executeQuery();
                
                ) {
            
            // process query results
            metaData = resultSet.getMetaData();
            numberOfColumns = metaData.getColumnCount();
            metaDataStrings = new ArrayList<>();
            
            // use for loop to obtain column attributes from the metadata
            for (int i = 1; i <= numberOfColumns; i++) {
                
                String[] strings = new String[3];
                strings[0] = metaData.getColumnName(i);
                strings[1] = metaData.getColumnTypeName(i);
                strings[2] = metaData.getColumnClassName(i);
                metaDataStrings.add(strings);
                
            } // end for loop
            
        } // end try
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
        return metaDataStrings;
        
    } // end method getMetaData
    
    /**
     * This method is implemented to return a list including all recipients data.
     * 
     * @return The list contains all recipients data.
     */
    @Override
    public List<RecipientDTO> getAllRecipients() {
        
        // declare a null ArrayList to store all recipients data retrieved
        ArrayList<RecipientDTO> recipients = null;
        
        // prepare querying statement for retrieving all recipients data
        String query = "SELECT * FROM Recipients";
        
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                // retrieve data by executing
                ResultSet resultSet = prepQuery.executeQuery();
                
                ) {
            
            recipients = new ArrayList<RecipientDTO>();
            
            // check whether the matching entries are found or not
            if(resultSet.next()) {
                
                do {
                    
                    // get each attribute of recipients data retrieved
                    RecipientDTO recipient =  new RecipientDTO();
                    recipient.setAwardID(resultSet.getInt("AwardID"));
                    recipient.setName(resultSet.getString("Name"));
                    recipient.setYear(resultSet.getInt("Year"));
                    recipient.setCity(resultSet.getString("City"));
                    recipient.setCategory(resultSet.getString("Category"));
                    recipients.add(recipient);
                
                } while (resultSet.next());
                // end do-while
                
            } else {
            
                // print an appropriate message if no matching entries found
                System.out.println("No results found as there is no recipient.");
            
            } // end if
            
        } // end try
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
        return recipients;
        
    } // end method getAllRecipients
    
    /**
     * This method is implemented to retrieve a recipient data in the kind of DTO 
     * according to AwardID received.
     * 
     * @param awardID The AwardID received to retrieve a specific recipient
     * @return The RecipientDTO based on AwardID derived
     */
    @Override
    public RecipientDTO getRecipientByAwardID(Integer awardID) {
        
        // declare a null RecipientDTO to store the recipient retrieved
        RecipientDTO recipient = null;
        
        // prepare querying statement for retrieving a recipient according to AwardID received
        String query = "SELECT * FROM Recipients WHERE AwardID = ?";
        
        // try-with-resources on outer layer in the method retrieving a recipient
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholder with AwardID received
            prepQuery.setInt(1,awardID);
            
            // nested try-with-resources on inner layer in the method retrieving a recipient
            try(
                    
                    // retrieve data by executing
                    ResultSet resultSet = prepQuery.executeQuery();
                    
                    ) {
                
                // check whether the matching entry is found or not
                if(resultSet.next()) {
                    
                    // get each attribute of the result data retrieved
                    recipient = new RecipientDTO();
                    recipient.setAwardID(resultSet.getInt("AwardID"));
                    recipient.setName(resultSet.getString("Name"));
                    recipient.setYear(resultSet.getInt("Year"));
                    recipient.setCity(resultSet.getString("City"));
                    recipient.setCategory(resultSet.getString("Category"));
                
                } else {
            
                    // print an appropriate message if no matching entry found
                    System.out.printf("No results found for the AwardID: %d.", awardID);
            
                } // end if
            
            } // end nested try on inner layer in the method retrieving a recipient
            
        } // end nested try on outer layer in the method retrieving a recipient
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
            
        return recipient;
        
    } // end method getRecipientByAwardID
    
    /**
     * This method is implemented to insert a recipient data in the kind of DTO to data source.
     * 
     * @param recipient The RecipientDTO received to be inserted to data source
     */
    @Override
    public void addRecipient(RecipientDTO recipient) {
        
        // prepare querying statement for inserting a recipient according to the DTO received
        String query = "INSERT INTO Recipients (AwardID, Name, Year, City, Category) "
                       + "VALUES(?, ?, ?, ?, ?)";
        
        // try-with-resources in the method inserting a recipient
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholders with the attributes drived from the RecipientDTO received
            prepQuery.setInt(1, recipient.getAwardID().intValue());
            prepQuery.setString(2, recipient.getName());
            prepQuery.setInt(3, recipient.getYear().intValue());
            prepQuery.setString(4, recipient.getCity());
            prepQuery.setString(5, recipient.getCategory());
            
            // call executeUpdate() to insert
            prepQuery.executeUpdate();
            
        } // end try in the method inserting a recipient
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
    } // end method addRecipient
    
    /**
     * This method is implemented to update a recipient data in the kind of DTO to data source.
     * 
     * @param recipient The RecipientDTO received to be updated in data source
     */
    @Override
    public void updateRecipient(RecipientDTO recipient) {
        
        // prepare querying statement for updating a recipient according to the DTO received
        String query = "UPDATE Recipients SET Name = ?, Year = ?, "
                       + "City = ?, Category = ? WHERE AwardID = ?";
        
        // try-with-resources in the method updating a recipient
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholders with the attributes drived from the RecipientDTO received
            prepQuery.setString(1, recipient.getName());
            prepQuery.setInt(2, recipient.getYear().intValue());
            prepQuery.setString(3, recipient.getCity());
            prepQuery.setString(4, recipient.getCategory());
            prepQuery.setInt(5, recipient.getAwardID().intValue());
            
            // call executeUpdate() to update
            prepQuery.executeUpdate();
            
        } // end try in the method updating a recipient
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
    } // end method updateRecipient
    
    /**
     * This method is implemented to delete a recipient data in the kind of DTO to data source.
     * 
     * @param recipient The RecipientDTO received to be deleted in data source
     */
    @Override
    public void deleteRecipient(RecipientDTO recipient) {
        
        // prepare querying statement for deleting a recipient according to the DTO received
        String query = "DELETE FROM Recipients WHERE AwardID = ?";
        
        // try-with-resources in the method deleting a recipient
        try(
                
                // establish connection singleton to database
                Connection connection = DataSourceSingleton.DATA_SOURCE.getConnection();
                // use PreparedStatement for the SQL query
                PreparedStatement prepQuery = connection.prepareStatement(query);
                
                ) {
            
            // fill the placeholder with AwardID drived from the RecipientDTO received
            prepQuery.setInt(1, recipient.getAwardID().intValue());
            
            // call executeUpdate() to delete
            prepQuery.executeUpdate();
            
        } // end try in the method deleting a recipient
        
        catch (SQLException sqlException) {
        
            sqlException.printStackTrace();
        
        } // end catch
        
    } // end method deleteRecipient
    
} // end class RecipientsDaoImpl
