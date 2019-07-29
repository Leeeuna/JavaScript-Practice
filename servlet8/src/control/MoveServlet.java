package control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//페이지 이동방법1 - 성공
		//response.sendRedirect("http://www.google.com");
		
		//페이지 이동방법2 - 실패
//		String path = "http://www.google.com"; //path설정 무의미
//		RequestDispatcher rd = request.getRequestDispatcher(path);
//		rd.forward(request, response ); 
		//같은 webcontent에서만 이동
		
//		String path = "jq/login.html";
//		response.sendRedirect(path);
		//응답헤더를 설정해서 웹브라우저에서 해당 path값을 재요청한다.
		
//		RequestDispatcher rd = request.getRequestDispatcher(path);
//		rd.forward(request, response); 
		//서버에서 path자원을 찾아서 이동하여 응답
		
		String opt = request.getParameter("opt");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if(opt == null || opt.equals("")) {
			out.println("<form method='post' action='move'>");
			out.println("<input type='hidden' name='opt'>");
			out.println("<input type='hidden' name='id' value='id1'>");
			out.println("<input type='hidden' name='pwd' value='p1'>");
			out.println("<button type='button' id='redirect'>리다이렉트</button>");
			out.println("<button type='button' id='forward'>포워드</button>");
			out.println("<button type='button' id='include'>인클루드</button>");
			out.println("</form>");
			out.println("<script>");
			out.println("var r = document.querySelector('#redirect');");
			out.println("var f = document.querySelector('#forward');");
			out.println("var i = document.querySelector('#include');");
			out.println("var form = document.querySelector('form');");
			out.println("var hidden= document.querySelector('input[type=hidden]');");
			out.println("r.addEventListener('click',function(){hidden.value='redirect';  form.submit(); });");
			out.println("f.addEventListener('click',function(){hidden.value='forward';  form.submit(); });");
			out.println("i.addEventListener('click',function(){hidden.value='include';  form.submit(); });");
			out.println("</script>");
		}
	}

	protected void doPost(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {
		String opt = request.getParameter("opt");
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print("BEFORE");//write at response buffer
		if(opt.equals("redirect")) {
			response.sendRedirect("login");
		}else if (opt.equals("forward")) {
			String path = "login";
			RequestDispatcher rd = 
					request.getRequestDispatcher(path);
			//Forwards a request from a servlet to another resource (servlet, JSP file, or HTML file) on the server.
			//the response buffer is automatically cleared before the forward.
			//포워드를 만나면 버퍼에 쌓여있는 것이 삭제가 된다. 그러므로 BEFORE가 출력되지않는다.
			rd.forward(request, response);
		}else if (opt.equals("include")) {
			String path = "login";
			RequestDispatcher rd = 
					request.getRequestDispatcher(path);
			rd.include(request, response);
			//Includes the content of a resource (servlet, JSP page, HTML file) in the response.
			//다른 자원의 결과값을 현재 사용중이 response에 포함함다.
		}
		out.print("AFTER");
		
	}
}
