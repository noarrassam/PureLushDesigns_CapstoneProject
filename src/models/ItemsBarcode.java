package models;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import dbHelpers.DB;

public class ItemsBarcode {
	 int itemGroupId;
	 String itemName;
     String description;
     String condtion;
	 int quantity;
	String condition="";
	String purchaseDate ;
	boolean hasError = false;
	int id=0;
  


	public ItemsBarcode() {
		this.quantity = 0;
		this.itemGroupId = 0;
		this.itemName = "";
		this.description = ""; 
		this.condition="";
		this.id=0;
		
	}
	public int getId() {
		return id;
	}
	public int setId(int id) {
		try {
			this.id = id;
			return id;
		} catch (NullPointerException npx) {
			hasError = true;
			return -1;
		}
	}
	public ItemsBarcode(int itemId) {
		super();
	}
	
//	public ItemsBarcode(String iD,String itemName, String description,int quantity) {
//		super();
//		this.itemId= iD;
//	
//	}
	public ItemsBarcode(int iD,String itemName, String description,String condition, int quantity,int itemGroupId) {
		super();
		this.id= iD;
		this.itemGroupId = itemGroupId;
		this.itemName = itemName;
		this.description = description; 
		this.condition= condition;
		this.quantity= quantity;
		
	
	}
	
	public String getCondition() {
		return condition;
	}
	public String setCondition(String condition) {
		// TODO Auto-generated method stub
		
		try {
			if (condition.trim().isEmpty()) {
				hasError = true;
				return " Add condition for item";
			}
			if (condition.length() > 45) {
				hasError = true;
				return "Comments Field cannot exceed 255 characters";
			}
			this.condition = condition;
			return "";
		} catch (NullPointerException npx) {
			hasError = true;
			return "";
		}
	}
	//select count(*) from item where groupID = ;
	public static int count(int itemId) {
		String select="select count(*) from Item where itemGroupID =?";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setInt(1,itemId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { 
				return rs.getInt(1); 
						              
			}
			return 0;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public static ItemsBarcode addNew(int itemId) {
		String insert="insert into Item (itemName,comments,itemGroupID,quantity)  Select  itemName, description,itemGroupID,quantity from ItemGroup where itemGroupID=?";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
//			ps.setString(1, barcode.getBarcode());
//			ps.setInt(2, barcode.getQuantity());
//			ps.setString(3, barcode.getComments());
			ps.setInt(1, itemId);
			
			int result = ps.executeUpdate();
			System.out.println("Inserted ID is " + result);
			ResultSet rs = ps.getGeneratedKeys();
			int itemGroupId = 0;
			if (rs.next()) {
				itemGroupId = rs.getInt(1);
			    System.out.println(itemGroupId);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static List<ItemsBarcode> getAll() {
		String select="SELECT * FROM Item";
		List<ItemsBarcode> list = new ArrayList<ItemsBarcode>(); 
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new ItemsBarcode(rs.getInt("itemID"), rs.getString("itemName"),  rs.getString("comments"), rs.getString("cndition"),rs.getInt("quantity"),rs.getInt("itemGroupId")));
			}
			if (list.size() > 0) return list; else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static ItemsBarcode getByID(int ID) {
		String select="SELECT * FROM Item WHERE itemID = ?;";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setInt(1,ID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { 
				return new ItemsBarcode(rs.getInt("itemID"), 
						             rs.getString("itemName"), 
						             rs.getString("comments"), 
						             rs.getString("cndition"),
						             rs.getInt("quantity"),
						             rs.getInt("itemGroupId"));
			}
			return null;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<ItemsBarcode> getItems(int id) {
		String select="SELECT * FROM FCCDecor.Item where itemGroupID=?";
		List<ItemsBarcode> list = new ArrayList<ItemsBarcode>(); 
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ItemsBarcode item = new ItemsBarcode(rs.getInt("itemID"),
													rs.getString("itemName"),
													rs.getString("comments"),
													rs.getString("cndition"),
													rs.getInt("quantity"),
													rs.getInt("itemGroupId"));
				list.add(item);
			}
			if (list.size() > 0) return list; else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Boolean deleteByID(int ID) {
		String delete="DELETE FROM Item WHERE itemID = ?;";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(delete);
			ps.setInt(1,ID);
			int result = ps.executeUpdate();
			
			if (result==1) {
				return true;
			}
			else 
				return false;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static ItemsBarcode editByID(ItemsBarcode newBarcode) {
		String update="UPDATE Item SET itemName = ?, comments = ?,cndition=?  WHERE itemID = ?";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(update);
			ps.setString(1,newBarcode.getitemName());
			ps.setString(2,newBarcode.getComments());
			ps.setString(3,newBarcode.getCondition());
			ps.setInt(4, newBarcode.getId());
			
			int result = ps.executeUpdate();
			if (result==1) {
				return newBarcode;
			}
			else 
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getitemGroupId() {
		return itemGroupId;
		
	}
	public void setitemGroupId(int itemGroupId) {
		this.itemGroupId=itemGroupId;
		
	}
	
	public String getDate() {
		return purchaseDate;
		
	}
	public boolean hasError() {
		return hasError;
	}


	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String setDate(String purchaseDate) {
		// TODO Auto-generated method stub
		
		try {
			if (purchaseDate.trim().isEmpty()) {
				hasError = true;
				return "Name Field cannot be empty";
			}
			if (purchaseDate.length() > 45) {
				hasError = true;
				return "Name Field cannot exceed 45 characters";
			}
			this.purchaseDate = purchaseDate;

			return "";
		} catch (NullPointerException npx) {
			return "Name Field cannot be empty";
		}
	}

	public String getitemName() {
		// TODO Auto-generated method stub
		return itemName;
	}
	public String setitemName(String itemName) {
		// TODO Auto-generated method stub
		try {
			if (itemName.trim().isEmpty()) {
				hasError = true;
				return "Name Field cannot be empty";
			}
			if (itemName.length() > 45) {
				hasError = true;
				return "Name Field cannot exceed 45 characters";
			}
			this.itemName = itemName;
			return "";
		} catch (NullPointerException npx) {
			return "Name Field cannot be empty";
		}
	}
	public int getQuantity() {
		// TODO Auto-generated method stub
		return quantity;
	}
	public int setQuantity(int quantity) {
		// TODO Auto-generated method stub
		try {
			this.quantity = quantity;
			return quantity;
		} catch (NullPointerException npx) {
			hasError = true;
			return -1;
		}
		
	}
	public String getComments() {
		// TODO Auto-generated method stub
		return description;
	}

	public String setComments(String description) {
		// TODO Auto-generated method stub
		
		try {
			if (description.trim().isEmpty()) {
				hasError = true;
				return " Add Comments";
			}
			if (description.length() > 45) {
				hasError = true;
				return "Comments Field cannot exceed 255 characters";
			}
			this.description = description;
			return "";
		} catch (NullPointerException npx) {
			hasError = true;
			return "";
		}
	}
	

}


	
