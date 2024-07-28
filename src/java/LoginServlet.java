import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            con = DatabaseUtil.getConnection();
            pst = con.prepareStatement("SELECT id, fullname FROM users WHERE username=? AND password=?");
            pst.setString(1, username);
            pst.setString(2, password);

            rs = pst.executeQuery();
            if (rs.next()) {
                // Valid login
                int userId = rs.getInt("id");
                String fullname = rs.getString("fullname");

                // Set session attributes
                request.getSession().setAttribute("userId", userId);
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("fullname", fullname);

                // Redirect to todo.jsp or any other authenticated page
                response.sendRedirect("todo.jsp");
            } else {
                // Invalid login
                request.setAttribute("loginError", "Invalid username or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            out.println("Error: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                out.println("Error closing resources: " + e.getMessage());
            }
            out.close();
        }
    }
}
