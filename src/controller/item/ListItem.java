package controller.item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Item;
import models.ItemSupplier;

/**
 * Servlet implementation class ListItem
 */
@WebServlet("/ListItem")
public class ListItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator") || session.getAttribute("urole").equals("Manager")  || session.getAttribute("urole").equals("General User")) {
		if (request.getParameter("searchVal") != null) {
			if (!request.getParameter("searchVal").trim().isEmpty()) {
				request.setAttribute("search", request.getParameter("searchVal").trim());
				request.setAttribute("list", Item.search(request.getParameter("searchVal")));	
			} else {
				request.setAttribute("list", Item.getAll());
			}
		} else {
			request.setAttribute("ItemList", Item.getAll());
			
		}
		request.setAttribute("list",ItemSupplier.getAll());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/read.jsp");
		dispatcher.forward(request, response);
	} else
	{
		throw new RuntimeException("Invalid access");
	}
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
