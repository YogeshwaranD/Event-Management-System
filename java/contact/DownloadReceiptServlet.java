package contact;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

@WebServlet("/downloadReceipt")
public class DownloadReceiptServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve booking details from session
        String Name = (String) request.getSession().getAttribute("full_name");
        String Email = (String) request.getSession().getAttribute("email");
        String Phone = (String) request.getSession().getAttribute("phone_number");
        String Date = (String) request.getSession().getAttribute("event_date");
        String Location = (String) request.getSession().getAttribute("event_location");
        String Guests = (String) request.getSession().getAttribute("number_of_guests");
        String StartTime = (String) request.getSession().getAttribute("start_time");  // Start time
        String EndTime = (String) request.getSession().getAttribute("end_time");  // End time
        String Cost = (String) request.getSession().getAttribute("cost");  // Cost
        String Description = (String) request.getSession().getAttribute("event_description");

        // Check if all required session attributes are present
        if (Name == null || Email == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Booking details not found in session.");
            return;
        }

        // Create the receipt content
        String receiptContent = "Booking Confirmation\n\n"
                + "Name: " + Name + "\n"
                + "Email: " + Email + "\n"
                + "Phone: " + Phone + "\n"
                + "Event Date: " + Date + "\n"
                + "Location: " + Location + "\n"
                + "Number of Guests: " + Guests + "\n"
                + "Start Time: " + StartTime + "\n"   // Display Start Time
                + "End Time: " + EndTime + "\n"       // Display End Time
                + "Cost: " + Cost + " INR\n"          // Display Cost
                + "Event Description: " + Description + "\n";

        // Set response headers for downloading the receipt as a PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=receipt.pdf");

        try {
            // Use iText to generate the PDF
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph(receiptContent));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward POST requests to doGet for PDF download
        doGet(request, response);
    }
}
