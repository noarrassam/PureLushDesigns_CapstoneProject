package controllers.supplier;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Supplier;
import models.SupplierItem;

/**
 * Servlet implementation class DetailSupplier
 */
@WebServlet(value="/Supplier/Details")
public class DetailSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailSupplier() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
	
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator") || session.getAttribute("urole").equals("Manager")) {
		if (request.getParameter("id") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Supplier model = Supplier.getByID(id);
				if (model != null) {
					request.setAttribute("model", model);
					request.setAttribute("list", SupplierItem.getItems(id));
					dispatcher = request.getRequestDispatcher("/supplier/details.jsp");
				} else {
					request.setAttribute("ErrCtlMsg", "Supplier Not Found");
				}
			} catch (NumberFormatException nfe) {
				request.setAttribute("ErrCtlMsg", "Can't fulfil request without ID");
			} 
		}
		dispatcher.forward(request, response);
		}else
		{
			throw new RuntimeException("Invalid access");
		}
	}


	/**s
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}