package controller.item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbHelpers.UpdateQuery;
import models.Item;

/**
 * Servlet implementation class UpdateItem
 */
@WebServlet("/UpdateItem")
public class UpdateItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//get the form data and set the item object
		int itemGroupId = Integer.parseInt(request.getParameter("itemGroupID"));
		String itemName = request.getParameter("itemName");
		String category = request.getParameter("category");
		String description = request.getParameter("description");
		String size = request.getParameter("size");
		String colour = request.getParameter("color");
		double initialCost = Double.parseDouble(request.getParameter("initialCost"));
		String location = request.getParameter("Location");
		String multiBarcode = request.getParameter("multiBarcode");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		
		Item item = new Item();
		item.setItemGroupID(itemGroupId);
		item.setName(itemName);
		item.setCategory(category);
		item.setDescription(description);
		item.setSize(size);
		item.setColour(colour);
		item.setinitialCost(initialCost);
		item.setLocation(location);
		item.setmultiBarcode(multiBarcode);
		item.setQuantity(quantity);
		//create an UpdateQuery object and use it to update  a item
		UpdateQuery dq = new UpdateQuery();
		dq.doUpdate(item);
		//pass the control to read servlet
		String url="/ListItem";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response); 
	}

}