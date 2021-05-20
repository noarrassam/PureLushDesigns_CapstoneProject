package controller.item;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.ItemsBarcode;



/**
 * Servlet implementation class DeleteBarcode
 */
@WebServlet("/DeleteBarcode")
public class DeleteBarcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBarcode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			if (request.getParameter("barcodeId") != null) {
				try {
					int id = Integer.parseInt(request.getParameter("barcodeId"));
					if (ItemsBarcode.deleteByID(id)) {
						request.setAttribute("SucCtlMsg", "Item barcode deleted successfully");
						RequestDispatcher dispatcher = request.getRequestDispatcher("/ReadBarcode");
						dispatcher.forward(request, response);
						
//						response.sendRedirect("./.");return;
					} else {
						request.setAttribute("ErrCtlMsg", "Error Deleting barcode ");
					}
				} catch (NumberFormatException nfe) {
					request.setAttribute("ErrCtlMsg", "Can't fulfil request without ID");
				} 
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/Delete.jsp");
			dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
