package contact;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;

/**
 * Servlet implementation class venues
 */
@WebServlet("/venues")
public class venues extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public venues() {
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
		String PhoneNumber=request.getParameter("phone");
		String Email=request.getParameter("email");
		String Venues=request.getParameter("venues");
		String Message=request.getParameter("message");

		try {
			String query="insert into venues(Name,PhoneNumber,Email,Venues,Message)values(?,?,?,?,?)";
			PreparedStatement ps=myconnection.getConnection().prepareStatement(query);
			ps.setString(1, Name);
			ps.setString(2, PhoneNumber);
			ps.setString(3, Email);
			ps.setString(4, Venues);
			ps.setString(5, Message);
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
