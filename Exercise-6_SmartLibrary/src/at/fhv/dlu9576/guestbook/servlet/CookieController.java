package at.fhv.dlu9576.guestbook.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/index.html"})
public class CookieController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /*
         * Prevent caching
         * Source: https://stackoverflow.com/a/1953527
         */
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache");

        /* Handle redirect */
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals("lastVisitedPage")) {
                    resp.sendRedirect(c.getValue());
                }
            }
        } else {
            resp.sendRedirect("home.html");
        }
    }
}
