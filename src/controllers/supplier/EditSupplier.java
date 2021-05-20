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
@WebServlet(value="/Supplier/Edit")
public class EditSupplier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditSupplier() {
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
				Supplier model = Supplier.getByID(id);
				if (model != null) {
					request.setAttribute("model", Supplier.getByID(id));
					request.setAttribute("action", "Edit");
					dispatcher = request.getRequestDispatcher("/supplier/form.jsp");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("/supplier/form.jsp");
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator") || session.getAttribute("urole").equals("Manager")) {
			Supplier newSupplier = new Supplier();
		
		
		//Request Verification (within Model for clean code)
			try {
				request.setAttribute("errName", newSupplier.setId(Integer.parseInt(request.getParameter("id"))));
			} catch (NumberFormatException nfe) {
				response.getWriter().append("Invalid ID, please restart Edit form.");
				return;
			} 
			request.setAttribute("errName", newSupplier.setName(request.getParameter("name")));
			request.setAttribute("errContact", newSupplier.setContact(request.getParameter("contact")));
			request.setAttribute("errTelephone", newSupplier.setTelephone(request.getParameter("telephone")));
			request.setAttribute("errComments", newSupplier.setComments(request.getParameter("comments")));
		
			
			request.setAttribute("model",newSupplier);
				
			if (newSupplier.hasError()) {
				request.setAttribute("action", "Edit");
				request.setAttribute("ErrCtlMsg", "Supplier Editing Error");
			} else {	
				Supplier.editByID(newSupplier);
				request.setAttribute("SucCtlMsg", "Supplier Edited Successfully");
				Logs.addNew(new Logs((int) session.getAttribute("uid"),"Supplier", session.getAttribute("uname") + " Editted Supplier Name:" + newSupplier.getName() ,""));
				System.out.println("Edited" + newSupplier.getName() + " With ID of " + newSupplier.getId());
				request.setAttribute("list", Supplier.getAll());
				dispatcher = request.getRequestDispatcher("/supplier/table.jsp");	
			}
			dispatcher.forward(request, response);
	
		}else
		{
			throw new RuntimeException("Invalid access");
		}
	}
}
