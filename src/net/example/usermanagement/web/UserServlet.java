package net.example.usermanagement.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Event;
import models.Supplier;
import net.example.usermanagement.dao.UserDAOHibernate;
import net.example.usermanagement.model.Logs;
import net.example.usermanagement.model.User;

@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOHibernate userDAO;
	private Supplier sup;

	public void init() {
		userDAO = new UserDAOHibernate();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/dologin":
				loginUser(request, response);
				break;
			case "/list":
				listUser(request, response);
				break;
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertUser(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateUser(request, response);
				break;
			case "/updateSettings":
				updateSettings(request, response);
				break;
			case "/logout":
				logoutUser(request, response);
				break;
//			case "/item":
//				showItem(request, response);
//				break;
//			case "/addItem":
//				insertItem(request, response);
//				break;
			case "/viewLogs":
				viewLogs(request, response);
				break;
			case "/changePass":
				changePass(request, response);
				break;	
			case "/changePassword":
				changePassword(request, response);
				break;
			case "/changePassGenUsers":
				changePassGenUsers(request, response);
				break;
			case "/changePassGenUsersName":
				changePassGenUsersName(request, response);
				break;
			case "/changeManagersPass":
				changeManagersPass(request, response);
				break;	
			default:
				showLoginForm(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void changeManagersPass(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		//int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String currentPassword = request.getParameter("currentPassword");
		String password = request.getParameter("password");
	
		System.out.print("new pass"+password);
		
		System.out.print("Old Pass"+currentPassword);
		
		String hash = MD5(currentPassword);
		HttpSession session = request.getSession();
		User currentUser = userDAO.selectUser((String) session.getAttribute("uemail"),
				(String) session.getAttribute("urole"));
		
		if(hash != null && currentPassword.length()>0 && hash.equals(currentUser.getPassword())) {
			String newHash = MD5(password);
			System.out.println("MD5(password)" + newHash);
			currentUser.setName(name);
			currentUser.setPassword(newHash);
			userDAO.updateUser(currentUser);	
		}
		response.sendRedirect("logout");
	}
	
private void changePassGenUsersName(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		//int id = Integer.parseInt(request.getParameter("id"));
		String currentName = request.getParameter("name");
		String name = request.getParameter("name");
		
		HttpSession session = request.getSession();
		User currentUser = userDAO.selectUser((String) session.getAttribute("uemail"),
				(String) session.getAttribute("urole"));
		
		
		if (currentName.length()>0 && !name.equals(currentUser.getName())) {

			currentUser.setName(name);
			
			userDAO.updateUser(currentUser);	
		}
		response.sendRedirect("logout");
	}

	
	private void changePassGenUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		
		//int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String currentPassword = request.getParameter("currentPassword");
		String password = request.getParameter("password");
		
		//User users  = new User(id, currentPassword, password);
		System.out.print("new pass"+password);
		
		System.out.print("Old Pass"+currentPassword);
		
		String hash = MD5(currentPassword);
		HttpSession session = request.getSession();
		User currentUser = userDAO.selectUser((String) session.getAttribute("uemail"),
				(String) session.getAttribute("urole"));
		//User newUser = userDAO.selectUser(id);
		//currentUser.setPassword(password);
		
		if(hash != null && currentPassword.length()>0 && hash.equals(currentUser.getPassword())) {
			String newHash = MD5(password);
			System.out.println("MD5(password)" + newHash);
			currentUser.setName(name);
			currentUser.setPassword(newHash);
			userDAO.updateUser(currentUser);	
		}
		response.sendRedirect("logout");
	}

	private void changePassword(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	
		int id = Integer.parseInt(request.getParameter("id"));
		String currentPassword = request.getParameter("currentPassword");
		String password = request.getParameter("password");
		System.out.print("new pass"+password);
		
		System.out.print("Old Pass"+currentPassword);

		String hash = MD5(currentPassword);

		HttpSession session = request.getSession();
		User currentUser = userDAO.selectUser((String) session.getAttribute("uemail"),
				(String) session.getAttribute("urole"));
		User newUser = userDAO.selectUser(id);
		//newUser.setPassword(password);
		if(hash != null && currentPassword.length()>0 && hash.equals(currentUser.getPassword()) && currentUser.getRole().equals("Administrator")) {
			String newHash = MD5(password);
			System.out.println("MD5(password)" + newHash);
			newUser.setPassword(newHash);
			userDAO.updateUser(newUser);	
		}
		response.sendRedirect("list");
	}
	
	private void changePass(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("changePassword.jsp");
		dispatcher.forward(request, response);
	}
	
	private void viewLogs(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator")) {
			RequestDispatcher dispatcher = null;
			List<Logs> myLogs = userDAO.selectAllLogs();
			request.setAttribute("logs", myLogs);
			dispatcher = request.getRequestDispatcher("logs-form.jsp");
			dispatcher.forward(request, response);
		}
		else
		{
			throw new RuntimeException("Invalid access");
		}
		
	}

	private void showLoginForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (sessionUser(request)) {
			response.sendRedirect("list");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("user-login.jsp");
			dispatcher.forward(request, response);
		}
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = userDAO.loginUser(email, password);

		if (sessionUser(request)) {
			response.sendRedirect("list");
		} else if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("uemail", user.getEmail());
			session.setAttribute("urole", user.getRole());
			session.setAttribute("uid", user.getId());
			session.setAttribute("uname", user.getName()); 
			
			response.sendRedirect("list");
		} else {
			request.setAttribute("isLoginFailed", true);
			request.getRequestDispatcher("user-login.jsp").forward(request, response);
			// response.sendRedirect("login");
		}
	}

	private boolean sessionUser(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String uemail = (String) session.getAttribute("uemail");
		String urole = (String) session.getAttribute("urole");
		if (uemail == null || urole == null)
			return false;
		return true;
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {

		if (sessionUser(request)) {
			RequestDispatcher dispatcher = null;
			HttpSession session = request.getSession();
			if (session.getAttribute("urole").equals("Administrator")) {
				List<User> listUser = userDAO.selectAllUsers();
				request.setAttribute("listUser", listUser);
				
				dispatcher = request.getRequestDispatcher("user-admin.jsp");
			} else if(session.getAttribute("urole").equals("General User")){ 
				User user = userDAO.selectUser((String) session.getAttribute("uemail"),
						(String) session.getAttribute("urole"));
				request.setAttribute("user", user);
				dispatcher = request.getRequestDispatcher("user-nonadmin.jsp");
				
				/*
				 * List<Event> events = Event.getAll(); request.setAttribute("list", events);
				 */
				if (request.getParameter("search") != null) {
					if (!request.getParameter("search").trim().isEmpty()) {
						request.setAttribute("search", request.getParameter("search").trim());
						request.setAttribute("list", Event.search(request.getParameter("search")));	
					} else {
						//List<Event> events = Event.getAll();
						//request.setAttribute("list", events);
						request.setAttribute("list", Event.getAll());
					}
				} else {
					request.setAttribute("list", Event.getAll());
				}
				dispatcher = request.getRequestDispatcher("user-nonadmin.jsp");
			} else if(session.getAttribute("urole").equals("Manager")){
				User user = userDAO.selectUser((String) session.getAttribute("uemail"),
						(String) session.getAttribute("urole"));
				request.setAttribute("user", user);
				if (request.getParameter("search") != null) {
					if (!request.getParameter("search").trim().isEmpty()) {
						request.setAttribute("search", request.getParameter("search").trim());
						request.setAttribute("list", Event.search(request.getParameter("search")));	
					} else {
						//List<Event> events = Event.getAll();
						//request.setAttribute("list", events);
						request.setAttribute("list", Event.getAll());
					}
				} else {
					request.setAttribute("list", Event.getAll());
				}
				//List<Event> events = Event.getAll();
				//request.setAttribute("list", events);
				dispatcher = request.getRequestDispatcher("managers.jsp");
			} 
			
			dispatcher.forward(request, response);
		} else
			response.sendRedirect("login");
	}

	private void logoutUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute("uemail", null);
		session.setAttribute("urole", null);
		request.setAttribute("isLogoutSuccess", true);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-login.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
	}

//	private void showItem(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		RequestDispatcher dispatcher = request.getRequestDispatcher("ItemForm.jsp");
//		dispatcher.forward(request, response);
//	}

//	private void insertItem(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
//		Item item = new Item();
//		query.doAdd(item);
//		response.sendRedirect("item");
//	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String status = request.getParameter("status");
		String logs = request.getParameter("logs");
		User newUser = new User(name, email, password, role, status, LocalDateTime.now());
		userDAO.insertUser(newUser);
		response.sendRedirect("list");
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String role = request.getParameter("role");
		String status = request.getParameter("status");

		User user = new User(id, name, email, password, role, status);

		User currentUser = userDAO.selectUser(id);
		if (status.equals(currentUser.getStatus())) {
			user.setJoiningDateTime(currentUser.getJoiningDateTime());
			user.setLeavingDateTime(currentUser.getLeavingDateTime());
		} else if (status.equalsIgnoreCase("inactive")) {
			user.setLeavingDateTime(LocalDateTime.now());
			user.setJoiningDateTime(currentUser.getJoiningDateTime());
		} else {
			user.setLeavingDateTime(null);
			user.setJoiningDateTime(LocalDateTime.now());
			user.setPassword(currentUser.getPassword());
		}
		userDAO.updateUser(user);
		response.sendRedirect("list");
	}

	private void updateSettings(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		//String password = request.getParameter("password");
		String role = request.getParameter("role");
		String status = request.getParameter("status");

		User user = new User(id, name, email, role, status);

		User currentUser = userDAO.selectUser(id);
		user.setPassword(currentUser.getPassword());
		if (status.equals(currentUser.getStatus())) {
			user.setJoiningDateTime(currentUser.getJoiningDateTime());
			user.setLeavingDateTime(currentUser.getLeavingDateTime());
		} else if (status.equalsIgnoreCase("inactive")) {
			user.setLeavingDateTime(LocalDateTime.now());
			user.setJoiningDateTime(currentUser.getJoiningDateTime());
		} else {
			user.setLeavingDateTime(null);
			user.setJoiningDateTime(LocalDateTime.now());
			user.setPassword(currentUser.getPassword());
		}
		userDAO.updateUser(user);
		response.sendRedirect("list");
	}
	
    private String MD5(String c) throws UnsupportedEncodingException {
		
    	try {
			MessageDigest digs = MessageDigest.getInstance("MD5");
			byte[] messageDigest = digs.digest(c.getBytes());
			BigInteger number = new BigInteger(1, messageDigest );
			String hashtext = number.toString(16);
			while(hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
						
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			return "";
		}
    }

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("userId"));
		userDAO.deleteUser(id);
		response.sendRedirect("list");
	}

}