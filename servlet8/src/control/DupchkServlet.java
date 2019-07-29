package control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.dao.CustomerDAO;
import com.my.exception.NotFoundException;
import com.my.service.CustomerService;
import com.my.vo.Customer;

public class DupchkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");	
		//String pwd = request.getParameter("pwd");
		
		CustomerService service = new CustomerService();
		String str = service.dupchk(id);
		
		/*
		 * int state = -1;
		 * 
		 * CustomerDAO dao = new CustomerDAO(); try { dao.selectById(id); state =1;
		 * 
		 * }catch(NotFoundException e) { e.printStackTrace(); }
		 */
		//String str = "{\"status\":" + state +"}";
		request.setAttribute("result", str);
		String path = "/result.jsp";
		RequestDispatcher rd = 
				request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

}