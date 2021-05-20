package dbHelpers;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import models.Item;



public class ReadQuery {
	private Connection con;
	private ResultSet results = null;
	public ReadQuery(){
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
	
	
	public void doRead(){
		try{
			String query="select * from ItemGroup ORDER BY itemGroupID ASC";
			PreparedStatement ps= con.prepareStatement(query);
			this.results = ps.executeQuery();
			
		} catch(SQLException ex){
			Logger.getLogger(ReadQuery.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
	
	public String getHTMLTable() {
	    String table = "";
	    table += "<table border=1 id=\"items\">";

	    try {
	    	 table += "<tr>";
             table += "<th>"; 
             table += "id";
             table += "</th>";
             table += "<th>"; 
             table += "Item Name";
             table += "</th>";
             table += "<th>"; 
             table += "Category";
             table += "</th>";
             table += "<th>"; 
             table += "Description";
             table += "</th>";
             table += "<th>"; 
             table += "Size";
             table += "</th>";
             table += "<th>"; 
             table += "Colour";
             table += "</th>";
             table += "<th>"; 
             table += "Initial Cost";
             table += "</th>";
             table += "<th>"; 
             table += "Location";
             table += "</th>";
             table += "<th>"; 
             table += "Multibarcode Item";
             table += "</th>";
             table += "<th>"; 
             table += "Quantity";
             table += "</th>";
             table += "<th>"; 
             table += "Delete Item";
             table += "</th>";
             table += "<th>"; 
             table += "Update Item";
             table += "</th>";
             table += "</tr>";
			while(this.results.next()) {
			    Item item = new Item();
			    item.setItemGroupID(this.results.getInt("itemGroupID"));
			    item.setName(this.results.getString("itemName"));
			    item.setCategory(this.results.getString("category"));
			    item.setDescription(this.results.getString("description"));
			    item.setSize(this.results.getString("size"));
			    item.setColour(this.results.getString("colour"));
			    item.setinitialCost(this.results.getDouble("initialCost"));
			    item.setLocation(this.results.getString("location"));
			    item.setmultiBarcode(this.results.getString("multiBarcode"));
			    item.setQuantity(this.results.getInt("quantity"));
			   
			    
			    
			   
			 
			    table += "<tr>";
			   
			        table += "<td>";
			            table += item.getitemGroupId();
			        table += "</td>";
			        table += "<td>";
		            table +=  item.getName();
		        table += "</td>";
			        table += "<td>";
		            	table += item.getCategory();
		            table += "</td>";
			        table += "<td>";
		            	table += item.getdescription();
		            table += "</td>";
			        table += "<td>";
		            	table += item.getsize();
		            table += "</td>";
		            table += "<td>";
	            		table += item.getColour();
	            	table += "</td>";
	            	table += "<td>";
            		table += item.getinitialCost();
            	table += "</td>";
            	table += "<td>";
        		table += item.getLocation();
        	table += "</td>";
        	table += "<td>";
    		table += item.getmultiBarcode();
    	table += "</td>";
    	table += "<td>";
		table += item.getQuantity();
	table += "</td>";
	            	table +="<td>";
	            			table+= " <a id=\"delete\" href=deleteServlet?ItemGroupID=" + item.getitemGroupId() + " >   delete</a>";
	            	table +="</td>";
	            	table +="<td>";
        			table+= " <a id=\"update\" href=UpdateServlet?ItemGroupID=" + item.getitemGroupId() + ">  update</a>";
        	table +="</td>";
        	table +="<td>";
			table+= " <a id=\"update\" href=CreateBarCode?ItemGroupID=" + item.getitemGroupId() + ">  Barcode</a>";
	table +="</td>";
			    table += "</tr>";

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    table += "</table>";
	        return table;

	}
	
}
