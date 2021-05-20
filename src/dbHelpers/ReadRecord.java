package dbHelpers;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import models.Item;

public class ReadRecord {
	private Connection con;
	private ResultSet results = null;

	private Item item = new Item();
	 private int itemGroupID;
	 public ReadRecord( int itemGroupID){
			System.out.println();
			InputStream input=getClass().getClassLoader().getResourceAsStream("dbConnection.properties");
			Properties props = new Properties();
			this.itemGroupID = itemGroupID;
			try {
				props.load(input);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				input.close();
			}
			catch(IOException e1) {
				
			}
			String driver=props.getProperty("driver");
			String url=props.getProperty("server");
			String username=props.getProperty("username");
			String passwd=props.getProperty("password");

			
			try {
				Class.forName(driver);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				con = DriverManager.getConnection(url,username,passwd);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(url + " " +  passwd + "  " +  username + "  " + driver);
		}
		
	 public void doRead() {
		 String query = "select * from ItemGroup where itemGroupID =?";
		 
		 try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1,this.itemGroupID);
			this.results = ps.executeQuery();
			this.results.next();
			 item.setItemGroupID(this.results.getInt(1));
			    item.setName(this.results.getString("itemName"));
			    item.setCategory(this.results.getString("category"));
			    item.setDescription(this.results.getString("description"));
			    item.setSize(this.results.getString("size"));
			    item.setColour(this.results.getString("colour"));
			    item.setinitialCost(this.results.getDouble("initialCost"));
			    item.setLocation(this.results.getString("location"));
			    item.setmultiBarcode(this.results.getString("multiBarcode"));
			    item.setQuantity(this.results.getInt("quantity"));
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
   public Item getItem() {
	   return this.item;
   }
}
