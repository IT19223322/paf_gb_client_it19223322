package model;
import com.customerpay;

import java.io.IOException;
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/customerpayApi")
public class customerpayApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	customerpay cusObj = new customerpay();
    
    public customerpayApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	//doPost complete
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 String output = cusObj.insertCustomerpay(request.getParameter("Card_No"), 
				 request.getParameter("Name_on_card"), 
				request.getParameter("Exp_date"), 
				request.getParameter("Cvv")); 
				response.getWriter().write(output); 

		
	}

	//doPut not complete
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 Map paras = getParasMap(request); 
		 String output = cusObj.updateCustomerpay(paras.get("hidItemIDSave").toString(), 
		 paras.get("Card_No").toString(), 
		 paras.get("Name_on_card").toString(), 
		paras.get("Exp_date").toString(), 
		paras.get("Cvv").toString()); 
		response.getWriter().write(output);
		
	}

	//doDelete
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Map paras = getParasMap(request); 
			String output = cusObj.deleteCustomerpay(paras.get("Card_Id").toString()); 
			response.getWriter().write(output); 
		
		
	}
	
	private static Map getParasMap(HttpServletRequest request) 
	{ 
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
