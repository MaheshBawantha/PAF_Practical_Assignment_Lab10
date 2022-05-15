package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Servlet implementation class InquiriesAPI
 */
@WebServlet("/InquiriesAPI")
public class InquiriesAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Inquiry inquiryObj = null;
    
    public InquiriesAPI() {
        super();
        
    }
    
    

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//This one only for test purpose
		System.out.println("Came to POST METHOD in inquiryiesAPI");
		
		Inquiry Inquiry = new Inquiry();
		String output = Inquiry.insertInquiry(request.getParameter("accountNum"),
				 request.getParameter("Name"),
				request.getParameter("contactNum"),
				request.getParameter("email"),
				request.getParameter("inquiryDet"));
				response.getWriter().write(output);
				
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//This one only for test purpose
		System.out.println("Came to PUT METHOD in inquiryiesAPI");
		
		Inquiry Inquiry = new Inquiry();
		// TODO Auto-generated method stub
		Map paras = getParasMap(request);
		 String output = Inquiry.updateInquiry(paras.get("hidInquiryIDSave").toString(),
		 paras.get("accountNum").toString(),
		paras.get("Name").toString(),
		paras.get("contactNum").toString(),
		paras.get("email").toString(),
		paras.get("inquiryDet").toString()
		);
		response.getWriter().write(output);
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//This one only for test purpose
		System.out.println("Came to DELETE METHOD in inquiryiesAPI");
		
		// TODO Auto-generated method stub
		Inquiry Inquiry = new Inquiry();
		Map paras = getParasMap(request);
		 String output = Inquiry.deleteInquiry(paras.get("inquiryID").toString());
		response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request)
		{
		//This one only for test purpose
		System.out.println("Came to MAP FUNCTION in inquiryiesAPI");
			Map<String, String> map = new HashMap<String, String>();
	try
	 	{
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ?
			scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params)
				{ 
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				 }
	 	}
		catch (Exception e)
			 {
			 	}
		return map;
				}

}
