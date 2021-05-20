package controllers.supplier;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Supplier;

/**
 * Servlet implementation class ListSupplier
 */
@WebServlet(value="/Supplier/")
public class ListSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListSupplier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator") || session.getAttribute("urole").equals("Manager")) {
		if (request.getParameter("search") != null) {
			if (!request.getParameter("search").trim().isEmpty()) {
				request.setAttribute("search", request.getParameter("search").trim());
				request.setAttribute("list", Supplier.search(request.getParameter("search")));	
			} else {
				request.setAttribute("list", Supplier.getAll());
			}
		} else {
			request.setAttribute("list", Supplier.getAll());
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/supplier/table.jsp");
		dispatcher.forward(request, response);
		} else {
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
