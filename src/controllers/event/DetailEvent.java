package controllers.event;

import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import helpers.Constants;
import models.Event;
import models.EventItem;
import models.Logs;
import net.example.usermanagement.model.User;

/**
 * Servlet implementation class DetailEvent
 */
@WebServlet(value="/Event/Details")
public class DetailEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator") || session.getAttribute("urole").equals("Manager")||session.getAttribute("urole").equals("General User")) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		if (request.getParameter("id") != null) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Event model = Event.getByID(id);
				if (model != null) {
					
					List<EventItem> linkedList = EventItem.getLinkedItems(id);
					double totalCost = 0D; 
					for (EventItem item : linkedList) {
						if (!item.isMultibarcode()) 
						{
							totalCost+=item.getQuantityHistoric()*item.getCost();
							continue;
						}
						
						totalCost += item.getCost();
					}
					request.setAttribute("model", model);
					request.setAttribute("listLinkedItems", linkedList);
					request.setAttribute("totalCost", totalCost);
					request.setAttribute("listAllItems", EventItem.getAllItems());
					request.setAttribute("listhmUsers", EventItem.getUsers());
					dispatcher = request.getRequestDispatcher("/event/details.jsp");
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
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String urole = (String) session.getAttribute("urole");
		int uid = (int) session.getAttribute("uid");
			
		if (urole != null) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/event/details.jsp");
		if (request.getParameter("eventID") != null) {
			try {
				int eventId = Integer.parseInt(request.getParameter("eventID"));
				Event model = Event.getByID(eventId);
				if (model != null) {  
					
				int[] barcodesLoadArr = convertBarcodesToIntArray(request.getParameterValues("barcodesLoad"));
				int[] barcodesReturnArr = convertBarcodesToIntArray(request.getParameterValues("barcodesReturn"));
				
				
				
				if (barcodesLoadArr != null || barcodesReturnArr != null) {
						List<EventItem> barcodesLoad = new ArrayList<EventItem>();
						List<EventItem> barcodesReLoad = new ArrayList<EventItem>();
						List<EventItem> barcodesQtyReLoad = new ArrayList<EventItem>();
						List<EventItem> barcodesReturn = new ArrayList<EventItem>();
						
						
						HashMap<String, EventItem> linkedMap =  EventItem.getHashedLinkedItems(eventId);
						HashMap<String, EventItem> availableMap =   EventItem.getHashedAllItems();
						//request.setAttribute("listhmUsers", EventItem.getUsers());
						int affectedRows = 0;
						//Iterates through Load Barcodes coming from request
						if (barcodesLoadArr != null ) {
							List<Logs> logList = new ArrayList<Logs>();
							for (int barcode : barcodesLoadArr) {
								EventItem newItem = availableMap.get(String.valueOf(barcode));
								if (newItem != null) {
									//If not first Time
									String qty= "";
									if (linkedMap.get(String.valueOf(newItem.getItemID())) != null) {
										//getting the item from LinkedMap method cuz it has more data about the item
										newItem = linkedMap.get(String.valueOf(newItem.getItemID()));
										if (!newItem.isMultibarcode()) {
											
											logList.add(new Logs(uid, "Items Loaded", session.getAttribute("uname") + " Loaded "  + newItem.getAddedQty() + " "+ newItem.getName() + ", into Event:" + model.getName() + " Dated on: " + model.getEventDate().toString(),""));
											newItem.setAddedQty(Integer.parseInt(request.getParameter("qty_Load"+barcode)));
											barcodesQtyReLoad.add(newItem);
										} else {
											logList.add(new Logs(uid, "Item Loaded", session.getAttribute("uname") + " reLoaded "  + newItem.getName() + ", into Event:" + model.getName() + " Dated on: " + model.getEventDate().toString(),""));
											barcodesReLoad.add(newItem);
										}
										continue; //as newItem is added to another list now to Update instead of Insert new Items
									}	
									qty = String.valueOf(newItem.getQuantity()); 
									logList.add(new Logs(uid, "Item Loaded", session.getAttribute("uname") + " Loaded " + newItem.getName() + ", into Event:" + model.getName() + " Dated on: " + model.getEventDate().toString(),""));
									barcodesLoad.add(newItem);//first time loaded items
								}
								
							}
							affectedRows += EventItem.insertNewLoaded(barcodesLoad, eventId, uid);
							affectedRows += EventItem.IncreaseQuantity(barcodesQtyReLoad,eventId, uid);
							affectedRows += EventItem.updateReload(barcodesReLoad,eventId, uid);
							Logs.addNewGroup(logList);
							
						}
						
						if (barcodesReturnArr != null ) {
							List<Logs> logList = new ArrayList<Logs>();
							for (int barcode : barcodesReturnArr) {
								EventItem returnItem = linkedMap.get(String.valueOf(barcode));
								if (returnItem != null) {
									if (!returnItem.isMultibarcode()) {
										int removedQuantity = Integer.parseInt(request.getParameter("qty_Return"+barcode));
										//Removes quantity from current if already exists 
										if (removedQuantity < returnItem.getQuantity()) {
											logList.add(new Logs(uid, "Items Returned", session.getAttribute("uname") + " returned " + removedQuantity + " " + returnItem.getName() + ", into Event:" + model.getName() + " Dated on: " + model.getEventDate().toString(),""));
											if(EventItem.decreaseQuantity(returnItem,eventId, uid,removedQuantity)) affectedRows++;
											continue;
										} 	
									}
									logList.add(new Logs(uid, "Item Returned", session.getAttribute("uname") + " returned " + returnItem.getName() + ", into Event:" + model.getName() + " Dated on: " + model.getEventDate().toString(),""));
									barcodesReturn.add(returnItem);
								}
							}
							affectedRows+= EventItem.updateReturned(barcodesReturn, eventId, uid);
							Logs.addNewGroup(logList);
						}
						
						double totalCost = 0D;
						List<EventItem> linkedList = EventItem.getLinkedItems(eventId);
						for (EventItem item : linkedList) {
							if (!item.isMultibarcode()) 
							{
								totalCost+=item.getQuantityHistoric()*item.getCost();
								continue;
							}
							
							totalCost += item.getCost();
						}
						request.setAttribute("model", model);
						request.setAttribute("listLinkedItems", linkedList);
						request.setAttribute("totalCost", totalCost);
						request.setAttribute("listAllItems", EventItem.getAllItems());
						request.setAttribute("listhmUsers", EventItem.getUsers());
						dispatcher = request.getRequestDispatcher("/event/details.jsp");
					
						request.setAttribute("SucCtlMsg", "Event Updated (" + affectedRows + " Items)");
						
//						response.sendRedirect(Constants.URL_PREFIX+"Event/Details?=" + eventId);
//						return;
					} else {
						request.setAttribute("ErrCtlMsg", "Event Not Found");
					}
				}
			} catch (NumberFormatException nfe) {
				request.setAttribute("ErrCtlMsg", "Can't fulfil request without Event ID");
			} 
		}
		dispatcher.forward(request, response);
	} else
	{
		throw new RuntimeException("Invalid access");
	}
		
	}

	private int[] convertBarcodesToIntArray(String[] strings) {
		int[] array;
        try {
        	array = Arrays.asList(strings).stream().mapToInt(Integer::parseInt).toArray();
        } catch (Exception e) {
        	return null;
        }
	    
	    return array;
	}

	
	
}

