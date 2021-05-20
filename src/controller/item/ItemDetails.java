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
import models.ItemsBarcode;

/**
 * Servlet implementation class ItemDetails
 */
@WebServlet("/ItemDetails")
public class ItemDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ItemDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		HttpSession session = request.getSession();
//		if (session.getAttribute("urole").equals("Administrator"))
//		{
//			request.setAttribute("model", model);
//		}
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator") || session.getAttribute("urole").equals("Manager")) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		if (request.getParameter("itemGroupId") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("itemGroupId"));
				
				Item model = Item.getByID(id);
				if (model != null) {
					request.setAttribute("model", model);
					request.setAttribute("list", Item.getItems(id));
				    request.setAttribute("listBarcode", ItemsBarcode.getItems(id));
				    request.setAttribute("count", ItemsBarcode.count(id));
					
					dispatcher = request.getRequestDispatcher("/BarcodeImage.jsp");
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
