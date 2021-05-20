package controllers.event;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Event;
import models.Logs;

/**
 * Servlet implementation class EditEvent
 */
@WebServlet(value="/Event/Edit")
public class EditEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEvent() {
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		if (request.getParameter("id") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Event model = Event.getByID(id);
				if (model != null) {
					request.setAttribute("model", Event.getByID(id));
					request.setAttribute("action", "Edit");
					dispatcher = request.getRequestDispatcher("/event/form.jsp");
				} else {
					request.setAttribute("ErrCtlMsg", "Event Not Found");
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
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator") || session.getAttribute("urole").equals("Manager")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/event/form.jsp");
			Event newEvent = new Event();
			
			
			//Request Verification (within Model for clean code)
				try {
					request.setAttribute("errName", newEvent.setId(Integer.parseInt(request.getParameter("id"))));
				} catch (NumberFormatException nfe) {
					response.getWriter().append("Invalid ID, please restart Edit form.");
					return;
				} 
				request.setAttribute("errName", newEvent.setName(request.getParameter("name")));
				request.setAttribute("errClient", newEvent.setClient(request.getParameter("client")));
				request.setAttribute("errLocation", newEvent.setLocation(request.getParameter("location")));
				request.setAttribute("errTelephone", newEvent.setTelephone(request.getParameter("telephone")));
				request.setAttribute("errComments", newEvent.setComments(request.getParameter("comments")));
				request.setAttribute("errEventDate", newEvent.setEventDate(request.getParameter("eventDate")));
			
				
			request.setAttribute("model",newEvent);
				
			if (newEvent.hasError()) {
				request.setAttribute("action", "Edit");
				request.setAttribute("ErrCtlMsg", "Event Editing Error");
			} else {
				
				Event.editByID(newEvent);
				request.setAttribute("SucCtlMsg", "Event Edited Successfully");
				Logs.addNew(new Logs((int)session.getAttribute("uid"),"Event","Edited Event Name:" + newEvent.getName() + ", on Date: " + newEvent.getEventDate() ,""));
				System.out.println("Edited" + newEvent.getName() + " With ID of " + newEvent.getId());
				request.setAttribute("list", Event.getAll());
				dispatcher = request.getRequestDispatcher("/event/table.jsp");
				
			}
			
			dispatcher.forward(request, response);
		} else
		{
			throw new RuntimeException("Invalid access");
		}
	}

}
