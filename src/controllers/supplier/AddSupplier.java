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
 * Servlet implementation class AddSupplier
 */
@WebServlet(value="/Supplier/Add")
public class AddSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSupplier() {
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
		request.setAttribute("action", "Add");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/supplier/form.jsp");
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
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator") || session.getAttribute("urole").equals("Manager")) {	
			request.setAttribute("action", "Add");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/supplier/form.jsp");
			Supplier newSupplier = new Supplier();
			
			//Request Verification (within Model for clean code)
				request.setAttribute("errName", newSupplier.setName(request.getParameter("name")));
				request.setAttribute("errContact", newSupplier.setContact(request.getParameter("contact")));
				request.setAttribute("errTelephone", newSupplier.setTelephone(request.getParameter("telephone")));
				request.setAttribute("errComments", newSupplier.setComments(request.getParameter("comments")));
				
			request.setAttribute("model",newSupplier);
				
			if (newSupplier.hasError()) {
				request.setAttribute("ErrCtlMsg", "Supplier Adding Error");
			} else {
				Supplier.addNew(newSupplier);
				Logs.addNew(new Logs((int)session.getAttribute("uid"),"Supplier", session.getAttribute("uname") + " Added New Supplier Name:" + newSupplier.getName() ,""));
				request.setAttribute("SucCtlMsg", "Supplier Added Successfully");
				request.setAttribute("list", Supplier.getAll());
				dispatcher = request.getRequestDispatcher("/supplier/table.jsp");
				// TODO redirect to Details
				//
			}
			
			dispatcher.forward(request, response);
		
		} else
		{
			throw new RuntimeException("Invalid access");
		}
	}
}