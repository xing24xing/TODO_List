import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String name = request.getParameter("fullname");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String pass = request.getParameter("password");

        try (Connection con = DatabaseUtil.getConnection()) {
            PreparedStatement pst = con.prepareStatement("INSERT INTO users(fullname, email, username, password) VALUES (?, ?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, email);
            pst.setString(3, username);
            pst.setString(4, pass);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                out.println("Record added successfully!");
            } else {
                out.println("Failed to add record!");
            }
        } catch (SQLException e) {
            out.println("Error: " + e.getMessage());
        } finally {
            out.close();
        }
    }
}
