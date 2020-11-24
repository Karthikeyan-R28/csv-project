

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.JOptionPane;

import com.mysql.jdbc.UpdatableResultSet;



/**
 * Servlet implementation class UploadCheck
 */
@WebServlet("/CheckUpload")
@MultipartConfig()
public class CheckUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		try{
			Part fileName = request.getPart("fileName");
			InputStream inputStream = null;
			if(fileName!=null) {
				inputStream = fileName.getInputStream();
			}
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/grootan","root","karthikn@27");
			PreparedStatement st = con.prepareStatement("insert into file(name) values(?);");
			st.setBlob(1, inputStream);
			int update = st.executeUpdate();
			if(update == 0) {
				JOptionPane.showMessageDialog(null, "Error in uploading file");
				response.sendRedirect("Upload.html");
			}
			else {
				JOptionPane.showMessageDialog(null, "Uploaded Successfully");
				response.sendRedirect("Upload.html");
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}

}
