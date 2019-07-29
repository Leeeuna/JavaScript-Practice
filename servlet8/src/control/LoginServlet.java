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

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				
	}

	private CustomerService service; //공유객체
	public LoginServlet() {
		service = new CustomerService();
	}
	//private CustomerService service = new CustomerService(); //여러사용자
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		
		String str = service.login(id, pwd);
		request.setAttribute("result", str);
		//int state = -1;
		/*
		 * CustomerDAO dao = new CustomerDAO(); try { Customer c = dao.selectById(id);
		 * if(c.getPwd().contentEquals(pwd)) { state =1; } }catch(NotFoundException e) {
		 * e.printStackTrace(); }
		 */
		
		String path = "/result.jsp";
		RequestDispatcher rd = 
				request.getRequestDispatcher(path);
		rd.forward(request, response);
		
		
		
		//1.응답형식 지정하기. MIME값 활용
		//String str = "{\"status\":" + state + ",\"id\": \"" + id +"\"}";
//		response.setContentType("text/html;charset=utf-8");
//		//2.응답출력스트림 얻기
//		PrintWriter out = response.getWriter();
//		//3.응답하기	
//		out.print(str);


	}
}
