package control;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * Servlet implementation class UpServlet
 */
@WebServlet("/up")
public class UpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//request.getParameter("t");
//		InputStream is = request.getInputStream(); 
//		InputStreamReader isr = new InputStreamReader(is); //한글자씩 읽어와서 한글 깨짐이 없음
//		int readValue = -1;
//		while((readValue = isr.read()) != -1) {
//			System.out.print((char)readValue);
//		}
		
		String saveDirectory = "files";
		String realPath = getServletContext().getRealPath(saveDirectory);
		int maxPostSize = 1000*1024;
		String encoding = "UTF-8";
		FileRenamePolicy policy = new com.my.util.MyFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request,realPath,maxPostSize,encoding,policy);
		
		String t = mr.getParameter("t");
	}

}
