package contact;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class dashboard
 */
@WebServlet("/dashboard")
public class dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public dashboard() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		  response.setContentType("text/html");
	        PrintWriter pw = response.getWriter();

	        // Check if the 'booking' button was clicked
	        if ("booking".equals(request.getParameter("booking"))) {
	            try {
	                // Database connection
	                String query = "SELECT * FROM catering";  // Your table name
	                PreparedStatement ps = myconnection.getConnection().prepareStatement(query);
	                ResultSet rs = ps.executeQuery();

	                // Store the result in a request attribute
	                request.setAttribute("resultSet", rs);

	                // Forward to the admin page (Admin.html) to display the data
	                RequestDispatcher rd = request.getRequestDispatcher("Admin.html");
	                rd.forward(request, response);

	            } catch (Exception ex) {
	                ex.printStackTrace();
	                response.getWriter().append("Error: " + ex.getMessage());
	            }}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
