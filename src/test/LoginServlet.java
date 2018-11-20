package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet
{
	//public ServletContext sc;
	public Connection con;
	@Override
	public void init()
	{
		con=DBConnection.getCon();
	}
	@Override
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		String uName=req.getParameter("uname");
		String pWord=req.getParameter("pword");
		
		try
		{
			PreparedStatement ps=con.prepareStatement("select * from UserReg14 where uname=? and pword=?");
			ps.setString(1, uName);
			ps.setString(2, pWord);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				//httpSession
				HttpSession hs=req.getSession();
				hs.setAttribute("fName", rs.getString(3));
				
				pw.println("<form action='welcome' method='post'>");
				pw.println("<input type='submit' value='SUBMIT'>");
				pw.println("</form>");
			}
			else
			{
				RequestDispatcher rd= req.getRequestDispatcher("login.html");
				rd.include(req, res);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
}
