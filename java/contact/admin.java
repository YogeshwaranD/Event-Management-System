package contact;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import contact.myconnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String name=request.getParameter("u");
		String password=request.getParameter("p");
		   try {
	            // SQL query to check if the name and password exist in the database
	            String query = "SELECT * FROM admin WHERE name = ? AND password = ?";
	            PreparedStatement ps = myconnection.getConnection().prepareStatement(query);
	            ps.setString(1, name);
	            ps.setString(2, password);
	            ResultSet rs = ps.executeQuery();
	            
	            // If username and password match, redirect to admin.html
	            if (rs.next()) {
	                // Redirecting to admin.html upon successful login
	                response.sendRedirect("Admin.html");
	            } else {
	                // If credentials are incorrect, include a message on the current page
	                System.out.println("Incorrect username or password.");
	                // You can display a message or redirect the user to an error page or stay on the login page
	                response.getWriter().append("Incorrect username or password.");
	                // Alternatively, you can forward to a different page or reload the current page
	                // RequestDispatcher rd = request.getRequestDispatcher("login.html");
	                // rd.include(request, response);
	            }
	        } catch (Exception ex) {
	            System.out.println(ex);
	            response.getWriter().append("Error: " + ex.getMessage());
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
