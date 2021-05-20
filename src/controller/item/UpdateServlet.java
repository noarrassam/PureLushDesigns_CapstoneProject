package controller.item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbHelpers.ReadRecord;
import models.Item;
import models.ItemSupplier;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get items id
		
		int ItemGroupID = Integer.parseInt(request.getParameter("itemGroupId"));
		
		//create ReadRecord class
		ReadRecord rr = new ReadRecord(ItemGroupID);
		
		//use read record to get the book data
		rr.doRead();
		
		Item item = rr.getItem();
		
		//pass item and control to updateForm.jsp
		request.setAttribute("item", item);
		 request.setAttribute("list",ItemSupplier.getAll());
		String url="/updateItem.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get items id
		
				int ItemGroupID = Integer.parseInt(request.getParameter("itemGroupId"));
				
				//create ReadRecord class
				ReadRecord rr = new ReadRecord(ItemGroupID);
				
				//use read record to get the book data
				rr.doRead();
				
				Item item = rr.getItem();
				
				//pass item and control to updateForm.jsp
				request.setAttribute("item", item);
				 request.setAttribute("list",ItemSupplier.getAll());
				String url="/updateItem.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response); 
				//craete a UpdateQuery object 
//						UpdateQuery dq = new UpdateQuery();
				//use update Query to delete record
//				dq.doUpdate(ItemGroupID);
				//pass execution on  to the ReadServlet
//				String url="/read";
//				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
//				dispatcher.forward(request, response); 
	}

}
