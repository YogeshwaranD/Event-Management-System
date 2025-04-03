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
 * Servlet implementation class booking
 */
@WebServlet("/booking")
public class booking extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters including start_time, end_time, and cost
        String Name = request.getParameter("full_name");
        String Email = request.getParameter("email");
        String Phone = request.getParameter("phone_number");
        String Date = request.getParameter("event_date");
        String Location = request.getParameter("event_location");
        String Guests = request.getParameter("number_of_guests");
        String StartTime = request.getParameter("start_time");  // New parameter for start time
        String EndTime = request.getParameter("end_time");  // New parameter for end time
        String Cost = request.getParameter("cost");  // Use cost from form
        String Description = request.getParameter("event_description");

        try {
            // Modified SQL query to include cost after guests
            String query = "INSERT INTO booking(Name, Email, Phone, Date, Location, Guests, Cost, StartTime, EndTime, Description) VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = myconnection.getConnection().prepareStatement(query);
            ps.setString(1, Name);
            ps.setString(2, Email);
            ps.setString(3, Phone);
            ps.setString(4, Date);
            ps.setString(5, Location);
            ps.setString(6, Guests);
            ps.setString(7, Cost);  // Insert cost right after guests
            ps.setString(8, StartTime);  // Set start time
            ps.setString(9, EndTime);  // Set end time
            ps.setString(10, Description);

            // Execute the query to insert the booking data
            if (ps.executeUpdate() > 0) {
                // Store details in the session
                request.getSession().setAttribute("full_name", Name);
                request.getSession().setAttribute("email", Email);
                request.getSession().setAttribute("phone_number", Phone);
                request.getSession().setAttribute("event_date", Date);
                request.getSession().setAttribute("event_location", Location);
                request.getSession().setAttribute("number_of_guests", Guests);
                request.getSession().setAttribute("cost", Cost);  // Store cost
                request.getSession().setAttribute("start_time", StartTime);  // Store start time
                request.getSession().setAttribute("end_time", EndTime);  // Store end time
                request.getSession().setAttribute("event_description", Description);

                // Redirect to confirmation page
                response.sendRedirect("confirmation.jsp");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
