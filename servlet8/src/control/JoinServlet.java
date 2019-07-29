package control;
 
import java.io.IOException; import java.io.PrintWriter; import
java.sql.Connection; import java.sql.DriverManager; import
java.sql.PreparedStatement; import java.sql.ResultSet; import
java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException; import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest; import
javax.servlet.http.HttpServletResponse;

import com.my.service.CustomerService;

public class JoinServlet extends HttpServlet { 
	private static final long serialVersionUID = 1L;
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
	}
	
	
//	private CustomerService service; //공유객체
//	public JoinServlet() {
//		service = new CustomerService();
//	}
	private CustomerService service; //공유객체
	public JoinServlet() {
		service = new CustomerService();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
	    String id = request.getParameter("id");
	    String pwd = request.getParameter("pass");
	    String name = request.getParameter("name");
	    String addr = request.getParameter("addr2");
	    String buildingno = request.getParameter("buildingno");

	    
	    System.out.println(id+pwd+name+buildingno+ "/" +addr);
	    
		String str = service.join(id, pwd, name, buildingno, addr);
		request.setAttribute("result", str);
	    String path = "/result.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	    
		
		
 	}
}
