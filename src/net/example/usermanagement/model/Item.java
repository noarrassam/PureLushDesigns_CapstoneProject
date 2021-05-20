package net.example.usermanagement.model;

public class Item {
    private int itemGroupId;
	private String itemName;
	private String description;
	private double size;
	private String colour;
	private double initialCost;
	private String location;
	private String multiBarcode;
	private int quantity;
	private String category;
	
	public Item() {
		this.itemGroupId = 0;
		this.itemName = "";
		this.description = ""; 
		this.size = 0.0;
		this.colour = "";
		this.initialCost = 0.0;
		this.location = "";
		this.multiBarcode = "";
		this.quantity = 0;
		this.category = "";
	}
 
	public Item(int itemGroupId, String itemName, String description,double size,String colour,double initialCost,String location,
			String multiBarcode,int quantity,String category){
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
		this.itemGroupId = itemGroupId;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return itemName;
	}
	public void setName(String itemName) {
		// TODO Auto-generated method stub
		this.itemName = itemName;
	}

	public String getdescription() {
		// TODO Auto-generated method stub
		return description;
	}

	public void setDescription(String description) {
		// TODO Auto-generated method stub
		this.description = description;
	}

	public double getsize() {
		// TODO Auto-generated method stub
		return size;
	}
	public void setSize(double size) {
		// TODO Auto-generated method stub
		this.size = size;
	}

	public String getColour() {
		// TODO Auto-generated method stub
		return colour;
	}

	public void setColour(String colour) {
		// TODO Auto-generated method stub
		this.colour = colour;
	}

	public double getinitialCost() {
		// TODO Auto-generated method stub
		return initialCost;
	}

	public void setinitialCost(double initialCost) {
		// TODO Auto-generated method stub
		this.initialCost = initialCost;
	}

	public String getmultiBarcode() {
		// TODO Auto-generated method stub
		return multiBarcode;
	}
	public void setmultiBarcode(String multiBarcode) {
		// TODO Auto-generated method stub
		this.multiBarcode = multiBarcode;
	}

	public int getQuantity() {
		// TODO Auto-generated method stub
		return quantity;
	}
	public void setQuantity(int quantity) {
		// TODO Auto-generated method stub
		this.quantity = quantity;
	}

	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}
	public void setLocation(String location) {
		// TODO Auto-generated method stub
		this.location = location;
	}

	public String getCategory() {
		// TODO Auto-generated method stub
		return category;
	}
	public void setCategory(String category) {
		// TODO Auto-generated method stub
		this.category = category;
	}

	public int getitemGroupId() {
		// TODO Auto-generated method stub
		return itemGroupId;
	}

	public void setItemGroupID(int itemGroupId) {
		// TODO Auto-generated method stub
		this.itemGroupId = itemGroupId;
	}
}