/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.servlet;

import at.fhv.dlu9576.guestbook.bean.LoginBean;
import at.fhv.dlu9576.guestbook.jdbc.JDBCDatabaseFacade;
import at.fhv.dlu9576.guestbook.jdbc.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(urlPatterns = {"/registrationHandler"})
public class RegistrationHandler extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* Create new user */
        User user = new User();
        user.setFirstName(req.getParameter("firstName"));
        user.setLastName(req.getParameter("lastName"));
        user.setPassword(req.getParameter("password"));
        user.setId(req.getParameter("userID"));

        String[] role = req.getParameterValues("role");
        user.setChild(Arrays.asList(role).contains("child"));
        user.setAdult(Arrays.asList(role).contains("adult"));
        user.setEmployee(Arrays.asList(role).contains("employee"));
        user.setAdmin(Arrays.asList(role).contains("admin"));

        HttpSession session = req.getSession();
        if (JDBCDatabaseFacade.checkIfExists(user)) {
            /* Redirect to account page */
            session.setAttribute("registrationFailed", true);
            resp.sendRedirect("account.jsp");
        } else {
            /* Save entry to database */
            JDBCDatabaseFacade.save(user);

            /* Create Login bean */
            LoginBean login = new LoginBean();
            login.setUserID(req.getParameter("userID"));
            login.setValidLogin(true);

            /* Set session and redirect to welcome page */
            session.setAttribute("login", login);
            resp.sendRedirect("welcome.jsp");
        }
    }
}
