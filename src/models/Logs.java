package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

import dbHelpers.DB;

public class Logs {
	int id = -1;
	String type = "";
	String activity = "";
	String target = "";
	boolean hasError = false;
	
	public Logs() {
		
	}
	public Logs( int userId, String type, String activity, String target) {
		super();
		Date date = Calendar.getInstance().getTime();  
	    DateFormat dateFormat = new SimpleDateFormat("MMM dd/yyyy hh:mm");  
	    String currentDate = dateFormat.format(date);  
		this.id = userId;
		this.type = type;
		this.activity = activity;
		this.target = currentDate;
		this.hasError = false;
	}
	public void setID(int id) {
		this.id = id;
		this.hasError = false;
	}
	public String setType(String type) {
		try {
			if (type.length() > 45) {
				hasError = true;
				return "Type Field cannot exceed 45 characters";
			}
			this.type = type;
			return "";
		} catch (NullPointerException npx) {
			hasError = true;
			return "";
		}
		
	}
	public String setActivity(String activity) {
		try {
			if (activity.length() > 45) {
				hasError = true;
				return "Activity Field cannot exceed 45 characters";
			}
			this.activity = activity;
			return "";
		} catch (NullPointerException npx) {
			hasError = true;
			return "";
		}
		
	}
	public String setTarget(String target) {
		try {
			if (target.length() > 45) {
				hasError = true;
				return "Target Field cannot exceed 45 characters";
			}
			this.target = target;
			return "";
		} catch (NullPointerException npx) {
			hasError = true;
			return "";
		}
		
	}
	public int getID() {
		return id;
	}
	public String getType() {
		return type;
	}
	public String getActivity() {
		return activity;
	}
	public String getTarget() {
		return target;
	}
	
	public static int addNewGroup(List<Logs> newList) {
		String insert= "insert into Logs(id,type,activity,target) values (?,?,?,?);";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			for (Logs log: newList) {
				ps.setInt(1,log.getID());
				ps.setString(2,log.getType());
				ps.setString(3,log.getActivity());
				ps.setString(4,log.getTarget());
				
				ps.addBatch();
				}
			return IntStream.of(ps.executeBatch()).sum();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	public static Logs addNew(Logs newLog) {
		String insert= "insert into Logs(id,type,activity,target) values (?,?,?,?);";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(insert,Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1,newLog.getID());
			ps.setString(2,newLog.getType());
			ps.setString(3,newLog.getActivity());
			ps.setString(4,newLog.getTarget());
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
	//not sure if we'll delete anything but making it just in case
	public static Boolean deleteByID(int ID) {
		String delete="DELETE FROM Logs WHERE id = ?;";
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
	//this will get every row
	public static List<Logs> getAll( ) {
		String select="SELECT * FROM Logs";
		List<Logs> list = new ArrayList<Logs>(); 
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Logs(	rs.getInt("id"), 
						rs.getString("Type"), 
						rs.getString("Activity"), 
						rs.getString("Target")));
			}
			if (list.size() > 0) return list; else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//this will only get info of a particular id(person)
	public static List<Logs> search(int id) {
		String select="SELECT * FROM Logs WHERE id = ?";
		List<Logs> list = new ArrayList<Logs>(); 
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(new Logs(	rs.getInt("id"), 
										rs.getString("Type"), 
										rs.getString("Activity"), 
										rs.getString("Target")));
			}
			if (list.size() > 0) return list; else return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
