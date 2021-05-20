package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import dbHelpers.DB;

public class Category {

	int id = -1;
	String categoryType="";
	boolean hasError= false;
	public Category() {
		
	}
	public Category(int id, String catType) {
		super();
		this.id = id;
		this.categoryType = catType;
		this.hasError = false;
	}
	public int getId() {
		return id;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public boolean hasError() {
		return hasError;
	}
	public int setId(int id) {
		try {
			this.id = id;
			return id;
		} catch (NullPointerException npx) {
			this.hasError = true;
			return -1;
		}
	}
	public String setCategoryType(String categoryType) {
		try {
			if (categoryType.trim().isEmpty()) {
				hasError = true;
				return "Category Field cannot be empty";
			}
			if (categoryType.length() > 45) {
				hasError = true;
				return "Name Field cannot exceed 45 characters";
			}
			this.categoryType = categoryType;
			return "";
		} catch (NullPointerException npx) {
			return "category Field cannot be empty";
		}
	}
	
	public static Category addNew(Category newCategory) {
		
		String insert = "insert into Category (category) values(?)";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, newCategory.getCategoryType());
			
			int result = ps.executeUpdate();
			System.out.println("inserted category: "+result);
			ResultSet rs = ps.getGeneratedKeys();
			int id = 0;
			if (rs.next()) {
				id = rs.getInt(1);
				System.out.println();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Category editByID(Category newCategory) {
		String update = "Update Category SET category = ? where categoryID = ?";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(update);
			ps.setString(1, newCategory.getCategoryType());
			ps.setInt(2,newCategory.getId());
			int result = ps.executeUpdate();
			if (result==1) {
				return newCategory;
			}
			else 
				return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Category getByID(int ID) {
		String select = "Select * from Category where categoryID=?;";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setInt(1, ID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				return new Category(ID,rs.getString("category"));
			}
			return null;
		}
		catch(SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Boolean deleteByID(int ID) {
		String delete = "Delete from Category where categoryID = ?;";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(delete);
			ps.setInt(1, ID);
			int result = ps.executeUpdate();
			if(result == 1) {
				return true;
			}
			else
				return false;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public static List<Category> getAll(){
		String select = "Select * from Category;";
		List<Category> list= new ArrayList<Category>();
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Category(rs.getInt("categoryID"),rs.getString("category")));				
			}
			if (list.size()>0)
				return list;
			else
				return null;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<Category> search(String word) {
		String select="SELECT * FROM Category WHERE LOWER(category)) LIKE LOWER(?)";
		List<Category> list = new ArrayList<Category>(); 
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setString(1, "%" + word + "%");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Category(	rs.getInt("categoryID"), 
										rs.getString("category")));
			}
			if (list.size() > 0) return list; else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
