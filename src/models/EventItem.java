package models;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import dbHelpers.DB;

/*
 * Notes:
 * 2020-01-01 is the minimum date, if it is inserted into database it is considered null
 * 
 * 
 * */
		


public class EventItem {
	
	private int groupQuantity = 0, qtyLeft= 0, quantityHistoric = 0;
	private int itemID = 0, eventID = 0, userTaken = 0, userBack=0,quantity=0,addedQty = 0;
	private double cost = 0;
	private String name="",dateTaken = "", dateBack = "";
	private boolean multibarcode = false; 
	

	

	
	


	//Important Note, GroupQuantity here is used as Quantity left for group
	//this constructor is only used to get all available items, don't use this constructor for other purposes
	public EventItem(int itemID, String name, int quantity, int qtyLeft, boolean multibarcode) {
		super();
		setQuantity(quantity);
		setQtyLeft(qtyLeft);;
		setMultibarcode(multibarcode);
		setName(name);
		setItemID(itemID);
	}

	public EventItem(	int eventID,
						int itemID,
						String name,
						boolean multibarcode,
						double cost,
						int groupQuantity,
						int quantity,  
						int quantityHistoric,
						String dateTaken, 
						String dateBack ,
						int userTaken, 
						int userBack
						) {
		super();
		
		setMultibarcode(multibarcode);
		setCost(cost);
		setName(name);
		setItemID(itemID);
		setEventID(eventID);
		setUserTaken(userTaken);
		setUserBack(userBack);
		setQuantity(quantity);;
		setGroupQuantity(groupQuantity);
		setQuantityHistoric(quantityHistoric);
		setDateTaken(dateTaken);
		setDateBack(dateBack);
	}
	public static int insertNewLoaded(List<EventItem> newList, int eventID, int userID) {
		String insert="INSERT INTO ItemEvent (itemID, eventID, dateTaken, userTaken) VALUES(?,?,?,?)";
		String insertQty="INSERT INTO ItemEvent (itemID, eventID, dateTaken, userTaken, quantityIE, quantityHistoric) VALUES(?,?,?,?,?,?)";
		PreparedStatement ps, psQty, thePS;
		
		try {
			
			ps = DB.getConnection().prepareStatement(insert);
			psQty = DB.getConnection().prepareStatement(insertQty);
			for (EventItem item: newList) {
				
				thePS = item.isMultibarcode()?ps:psQty;
					
				thePS.setInt(1,item.getItemID());
				thePS.setInt(2,eventID);
				thePS.setDate(3,new Date(System.currentTimeMillis()));
				thePS.setInt(4,userID);
				if (!item.isMultibarcode()) {
					thePS.setInt(5,item.getQuantity());
					thePS.setInt(6,item.getQuantity());
				}
				
				thePS.addBatch();
			}
			
			return IntStream.of(ps.executeBatch()).sum() + IntStream.of(psQty.executeBatch()).sum();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} 
	}
	
	public static int updateReload(List<EventItem> barcodesReLoad, int eventID, int userID) {
		// TODO Auto-generated method stub
		
		String update="UPDATE ItemEvent SET userTaken = ?, dateTaken = ?, userBack = 0, dateBack = null WHERE itemID = ? AND eventID = ?";
		PreparedStatement ps;
		try {
			
			ps = DB.getConnection().prepareStatement(update);
			for (EventItem item: barcodesReLoad) {	

				ps.setInt(1,userID);
				ps.setDate(2,new Date(System.currentTimeMillis()));
				/* ps.setNull(3,Types.INTEGER); */
				ps.setInt(3,item.getItemID());
				ps.setInt(4,eventID);
				
				ps.addBatch();
			}
			
			return IntStream.of(ps.executeBatch()).sum();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int updateReturned(List<EventItem> returnList, int eventID, int userID) {
		String insert="UPDATE ItemEvent SET dateBack = ?, userBack = ?, quantityIE = 0 WHERE itemID = ? AND eventID = ? AND dateBack IS NULL";
		
		PreparedStatement ps;
		
		try {
			
			ps = DB.getConnection().prepareStatement(insert);
			for (EventItem item: returnList) {
				ps.setDate(1,new Date(System.currentTimeMillis()));
				ps.setInt(2, userID);
				ps.setInt(3,item.getItemID());
				ps.setInt(4,eventID);
				
				ps.addBatch();
			}
			
			
			return IntStream.of(ps.executeBatch()).sum();
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	public static int IncreaseQuantity(List<EventItem> updateLoadList , int eventID, int userID) {
			String insert="UPDATE ItemEvent SET quantityIE = ?, quantityHistoric = ?, userTaken = ?, userBack = 0, dateBack = null WHERE itemID = ? AND eventID = ?";
		//LOOOG HERE
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(insert);
			for (EventItem item: updateLoadList) {
				ps.setInt(1,item.getQuantity() + item.getAddedQty());
				ps.setInt(2,item.getQuantityHistoric() + item.getAddedQty());
				ps.setInt(3, userID);
				ps.setInt(4,item.getItemID());
				ps.setInt(5,eventID);	
				
				ps.addBatch();
			}
			
			return IntStream.of(ps.executeBatch()).sum();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public static boolean decreaseQuantity(EventItem item, int eventID, int userID, int removedAmount) {
		String insert="UPDATE ItemEvent SET quantityIE = ?,  userBack = 0, dateBack = null WHERE itemID = ? AND eventID = ?";
	//LOOOG HERE
	PreparedStatement ps;
	try {
		ps = DB.getConnection().prepareStatement(insert);
		ps.setInt(1,item.getQuantity() - removedAmount);
		ps.setInt(2,item.getItemID());
		ps.setInt(3,eventID);	
		if (ps.executeUpdate() > 0) 
			return true;
		else return false;

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return false;
	}
}
	
	public static HashMap<String,String> getUsers() {
		HashMap<String,String> hmUsers = new HashMap<String,String>();
		String select="Select * from Users;";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { 
				hmUsers.put(String.valueOf(rs.getInt("id")), rs.getString("name"));
			}
			return hmUsers;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}		
		
	} 
	
	public static HashMap<String, EventItem> getHashedLinkedItems(int ID) {
		List<EventItem> list = getLinkedItems(ID);
		HashMap<String, EventItem>  hashmap = new HashMap<String, EventItem>();
		for (EventItem item: list) {
			hashmap.put(String.valueOf(item.getItemID()), item);
		}
		return hashmap;
	}
	
	public static HashMap<String, EventItem> getHashedAllItems() {
		List<EventItem> list = getAllItems();
		HashMap<String, EventItem>  hashmap = new HashMap<String, EventItem>();
		for (EventItem item: list) {
			hashmap.put(String.valueOf(item.getItemID()), item);
		}
		return hashmap;
	}
	
	public static HashMap<String, EventItem> hashByID(List<EventItem> list) {
		HashMap<String, EventItem>  hashmap = new HashMap<String, EventItem>();
		for (EventItem item: list) {
			hashmap.put(String.valueOf(item.getItemID()), item);
		}
		return hashmap;
	}

	
	public static List<EventItem> getLinkedItems(int eventID) {
		List<EventItem> list = new ArrayList<EventItem>();
//		String select="Select * from ItemGroup join Item using(itemGroupID) join ItemEvent ie using(itemID) WHERE eventID = ?;";
		String select="Select * from ItemGroup join Item using(itemGroupID) join ItemEvent ie using(itemID) WHERE eventID = ? ORDER By dateBack, ItemGroup.itemName;";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ps.setInt(1,eventID);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { 
				EventItem item = new EventItem(	eventID,
												rs.getInt("itemID"),
												rs.getString("ItemGroup.itemName"), 
												isMulti(rs.getString("multibarcode")),
												rs.getDouble("initialCost"),
												rs.getInt("quantity"),
												rs.getInt("quantityIE"),
												rs.getInt("quantityHistoric"),
												dateOF(rs.getDate("dateTaken")), 
												dateOF(rs.getDate("dateBack")),	
												rs.getInt("userTaken"), 
												rs.getInt("userBack")					
												) ;

				item.setEventID(eventID);
				list.add(item);
			}
			return list;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getCause() +"\n\n" +   e.getMessage());
			return null;
		}		
	}
	
	public static boolean isMulti(String word) {
		if (word != null)
			if (word.contains("y")) return true; else return false;
		else return false;
	}
	
	public static String dateOF (Date sqlDate) {
		try {
			String result = sqlDate.toString();
			if (!result.equals("2020-01-01")) {
				return result;
			} else return "";
		} catch (Exception e) {
			return "";
		}
	}
	
	public static List<EventItem> getAllItems() {
		List<EventItem> list = new ArrayList<EventItem>();
		String select="Select *, itemID, dateBack, (CASE WHEN dateBack IS NULL AND multiBarcode like 'n%' THEN ItemGroup.quantity-IFNULL(sum(quantityIE),0)  WHEN dateBack IS NOT NULL AND multiBarcode like 'n%' THEN ItemGroup.quantity ELSE null END) AS qtyLeft "
								+ "		from 		ItemGroup " 
								+ "		JOIN 		Item using(itemGroupID)"
								+ " 	LEFT JOIN 	ItemEvent ie using(itemID) "
								+ "group by itemID ;";
		PreparedStatement ps;
		try {
			ps = DB.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { 
				list.add(new EventItem( 	rs.getInt("itemID"),
											rs.getString("ItemGroup.itemName"), 
											rs.getInt("quantityIE"), 
											rs.getInt("qtyLeft"), 
											isMulti(rs.getString("multibarcode"))
											));
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}	
	}
	
	public int getItemID() {
		return itemID;
	}
	public void setItemID(Integer itemID) {
		if (itemID != null) this.itemID = itemID;
	}
	public int getEventID() {
		return eventID;
	}
	public void setEventID(Integer eventID) {
		if (eventID != null) this.eventID = eventID;
	}
	
	public int getQtyLeft() {
		return qtyLeft;
	}

	public void setQtyLeft(Integer qtyLeft) {
		if (qtyLeft != null) this.qtyLeft = qtyLeft;
	}
	
	public int getGroupQuantity() {
		return groupQuantity;
	}
	public void setGroupQuantity(Integer groupQuantity) {
		if (groupQuantity != null) this.groupQuantity = groupQuantity;
	}
	public boolean isMultibarcode() {
		return multibarcode;
	}
	public void setMultibarcode(boolean multibarcode) {
		this.multibarcode = multibarcode;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name != null) this.name = name;
	}
	public int getUserTaken() {
		return userTaken;
	}
	public void setUserTaken(Integer userTaken) {
		if (userTaken != null) this.userTaken = userTaken;
	}
	public int getUserBack() {
		return userBack;
	}
	public void setUserBack(Integer userBack) {
		if (userBack != null) this.userBack = userBack;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		if (quantity != null) this.quantity = quantity;
	}
	
	
	public String getDateTaken() {
		return dateTaken;
	}
	public void setDateTaken(String dateTaken) {
		if(dateTaken != null) this.dateTaken = dateTaken;
	}
	public String getDateBack() {
		return dateBack;
	}
	public void setDateBack(String dateBack) {
		if(dateBack != null)  this.dateBack = dateBack;
	}

	public int getQuantityHistoric() {
		return quantityHistoric;
	}

	public void setQuantityHistoric(Integer quantityHistoric) {
		if (quantityHistoric != null)  this.quantityHistoric = quantityHistoric;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		if(cost != null)  this.cost = cost;
	}

	public int getAddedQty() {
		return addedQty;
	}

	public void setAddedQty(int addedQty) {
		this.addedQty = addedQty;
	}

	


}