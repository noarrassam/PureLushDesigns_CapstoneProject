package controller.item;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbHelpers.BarcodeQuery;

/**
 * Servlet implementation class SaveBarcode
 */
@WebServlet("/SaveBarcode")
public class SaveBarcode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveBarcode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				String value=request.getParameter("ItemGroupID");
				//create ReadRecord class
			BarcodeQuery rr = new BarcodeQuery(value);
//				
//				//use read record to get the book data
			rr.doBarcode();
//				
//				Item item = rr.getItem();
		//response.setContentType("image/jpg");
		//Code128Bean code128 = new Code128Bean();
		//code128.setHeight(15f);
		//code128.setModuleWidth(0.3);
		//code128.setQuietZone(10);
		//code128.doQuietZone(true);
		//ByteArrayOutputStream baos = new ByteArrayOutputStream();
		//BitmapCanvasProvider canvas = new BitmapCanvasProvider(baos, "image/x-png",300,BufferedImage.TYPE_BYTE_BINARY,false,0);
		//code128.generateBarcode(canvas, value);
		//canvas.finish();
		//
		//ServletOutputStream responseOutputStream = response.getOutputStream();
		//responseOutputStream.write(baos.toByteArray());
		//responseOutputStream.flush();
		//responseOutputStream.close();

				
		//pass item and control to updateForm.jsp
//				request.setAttribute("item", item);
//				request.setAttribute("item", model);
//				dispatcher.forward(request, response);
				
				
				
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
