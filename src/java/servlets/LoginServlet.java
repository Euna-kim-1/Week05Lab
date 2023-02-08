package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author rladm
 */
public class LoginServlet extends HttpServlet {
   
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String logout = request.getParameter("logout");
        if(logout != null){
            session.invalidate();
            request.setAttribute("message", "You have successfully logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
            return;
        }
        String username = (String) session.getAttribute("username");
        if(username != null && !username.equals("")){
            response.sendRedirect("home");
            return;
        }
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

       String username = request.getParameter("username");
       String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (username.equals("") || username == null || password.equals("") || password == null) {
            request.setAttribute("message", "Invalid login");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        } else {
            AccountService as = new AccountService();
            User user = as.login(username, password);

            if (user == null) {
                request.setAttribute("message", "Invalid login");
                request.setAttribute("user", username);
                request.setAttribute("password", password);
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            } else {
                session.setAttribute("username", user.getUsername());
                response.sendRedirect("home");
                return;
            }
        }
    }
}
