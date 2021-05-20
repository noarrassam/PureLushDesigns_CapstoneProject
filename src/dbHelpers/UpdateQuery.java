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

public class UpdateQuery {
	
	private Connection con;
	private ResultSet results = null;
	public UpdateQuery(){
		System.out.println();
		InputStream input=getClass().getClassLoader().getResourceAsStream("dbConnection.properties");
		Properties props = new Properties();
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
	
	public void doUpdate(Item item) {
		//set up a string to hold our query
		
		String query ="UPDATE ItemGroup SET  itemName=?,category=?,description=?,size=?,colour=?,initialCost=?,location=?,multiBarcode=?,quantity=? WHERE itemGroupID=?";
		//create a prepared statement using our query string
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
		
			ps.setString(1,item.getName() );
			ps.setString(2, item.getCategory());
			ps.setString(3, item.getdescription());
			ps.setString(4, item.getsize());
			ps.setNString(5, item.getColour());
			ps.setDouble(6, item.getinitialCost());
			ps.setString(7,item.getLocation());
			ps.setString(8, item.getmultiBarcode());
			ps.setInt(9,item.getQuantity());
			ps.setInt(10,item.getitemGroupId());
			
			//execute the query
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//fill in the prepared statement
		//execute the query
	}

}
