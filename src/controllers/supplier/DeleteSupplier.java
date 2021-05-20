package controllers.supplier;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Logs;
import models.Supplier;

/**
 * Servlet implementation class EditSupplier
 */
@WebServlet(value="/Supplier/Delete")
public class DeleteSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteSupplier() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator") || session.getAttribute("urole").equals("Manager")) {
		if (request.getParameter("id") != null) {
			try {
				
				
				int id = Integer.parseInt(request.getParameter("id"));
				String oldName = Supplier.getByID(id).getName();

				if (Supplier.deleteByID(id)) {
					request.setAttribute("SucCtlMsg", "Deleted Supplier Successfully");
					Logs.addNew(new Logs((int)session.getAttribute("uid"),"Supplier",session.getAttribute("uname") + " Deleted Supplier Name:" + oldName,""));
					response.sendRedirect("./.");return;
				} else {
					request.setAttribute("ErrCtlMsg", "Error Deleting Supplier");
				}
			} catch (NumberFormatException nfe) {
				request.setAttribute("ErrCtlMsg", "Can't fulfil request without ID");
			} 
		}
		
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
