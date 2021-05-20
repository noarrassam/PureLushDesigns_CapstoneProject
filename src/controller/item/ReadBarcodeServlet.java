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
 * Servlet implementation class ReadBarcodeServlet
 */
@WebServlet("/ReadBarcodeServlet")
public class ReadBarcodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadBarcodeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		if (request.getParameter("itemGroupID") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("itemGroupID"));
				
					request.setAttribute("listBarcode", ItemsBarcode.getItems(id));
					
					dispatcher = request.getRequestDispatcher("/SaveBarcode.jsp");
				
				
			} catch (NumberFormatException nfe) {
				request.setAttribute("ErrCtlMsg", "Can't fulfil request without ID");
			} 
		}
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
