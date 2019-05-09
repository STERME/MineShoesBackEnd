package db;

import java.util.List;
import java.util.Set;

import entity.shoses;


public interface DBConnection {
	/**
	 * Close the connection.
	 */
	public void close();

	/**
	 * Search allshoses.
	 * 
	 * @return list of items
	 */
	public List<shoses> searchAllShoses();
	/**
	 * Register user.
	 * 
	 * @return insert success or not
	 */
	public boolean registerUser(String userId, String password, String firstname, String lastname);
	/**
	 * insert allshoses.
	 * 
	 * @return insert success or not
	 */
    public boolean insertShoses(String shoses_id, String name, String category, String color, String vendor, String description, String image_url, String price, String size, String width, String type, String msrp);
	   
   public boolean deleteShoses(String shoses_id);
	

	/**
	 * Get full name of a user. (This is not needed for main course, just for demo
	 * and extension).
	 * 
	 * @param userId
	 * @return full name of the user
	 */
	public String getFullname(String userId);

	/**
	 * Return whether the credential is correct. (This is not needed for main
	 * course, just for demo and extension)
	 * 
	 * @param userId
	 * @param password
	 * @return boolean
	 */
	public boolean verifyLogin(String userId, String password);
}
