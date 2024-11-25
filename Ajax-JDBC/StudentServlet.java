import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getStudentDetails")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Database credentials
    private final String DB_URL = "jdbc:mysql://localhost:3306/studentdb";
    private final String DB_USER = "root"; // Replace with your MySQL username
    private final String DB_PASSWORD = ""; // Replace with your MySQL password

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String regNo = request.getParameter("regNo");

        if (regNo != null && !regNo.trim().isEmpty()) {
            Connection conn = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;

            try {
                // Load MySQL JDBC Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establish connection
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                // Prepare SQL query
                String sql = "SELECT * FROM students WHERE reg_no = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, regNo);

                // Execute query
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String email = rs.getString("email");
                    String course = rs.getString("course");

                    out.println("<html><body>");
                    out.println("<h2>Student Details</h2>");
                    out.println("<p><strong>Registration Number:</strong> " + regNo + "</p>");
                    out.println("<p><strong>Name:</strong> " + firstName + " " + lastName + "</p>");
                    out.println("<p><strong>Email:</strong> " + email + "</p>");
                    out.println("<p><strong>Course:</strong> " + course + "</p>");
                    out.println("<br><a href='index.html'>Back</a>");
                    out.println("</body></html>");
                } else {
                    out.println("<html><body>");
                    out.println("<h2>Student Details</h2>");
                    out.println("<p>No student found with Registration Number: " + regNo + "</p>");
                    out.println("<br><a href='index.html'>Back</a>");
                    out.println("</body></html>");
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                out.println("<html><body>");
                out.println("<h2>Error</h2>");
                out.println("<p>JDBC Driver not found.</p>");
                out.println("<br><a href='index.html'>Back</a>");
                out.println("</body></html>");
            } catch (SQLException e) {
                e.printStackTrace();
                out.println("<html><body>");
                out.println("<h2>Error</h2>");
                out.println("<p>Database error occurred.</p>");
                out.println("<br><a href='index.html'>Back</a>");
                out.println("</body></html>");
            } finally {
                // Close resources
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            // Handle case where registration number is not provided
            out.println("<html><body>");
            out.println("<h2>Error</h2>");
            out.println("<p>Please provide a valid Registration Number.</p>");
            out.println("<br><a href='index.html'>Back</a>");
            out.println("</body></html>");
        }
    }
}
