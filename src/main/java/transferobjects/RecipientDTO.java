package transferobjects;

/**
 * This class as the data transfer object encapsulates recipients data.
 * It contains five(5) attributes representing a recipient 
 * including AwardID, Name, Year, City and Category. 
 * There are several getters and setters one pair per attribute.
 * 
 * @author Xin Wang
 * @version 1.0
 * @since JDK 21 (Default)
 */
public class RecipientDTO {
    
    private Integer awardID;
    private String name;
    private Integer year;
    private String city;
    private String category;
    
    /**
     * This is the default constructor for RecipientDTO.
     * 
     * It can be used to initialize a new instance of RecipientDTO.
     */
    public RecipientDTO() {
    
    }
    
    /**
     * This method as a getter returns the AwardID.
     * 
     * @return The AwardID to get
     */
    public Integer getAwardID() {
        return awardID;
    }
    
    /**
     * This method as a setter sets the AwardID.
     * 
     * @param awardID The AwardID to set
     */
    public void setAwardID(Integer awardID) {
        this.awardID = awardID;
    }
    
    /**
     * This method as a getter returns the Name.
     * 
     * @return The Name to get
     */
    public String getName() {
        return name;
    }
    
    /**
     * This method as a setter sets the Name.
     * 
     * @param name The Name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * This method as a getter returns the Year.
     * 
     * @return The Year to get
     */
    public Integer getYear() {
        return year;
    }
    
    /**
     * This method as a setter sets the Year.
     * 
     * @param year The Year to set
     */
    public void setYear(Integer year) {
        this.year = year;
    }
    
    /**
     * This method as a getter returns the City.
     * 
     * @return The City to get
     */
    public String getCity() {
        return city;
    }
    
    /**
     * This method as a setter sets the City.
     * 
     * @param city The City to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * This method as a getter returns the Category.
     * 
     * @return The Category to get
     */
    public String getCategory() {
        return category;
    }
    
    /**
     * This method as a setter sets the Category.
     * 
     * @param category The Category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }
    
}
