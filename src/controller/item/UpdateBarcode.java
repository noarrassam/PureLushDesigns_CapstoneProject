package controller.item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.ItemsBarcode;

/**
 * Servlet implementation class UpdateBarcode
 */
@WebServlet("/UpdateBarcode")
public class UpdateBarcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateBarcode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//    	this.doPost(request, response);
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator") || session.getAttribute("urole").equals("Manager")) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BarcodeForm.jsp");
		if (request.getParameter("barcodeId") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("barcodeId"));
				ItemsBarcode model = ItemsBarcode.getByID(id);
				if (model != null) {
					request.setAttribute("model", model);
					request.setAttribute("action", "UpdateBarcode");
					dispatcher = request.getRequestDispatcher("/BarcodeForm.jsp");
				} else {
					request.setAttribute("ErrCtlMsg", " Not Found");
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/BarcodeForm.jsp");
		ItemsBarcode itemsBarcode = new ItemsBarcode();
		
		
		//Request Verification (within Model for clean code)
//			try {
				request.setAttribute("errId", itemsBarcode.setId(Integer.parseInt(request.getParameter("barcodeId"))));
//			} catch (NumberFormatException nfe) {
//				response.getWriter().append("Invalid ID, please restart Edit form.");
//				return;
//			} 
			request.setAttribute("errName", itemsBarcode.setitemName(request.getParameter("itemName")));
			request.setAttribute("errCondition", itemsBarcode.setCondition(request.getParameter("condition")));
		    request.setAttribute("errComments", itemsBarcode.setComments(request.getParameter("description")));
		
			
		request.setAttribute("model",itemsBarcode);
			
		if (itemsBarcode.hasError()) {
			request.setAttribute("action", "Edit");
			request.setAttribute("ErrCtlMsg", " Editing Error");
		} else {
			
			ItemsBarcode.editByID(itemsBarcode);
			request.setAttribute("SucCtlMsg", "Updated Successfully");
			System.out.println("Edited" + " " + itemsBarcode.getitemName() + " With ID of " + itemsBarcode.getId());
			request.setAttribute("list", ItemsBarcode.getAll());
			request.setAttribute("SucCtlMsg", "Updated Successfully");
			dispatcher = request.getRequestDispatcher("/BarcodeForm.jsp");
			
		}
		
		dispatcher.forward(request, response);
	}

}
