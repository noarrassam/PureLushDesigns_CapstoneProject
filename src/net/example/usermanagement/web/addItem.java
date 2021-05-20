package net.example.usermanagement.web;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.example.usermanagement.dao.AddQuery;
import net.example.usermanagement.model.Item;

/**
 * Servlet implementation class addItem
 */
@WebServlet("/addItem")
public class addItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//get the data
				String itemName = request.getParameter("itemName");
				String category = request.getParameter("category");
				String description = request.getParameter("description");
				double size = Double.parseDouble(request.getParameter("size"));
				String colour = request.getParameter("color");
				double initialCost = Double.parseDouble(request.getParameter("initialCost"));
				String location = request.getParameter("Location");
				String multiBarcode = request.getParameter("multiBarcode");
				int quantity = Integer.parseInt(request.getParameter("quantity"));
				
				//set up a item object
				Item item = new Item();
				item.setName(itemName);
				item.setCategory(category);
				item.setDescription(description);
				item.setSize(size);
				item.setColour(colour);
				item.setinitialCost(initialCost);
				item.setLocation(location);
				item.setmultiBarcode(multiBarcode);
				item.setQuantity(quantity);

				//set up an addQuery object
				AddQuery  aq = new AddQuery();
				//pass the item to addQuery to add to the database
				aq.doAdd(item);
				//pass execution control to the ReadServlet
				String url ="/ItemAdded.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);		
	}
}