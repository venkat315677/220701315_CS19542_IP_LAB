import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form parameters
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // Basic validation
        String errorMessage = null;
        if (username == null || username.trim().isEmpty()) {
            errorMessage = "Username is required.";
        } else if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            errorMessage = "Invalid email format.";
        } else if (password == null || password.length() < 6) {
            errorMessage = "Password must be at least 6 characters long.";
        }
        
        if (errorMessage != null) {
            response.sendRedirect("formValidate.html?error=" + java.net.URLEncoder.encode(errorMessage, "UTF-8"));
        }
        else {
            response.getWriter().println("Registration successful!");
        }
    }
}
