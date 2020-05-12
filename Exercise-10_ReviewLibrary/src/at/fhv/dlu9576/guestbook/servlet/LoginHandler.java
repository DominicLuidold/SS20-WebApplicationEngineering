/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.servlet;

import at.fhv.dlu9576.guestbook.bean.LoginBean;
import at.fhv.dlu9576.guestbook.jdbc.JDBCDatabaseFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/loginHandler"})
public class LoginHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        LoginBean login = new LoginBean();
        if (JDBCDatabaseFacade.loginUser(req.getParameter("userID"), req.getParameter("password"))) {
            login.setUserID(req.getParameter("userID"));
            login.setValidLogin(true);

            /* Set session and redirect to start page */
            session.setAttribute("login", login);
            resp.sendRedirect("home.html");
        } else {
            /* Set session and redirect to login page */
            login.setValidLogin(false);
            session.setAttribute("login", login);
            resp.sendRedirect("login.jsp");
        }
    }
}
