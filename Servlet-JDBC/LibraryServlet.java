

import java.sql.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LibraryServlet")
public class LibraryServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String action = request.getParameter("action");
	        String accno = request.getParameter("accno");
	        String title = request.getParameter("title");
	        String author = request.getParameter("author");
	        String publisher = request.getParameter("publisher");
	        String edition = request.getParameter("edition");
	        String price = request.getParameter("price");
	        response.setContentType("text/html");

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	           
	        	 String JDBC_URL = "jdbc:mysql://localhost:3306/library_management";
	             String JDBC_USER = "root";
	             String JDBC_PASS = "mySql@22122004";
	            Connection con = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);

	            if (action.equals("Insert")) {
	                PreparedStatement ps = con.prepareStatement("INSERT INTO BOOKS (ACC_NO, TITLE, AUTHOR, PUBLISHER, EDITION, PRICE) VALUES (?, ?, ?, ?, ?, ?)");
	                ps.setString(1, accno);
	                ps.setString(2, title);
	                ps.setString(3, author);
	                ps.setString(4, publisher);
	                ps.setString(5, edition);
	                ps.setString(6, price);
	                ps.executeUpdate();
	                response.getWriter().println("Book inserted successfully.");
	            } else if (action.equals("Update")) {
	                PreparedStatement ps = con.prepareStatement("UPDATE BOOKS SET TITLE=?, AUTHOR=?, PUBLISHER=?, EDITION=?, PRICE=? WHERE ACC_NO=?");
	                ps.setString(1, title);
	                ps.setString(2, author);
	                ps.setString(3, publisher);
	                ps.setString(4, edition);
	                ps.setString(5, price);
	                ps.setString(6, accno);
	                ps.executeUpdate();
	                response.getWriter().println("Book updated successfully.");
	            } else if (action.equals("Delete")) {
	                PreparedStatement ps = con.prepareStatement("DELETE FROM BOOKS WHERE ACC_NO=?");
	                ps.setString(1, accno);
	                ps.executeUpdate();
	                response.getWriter().println("Book deleted successfully.");
	            } else if (action.equals("Display All")) {
	                Statement stmt = con.createStatement();
	                ResultSet rs = stmt.executeQuery("SELECT * FROM BOOKS");
	                PrintWriter out = response.getWriter();
	                out.println("<h2>Book Information:</h2>");

	             // Start the table
	             out.println("<table border='1' cellpadding='5' cellspacing='0'>");
	             out.println("<tr>"
	                 + "<th>Account No</th>"
	                 + "<th>Title</th>"
	                 + "<th>Author</th>"
	                 + "<th>Publisher</th>"
	                 + "<th>Edition</th>"
	                 + "<th>Price</th>"
	                 + "</tr>");

	             // Iterate over the ResultSet and populate the table rows
	             while (rs.next()) {
	                 out.println("<tr>");
	                 out.println("<td>" + rs.getString("ACC_NO") + "</td>");
	                 out.println("<td>" + rs.getString("TITLE") + "</td>");
	                 out.println("<td>" + rs.getString("AUTHOR") + "</td>");
	                 out.println("<td>" + rs.getString("PUBLISHER") + "</td>");
	                 out.println("<td>" + rs.getString("EDITION") + "</td>");
	                 out.println("<td>" + rs.getString("PRICE") + "</td>");
	                 out.println("</tr>");
	             }

	             // End the table
	             out.println("</table>");

	            }
	            con.close();
	        } catch (ClassNotFoundException e) {
	           e.printStackTrace();
	           response.getWriter().println("Error: "+e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("Error: " + e.getMessage());
	        }
	}

}
