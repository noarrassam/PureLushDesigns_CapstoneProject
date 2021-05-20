package net.example.usermanagement.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import net.example.usermanagement.model.Item;

public class AddQuery {

	private Connection con;
	private ResultSet results = null;

	public AddQuery() {
		System.out.println();
//		InputStream input = (InputStream) getClass().getClassLoader().getResourceAsStream("dbConnection.properties");
//		Properties props = new Properties();
//		try {
//			props.load(input);
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			input.close();
//		} catch (IOException e2) {
//			e2.printStackTrace();
//		}
//		String driver = props.getProperty("driver");
//		String url = props.getProperty("server");
//		String username = props.getProperty("username");
//		String passwd = props.getProperty("password");
		String driver ="com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://fccdecortest.cgecpjdjy8n1.ca-central-1.rds.amazonaws.com:3306/FCCDecor";
		String username = "admin";
		String passwd = "IEteam2020";
		try {
			Class.forName(driver);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			con = DriverManager.getConnection(url, username, passwd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(url + " " + passwd + "  " + username + "  " + driver);
	}

	public void doAdd(Item item) {

		try {
			//read rd = new read();

			String query = "insert into ItemGroup (itemName,category,description,size,colour,initialCost,location,multiBarcode,quantity) values(?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1, item.getName());
			ps.setString(2, item.getCategory());
			ps.setString(3, item.getdescription());
			ps.setDouble(4, item.getsize());
			ps.setString(5, item.getColour());
			ps.setDouble(6, item.getinitialCost());
			ps.setString(7, item.getLocation());
			ps.setString(8, item.getmultiBarcode());
			ps.setInt(9, item.getQuantity());

			ps.executeUpdate();
			this.results = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}