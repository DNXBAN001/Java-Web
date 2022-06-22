import database.DatabaseHandle;
import model.Person;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CapturePersonServlet
 */
/**
 * @author barne
 *
 */
public class CapturePersonServlet extends HttpServlet {

	protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Person personObj = new Person(request.getParameter("firstName"), request.getParameter("lastName"),
				request.getParameter("idNo"), processDate(request.getParameter("dateOfBirth")));

		System.out.println("Date unprocessed: " + request.getParameter("dateOfBirth"));
		System.out.println("Date processed: " + processDate(request.getParameter("dateOfBirth")));
		try {

			DatabaseHandle dbObj = DatabaseHandle.getInstance();
			dbObj.Insert(personObj);

			ResultSet resultSet = dbObj.Select("select * from person where id_no = " + personObj.getId());
			if (resultSet.next())
				response.getWriter().println(resultSet.getString("first_name") + " " + resultSet.getString("last_name")
						+ " " + resultSet.getString("id_no") + " " + resultSet.getString("date_of_birth"));

		} catch (ClassNotFoundException | SQLException e) {

			response.getWriter().println("Something's wrong happened...");
		}

	}

	/**
	 * This method takes the date passed from the input field which is in the format yyyy-mm-dd
	 * and formats it to dd/mm/yyyy before it can be captured to the database
	 * @param str
	 * @return formatted date string
	 */
	public String processDate(String str) {

		String[] strArr = str.split("-");
		return strArr[2] + "/" + strArr[1] + "/" + strArr[0];
	}
	
	/**
	 * This method checks whether or not the id no passed is indeed numeric and has 13 digits
	 * @param id
	 * @return true if id is numeric
	 */
	public boolean isNumeric(String id) {
  
		return id.matches("-?\\d+(\\.\\d+)?")&&id.length()==13;
	}
	
	/**
	 * This method goes throguh the database and checks if the id that was just punched on the input field
	 * already exists or not
	 * @param id
	 * @return true if the id already exists in the database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean isExist(String id) throws ClassNotFoundException, SQLException {
		
		ResultSet resultSet = DatabaseHandle.getInstance().Select("select id_no from person where id_no="+ id);
		return resultSet != null;
	}
	
	public void populateInputField(HttpServletResponse response, String id) {
		
		if(isExist(id))
			
	}
	 

}






