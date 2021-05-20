package models;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import dbHelpers.DB;


public class ItemSupplier {
	
	int id = -1;
	String name="";
	boolean hasError = false;
	
	public ItemSupplier() {
		
	}
	
	public ItemSupplier(int id, String name) {
		super();
		this.id= id;
		this.name=name;
	}

	public static List<ItemSupplier> getAll(){
		String select="SELECT * FROM Supplier";
		List<ItemSupplier> list = new ArrayList<ItemSupplier>();
		PreparedStatement ps;
		
		try {
			ps=DB.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new ItemSupplier(rs.getInt("supplierID"),rs.getString("name")));
			}
			if(list.size()>0) return list;
			else return null;
			
		

}catch(SQLException e) {
	e.printStackTrace();
}
		return null;
}
	
	public static ItemSupplier addNew(int itemId) {
		String insert="insert into ItemGroup(supplierID)  Select supplierID from Supplier where SupplierID=?";
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
//	public static List<Supplier> getItems(int id) {
//		String select="SELECT * FROM Spplier WHERE supplierID = ?";
//		List<Supplier> list = new ArrayList<Supplier>(); 
//		PreparedStatement ps;
//		try {
//			ps = DB.getConnection().prepareStatement(select);
//			ps.setInt(1, id);
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next()) {
//				list.add(new Supplier(rs.getInt("itemGroupID"), rs.getString("itemName"), rs.getString("category"), rs.getString("description")));
//			}
//			if (list.size() > 0) return list; else return null;
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	public int getId() {
		return id;
		
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getName() {
		return name;
		
	}
	public String setName(String name) {
		// TODO Auto-generated method stub
		try {
			if (name.trim().isEmpty()) {
				hasError = true;
				return "Name Field cannot be empty";
			}
			if (name.length() > 45) {
				hasError = true;
				return "Name Field cannot exceed 45 characters";
			}
			this.name=name;
			return "";
		} catch (NullPointerException npx) {
			return "Name Field cannot be empty";
		}
	}

	
}
	
	
