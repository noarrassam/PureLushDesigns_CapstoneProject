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
 * Servlet implementation class AddCategory
 */
@WebServlet(value="/Category/Add")
public class AddCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategory() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("action", "Add");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/category/form.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("action", "Add");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/category/form.jsp");
		Category newCategory = new Category();
		
		
		//Request Verification (within Model for clean code)
			request.setAttribute("errCategory", newCategory.setCategoryType(request.getParameter("category")));
			
			
		request.setAttribute("model",newCategory);
			
		if (newCategory.hasError()) {
			request.setAttribute("ErrCtlMsg", "Error adding Category");
		} else {
			Category.addNew(newCategory);
			request.setAttribute("SucCtlMsg", "Category Added Successfully");
			request.setAttribute("list", Category.getAll());
			dispatcher = request.getRequestDispatcher("/category/table.jsp");
			
		}
		
		dispatcher.forward(request, response);
	}

}
