package mySQL;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ControllerServlet() {
        // TODO Auto-generated constructor stub
    	
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		if (DBHandler.connectToDB()) {
			
			if (request.getParameter("philosophers") != null) {
				
				String[] paramList = request.getParameterValues("philosophers");
				DBHandler.addToTable("philosophers", paramList);
				response.getWriter().println(DBHandler.getDBAsTable("philosophers"));
				
			} else if (request.getParameter("countries") != null) {
				
				String[] paramList = request.getParameterValues("countries");
				DBHandler.addToTable("Countries", paramList);
				response.getWriter().println(DBHandler.getDBAsTable("Countries"));
			} else if (request.getParameter("cities") != null) {
				
				String[] paramList = request.getParameterValues("cities");
				DBHandler.addToTable("Cities", paramList);
				response.getWriter().println(DBHandler.getDBAsTable("Cities"));
			}
			
		}
	}

}
