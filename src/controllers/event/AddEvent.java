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
 * Servlet implementation class AddEvent
 */
@WebServlet(value="/Event/Add")
public class AddEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEvent() {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/event/form.jsp");
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("/event/form.jsp");
			Event newEvent = new Event();
			
			
			//Request Verification (within Model for clean code)
				request.setAttribute("errName", newEvent.setName(request.getParameter("name")));
				request.setAttribute("errClient", newEvent.setClient(request.getParameter("client")));
				request.setAttribute("errLocation", newEvent.setLocation(request.getParameter("location")));
				request.setAttribute("errTelephone", newEvent.setTelephone(request.getParameter("telephone")));
				request.setAttribute("errComments", newEvent.setComments(request.getParameter("comments")));
				request.setAttribute("errEventDate", newEvent.setEventDate(request.getParameter("eventDate")));
			
				
			request.setAttribute("model",newEvent);
				
			if (newEvent.hasError()) {
				request.setAttribute("ErrCtlMsg", "Event Adding Error");
			} else {
				Event.addNew(newEvent);
				Logs.addNew(new Logs((int)session.getAttribute("uid"),"Event", session.getAttribute("uname") + " Added New Event Name:" + newEvent.getName() + ", on Date: " + newEvent.getEventDate() ,""));
				request.setAttribute("SucCtlMsg", "Event Added Successfully");
				request.setAttribute("list", Event.getAll());
				dispatcher = request.getRequestDispatcher("/event/table.jsp");
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
