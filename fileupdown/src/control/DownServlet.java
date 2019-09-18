package control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/down")
public class DownServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String realPath = getServletContext().getRealPath("files");
		String fileName = request.getParameter("fileName");
		
		File file = new File(realPath, fileName);
		FileInputStream fis = new FileInputStream(file);
		
		response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
		response.setHeader("Contnet-Disposition", "attachment; filename=" + fileName +";");
		
		OutputStream os = response.getOutputStream();
		
		int readValue = -1;
		while((readValue = fis.read()) != -1) {
			System.out.println((char)readValue);
			os.write(readValue);
		}
		os.flush();
		os.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
