

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Ajax
 */
@WebServlet("/Ajax")
public class Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	private static final String[] STUDENT_NAMES = {
			"Alice Johnson", "Bob Smith", "Charlie Williams", "David Brown", "Edward Clark",
		    "Fiona Green", "George White", "Helen Black", "Irene Scott", "Jack King",
		    "Kara Lee", "Liam Wright", "Mia Harris", "Nathan Young", "Olivia Adams",
		    "Penny Turner", "Quincy Taylor", "Rachel Collins", "Sam Thompson", "Tina Walker",
		    "Uma Richards", "Victor Hall", "Wendy Cooper", "Xander Phillips", "Yara Wood",
		    "Zack Daniels", "Aaron Miles", "Bella Watson", "Carter Hill", "Daisy Evans",
		    "Ethan Morris", "Faith Brooks", "Gabriel Bell", "Hannah Gray", "Isaac Hughes",
		    "Julia Ward", "Kevin Foster", "Laura Fox", "Michael Knight", "Nina Perry",
		    "Oscar Reed", "Paula Bryant", "Quinn Lewis", "Ryan Peterson", "Sophie Barnes",
		    "Tyler Morgan", "Ursula Chambers", "Vincent Kelley", "Willow Foster", "Xenia Russell",
		    "Yvonne Parker", "Zoe Sanders", "Amelia Mitchell", "Benjamin Cole", "Charlotte Russell",
		    "Daniel James", "Elijah Patterson", "Grace Bennett", "Harper Lee", "Ivy Edwards",
		    "Jason Carter", "Kimberly Gomez", "Logan Simmons", "Mason Torres", "Noah Richardson",
		    "Olivia Nelson", "Parker Ward", "Quentin Morales", "Riley Jenkins", "Sara Bailey",
		    "Travis Ross", "Ulysses Powell", "Violet Scott", "Wyatt Bell", "Ximena Mitchell",
		    "Yusuf Anderson", "Zane Hughes", "Amira Edwards", "Brandon Flores", "Chloe Hall",
		    "Dylan Martin", "Ella Perez", "Felix Wright", "Gianna Cooper", "Henry Parker",
		    "Isabella Morgan", "Jackson Campbell", "Kayla Powell", "Leo Rivera", "Mila Flores",
		    "Noelle Ward", "Owen Hughes", "Phoebe Bennett", "Quinton Bell", "Reese Sanders",
		    "Scarlett Brooks", "Tucker Edwards", "Ulyana Morris", "Vivian Howard", "Xavier Martinez",
		    "Yara Henderson", "Zion Reed"
	    };
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 // Get the input query (what the user has typed)
        String query = request.getParameter("query").toLowerCase();

        // Set response type as text/html
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Generate the list of suggestions
        for (String student : STUDENT_NAMES) {
            if (student.toLowerCase().startsWith(query)) {
                out.println("<div onclick=\"setStudentName('" + student + "')\">" + student + "</div>");
            }
        }

        out.close();

	}

}
