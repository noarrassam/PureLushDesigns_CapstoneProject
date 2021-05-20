package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbHelpers.DB;

public class Supplier {
	int id = -1;
	String name = "", contact = "", telephone = "", comments = "";
	boolean hasError = false;
	
	public Supplier() {
		
	}
	
	public Supplier(int id, String name, String contact, String telephone, String comments) {
		super();
		this.id = id;
		this.name = name;
		this.contact = contact;
		this.telephone = telephone;
		this.comments = comments;
	}
	
	public static Supplier addNew(Supplier newSupplier) {
		String insert="INSERT INTO Supplier(name, contact, telephone, comments) VALUES(?,?,?,?);";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,newSupplier.getName());
			ps.setString(2,newSupplier.getContact());
			ps.setString(3,newSupplier.getTelephone());
			ps.setString(4,newSupplier.getComments());
			int result = ps.executeUpdate();
			System.out.println("Inserted ID is " + result);
			ResultSet rs = ps.getGeneratedKeys();
			int id = 0;
			if (rs.next()) {
			    id = rs.getInt(1);
			    System.out.println(id);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Supplier editByID(Supplier newSupplier) {
		String update="UPDATE Supplier SET name = ?, contact = ?, telephone = ?, comments = ? WHERE supplierID = ?";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(update);
			ps.setString(1,newSupplier.getName());
			ps.setString(2,newSupplier.getContact());
			ps.setString(3,newSupplier.getTelephone());
			ps.setString(4,newSupplier.getComments());
			ps.setInt(5,newSupplier.getId());
			int result = ps.executeUpdate();
			if (result==1) {
				return newSupplier;
			}
			else 
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Supplier getByID(int ID) {
		String select="SELECT * FROM Supplier WHERE supplierID = ?;";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setInt(1,ID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { 
				return new Supplier(ID, 	rs.getString("name"), 
											rs.getString("contact"), 
											rs.getString("telephone"), 
											rs.getString("comments"));
			}
			return null;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Boolean deleteByID(int ID) {
		String delete="DELETE FROM Supplier WHERE supplierID = ?;";
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
	
	public static List<Supplier> getAll( ) {
		String select="SELECT * FROM Supplier";
		List<Supplier> list = new ArrayList<Supplier>(); 
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Supplier(	rs.getInt("supplierID"), 
										rs.getString("name"), 
										rs.getString("contact"), 
										rs.getString("telephone"), 
										rs.getString("comments")));
			}
			if (list.size() > 0) return list; else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Supplier> search(String word) {
		String select="SELECT * FROM Supplier WHERE LOWER(CONCAT(name, ' ', contact, ' ', telephone, ' ',comments)) LIKE LOWER(?)";
		List<Supplier> list = new ArrayList<Supplier>(); 
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setString(1, "%" + word + "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Supplier(	rs.getInt("supplierID"), 
										rs.getString("name"), 
										rs.getString("contact"), 
										rs.getString("telephone"), 
										rs.getString("comments")));
			}
			if (list.size() > 0) return list; else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public int getId() {
		return id;
	}
	public boolean hasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
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
	
	public String getName() {
		return name;
	}
	
	public String setName(String name) {
		try {
			if (name.trim().isEmpty()) {
				hasError = true;
				return "Name Field cannot be empty";
			}
			if (name.length() > 45) {
				hasError = true;
				return "Name Field cannot exceed 45 characters";
			}
			this.name = name;
			return "";
		} catch (NullPointerException npx) {
			return "Name Field cannot be empty";
		}
	}
	
	public String getContact() {
		return contact;
	}
	
	public String setContact(String contact) {
		try {
			if (contact.length() > 45) {
				hasError = true;
				return "Contact Field cannot exceed 45 characters";
			}
			this.contact = contact;
			return "";
		} catch (NullPointerException npx) {
			hasError = true;
			return "";
		}
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public String setTelephone(String telephone) {
		try {
			if (telephone.length() > 45) {
				hasError = true;
				return "Telephone Field cannot exceed 45 characters";
			}
			this.telephone = telephone;
			return "";
		} catch (NullPointerException npx) {
			hasError = true;
			return "";
		}
		
	}
	
	public String getComments() {
		return comments;
	}
	
	public String setComments(String comments) {
		try {
			if (comments.length() > 2000) {
				hasError = true;
				return "Comments Field cannot exceed 255 characters";
			}
			this.comments = comments;
			return "";
		} catch (NullPointerException npx) {
				System.err.println(npx.getMessage());
			hasError = true;
			return "";
		}		
	}	
}