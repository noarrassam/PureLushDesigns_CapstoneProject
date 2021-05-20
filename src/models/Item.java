package models;



import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;

import dbHelpers.DB;
import models.Item;






public class Item {
    private int itemGroupId;
    private int supplierID;
	private String itemName;
	private String description;
	private String size;
	private String colour;
	private double initialCost;
	private String location;
	private String multiBarcode;
	private int quantity;
	private String category;
	private String supplierName;
	boolean hasError = false;
	
	public Item() {
		this.itemGroupId = 0;
		this.supplierID = 0;
		this.itemName = "";
		this.description = ""; 
		this.size = "";
		this.colour = "";
		this.initialCost = 0.0;
		this.location = "";
		this.multiBarcode = "";
		this.quantity = 0;
		this.category = "";
		this.supplierName="";
	}
 
	public Item(int itemGroupId, String itemName, String description,String size,String colour,double initialCost,String location,
			String multiBarcode,int quantity,String category,String supplierName){
		this.itemGroupId =itemGroupId;
		this.itemName = itemName;
		this.description = description;
		this.size = size;
		this.colour = colour;
		this.initialCost = initialCost;
		this.location = location;
		this.multiBarcode = multiBarcode;
		this.quantity = quantity;
		this.category = category;
	
		this.supplierName = supplierName;
		this.itemGroupId = itemGroupId;
	}
 
	public boolean hasError() {
		return hasError;
	}


	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return itemName;
	}
	public String setName(String itemName) {
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
	public String getsupplierName() {
		// TODO Auto-generated method stub
		return supplierName;
	}
	public String setsupplierName(String supplierName) {
		// TODO Auto-generated method stub
		try {
			if (supplierName.trim().isEmpty()) {
				hasError = true;
				return " Supplier Name Field cannot be empty";
			}
			if (supplierName.length() > 45) {
				hasError = true;
				return "Name Field cannot exceed 45 characters";
			}
			this.supplierName = supplierName;
			return "";
		} catch (NullPointerException npx) {
			return "Name Field cannot be empty";
		}
	}



	public String getdescription() {
		// TODO Auto-generated method stub
		return description;
	}

	public String setDescription(String description) {
		// TODO Auto-generated method stub
		
		try {
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



	public String getsize() {
		// TODO Auto-generated method stub
		return size;
	}
	public String setSize(String size) {
		// TODO Auto-generated method stub
		try {
			if (size.length() > 45) {
				hasError = true;
				return "Telephone Field cannot exceed 45 characters";
			}
			this.size = size;
			return "";
		} catch (NullPointerException npx) {
			hasError = true;
			return "";
		}
		
		
	}




	public String getColour() {
		// TODO Auto-generated method stub
		return colour;
	}

	public String setColour(String colour) {
		// TODO Auto-generated method stub
		try {
			if (colour.trim().isEmpty()) {
				hasError = true;
				return "Name Field cannot be empty";
			}
			if (colour.length() > 45) {
				hasError = true;
				return "Name Field cannot exceed 45 characters";
			}
			this.colour = colour;

			return "";
		} catch (NullPointerException npx) {
			return "Name Field cannot be empty";
		}
			}



	public double getinitialCost() {
		// TODO Auto-generated method stub
		return initialCost;
	}

	public String setinitialCost(double initialCost) {
		// TODO Auto-generated method stub
		
		try {
			if (initialCost > 45) {
				hasError = true;
				return "Telephone Field cannot exceed 45 characters";
			}
			this.initialCost = initialCost;
			return "";
		} catch (NullPointerException npx) {
			hasError = true;
			return "";
		}
		
	}



	public String getmultiBarcode() {
		// TODO Auto-generated method stub
		return multiBarcode;
	}
	public String setmultiBarcode(String multiBarcode) {
		// TODO Auto-generated method stub
		try {
			if (location.trim().isEmpty()) {
				hasError = true;
				return "Name Field cannot be empty";
			}
			if (location.length() > 45) {
				hasError = true;
				return "Name Field cannot exceed 45 characters";
			}
			this.multiBarcode = multiBarcode;

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




	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}
	public String setLocation(String location) {
		// TODO Auto-generated method stub
		
		try {
			if (location.trim().isEmpty()) {
				hasError = true;
				return "Name Field cannot be empty";
			}
			if (location.length() > 45) {
				hasError = true;
				return "Name Field cannot exceed 45 characters";
			}
			this.location = location;

			return "";
		} catch (NullPointerException npx) {
			return "Name Field cannot be empty";
		}
	}




	public String getCategory() {
		// TODO Auto-generated method stub
		return category;
	}
	public String setCategory(String category) {
		// TODO Auto-generated method stub
		try {
			if (category =="") {
				hasError = true;
				return "Select the category";
			}
			this.category = category;
			return "";
		} catch (NullPointerException npx) {
			hasError = true;
			return "";
		}
		
		
	}

	public int getitemGroupId() {
		// TODO Auto-generated method stub
		return itemGroupId;
	}

	public void setItemGroupID(int itemGroupId) {
		// TODO Auto-generated method stub
		this.itemGroupId = itemGroupId;
	}
	public static Item updateQuantity() {
		String update="UPDATE ItemGroup\r\n" + 
				"SET quantity = 1\r\n" + 
				"WHERE multiBarcode = 'no' ";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(update);
			
			
			int result = ps.executeUpdate();
			if (result==1) {
				return null;
			}
			else 
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static Item addNew(Item item) {
		String insert="insert into ItemGroup (itemName,category,description,size,colour,initialCost,location,multiBarcode,quantity,supplierName) values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());
			ps.setString(2, item.getCategory());
			ps.setString(3, item.getdescription());
			ps.setString(4, item.getsize());
			ps.setString(5, item.getColour());
			ps.setDouble(6, item.getinitialCost());
			ps.setString(7, item.getLocation());
			ps.setString(8, item.getmultiBarcode());
			ps.setInt(9, item.getQuantity());
			
			ps.setString(10, item.getsupplierName());
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
	public int getSupplierID() {
		return supplierID;
	}

	public int setSupplierID(int supplierID) {
		// TODO Auto-generated method stub
		try {
			this.supplierID = supplierID;
			return supplierID;
		} catch (NullPointerException npx) {
			hasError = true;
			return -1;
		}
		
	}
	public static List<Item> getAll() {
		String select="SELECT * FROM ItemGroup";
		List<Item> list = new ArrayList<Item>(); 
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Item(rs.getInt("itemGroupID"), rs.getString("itemName"),  rs.getString("description"), rs.getString("size"),
						rs.getString("colour"),rs.getDouble("initialCost"),rs.getString("location"),rs.getString("multiBarcode"),rs.getInt("quantity"),rs.getString("category"),rs.getString("supplierName")));
			}
			if (list.size() > 0) return list; else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Item getByID(int ID) {
		String select="SELECT * FROM ItemGroup WHERE itemGroupID = ?;";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setInt(1,ID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { 
				return new Item(ID, rs.getString("itemName"),  rs.getString("description"), rs.getString("size"),
						rs.getString("colour"),rs.getDouble("initialCost"),rs.getString("location"),rs.getString("multiBarcode"),rs.getInt("quantity"),rs.getString("category"),rs.getString("supplierName"));
			}
			return null;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static List<Item> getItems(int id) {
		String select="SELECT *  FROM ItemGroup where itemGroupID=?";
		List<Item> list = new ArrayList<Item>(); 
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Item(rs.getInt("itemGroupID"), rs.getString("itemName"),  rs.getString("description"), rs.getString("size"),
						rs.getString("colour"),rs.getDouble("initialCost"),rs.getString("location"),rs.getString("multiBarcode"),rs.getInt("quantity"),rs.getString("category"),rs.getString("supplierName")));
			}
			if (list.size() > 0) return list; else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static List<Item> search(String word) {
		String select=	"select * from ItemGroup where UPPER(itemName) LIKE ? ORDER BY itemGroupID ASC";
//		String select="SELECT * FROM ItemGroup WHERE LOWER(CONCAT(itemName, ' ', category, ' ', description, ' ',size,' ',colour,' ', initialCost,' ' , location,' ' multiBarcode, ' ', quantity,' ', supplierName)) LIKE LOWER(?)";
		List<Item> list = new ArrayList<Item>(); 
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setString(1, "%" + word + "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Item(rs.getInt("itemGroupID"), rs.getString("itemName"),  rs.getString("description"), rs.getString("size"),
						rs.getString("colour"),rs.getDouble("initialCost"),rs.getString("location"),rs.getString("multiBarcode"),rs.getInt("quantity"),rs.getString("category"),rs.getString("supplierName")));
			}
			if (list.size() > 0) return list; else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
