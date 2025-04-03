package contact;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class catering
 */
@WebServlet("/catering")
public class catering extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public catering() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());


	    		String Name=request.getParameter("name");
	    		String Email =request.getParameter("email");
	    		String Phone=request.getParameter("phone");
	    		String Menu=request.getParameter("menu");
	    		String Guests=request.getParameter("guests");
	    		String Notes=request.getParameter("notes");
	    		String Date=request.getParameter("date");
	    		String Time=request.getParameter("time");
	    		try {
	    			String query="insert into catering(Name,Email,Phone,Menu,Guests,Notes,Date,Time)values(?,?,?,?,?,?,?,?)";
	    			PreparedStatement ps=myconnection.getConnection().prepareStatement(query);
	    			ps.setString(1, Name);
	    			ps.setString(2, Email);
	    			ps.setString(3, Phone);
	    			ps.setString(4, Menu);
	    			ps.setString(5, Guests);
	    			ps.setString(6, Notes);
	    			ps.setString(7, Date);
	    			ps.setString(8, Time);
	    			if(ps.executeUpdate()>0) {
	    				  request.setAttribute("successMessage", "Your details were submitted successfully!");

	    			        // Forward the request to the same page (no redirection)
	    			        RequestDispatcher rd = request.getRequestDispatcher("Contactus.html"); // Replace with your actual page
	    			        rd.forward(request, response);
	    			}
	    		}
	    		catch(Exception ex){
	    			System.out.println(ex);
	    		}
		
		
		
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
