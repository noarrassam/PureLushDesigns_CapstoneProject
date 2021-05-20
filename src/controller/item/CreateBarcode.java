	package controller.item;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;

import dbHelpers.BarcodeQuery;
import models.Item;

/**
 * Servlet implementation class CreateBarcode
 */
@WebServlet("/CreateBarcode")
public class CreateBarcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateBarcode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if (session.getAttribute("urole").equals("Administrator") || session.getAttribute("urole").equals("Manager")) {
		RequestDispatcher dispatcher = request.getRequestDispatcher("");	
		//get items id
		
		String value=request.getParameter("barcodeId");
	   
		
		    
	    
	   //get id to generate barcode for items
	  
				
 
		//create ReadRecord class
		BarcodeQuery rr = new BarcodeQuery(value);
		rr.doBarcode();
				
		Item item = rr.getItem();
		response.setContentType("image/jpg");
		Code128Bean code128 = new Code128Bean();
		code128.setHeight(15f);
		code128.setModuleWidth(0.3);
		code128.setQuietZone(10);
		code128.doQuietZone(true);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png",300,BufferedImage.TYPE_BYTE_BINARY,false,0);
		
		code128.generateBarcode(canvas, value);
		canvas.finish();
		
		ServletOutputStream responseOutputStream = response.getOutputStream();
		responseOutputStream.write(baos.toByteArray());
		responseOutputStream.flush();
		responseOutputStream.close();
		
				
		//pass item and control to updateForm.jsp
				 request.setAttribute("item", item);
				 dispatcher = request.getRequestDispatcher("/BarcodeDisplay.jsp");
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
