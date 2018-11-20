package test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WelcomeServlet extends HttpServlet
{
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		HttpSession hs=req.getSession();
		String fName=(String) hs.getAttribute("fName");
		
		pw.println("WELCOME.."+fName);
	}
}
