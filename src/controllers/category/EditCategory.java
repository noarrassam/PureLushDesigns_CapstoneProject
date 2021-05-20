package controllers.category;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Category;
import models.Supplier;
/**
 * Servlet implementation class EditCategory
 */
@WebServlet(value="/Category/Edit")
public class EditCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		if (request.getParameter("id") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Category model = Category.getByID(id);
				if (model != null) {
					request.setAttribute("model", Category.getByID(id));
					request.setAttribute("action", "Edit");
					dispatcher = request.getRequestDispatcher("/category/form.jsp");
				} else {
					request.setAttribute("ErrCtlMsg", "Category Not Found");
				}
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
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/supplier/form.jsp");
		Category newCategory = new Category();
		
		
		//Request Verification (within Model for clean code)
			try {
				request.setAttribute("errName", newCategory.setId(Integer.parseInt(request.getParameter("id"))));
			} catch (NumberFormatException nfe) {
				response.getWriter().append("Invalid ID, please restart Edit form.");
				return;
			} 
			request.setAttribute("errName", newCategory.setCategoryType(request.getParameter("name")));
			
			
		request.setAttribute("model",newCategory);
			
		if (newCategory.hasError()) {
			request.setAttribute("action", "Edit");
			request.setAttribute("ErrCtlMsg", "Error editing category");
		} else {
			
			Category.editByID(newCategory);
			request.setAttribute("SucCtlMsg", "Category Edited Successfully");
			System.out.println("Edited" + newCategory.getCategoryType() + " With ID of " + newCategory.getId());
			request.setAttribute("list", Category.getAll());
			dispatcher = request.getRequestDispatcher("/category/table.jsp");
			
		}
		
		dispatcher.forward(request, response);
	}

}
