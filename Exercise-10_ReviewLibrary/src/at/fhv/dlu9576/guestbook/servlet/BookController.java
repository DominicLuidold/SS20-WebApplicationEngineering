/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.servlet;

import at.fhv.dlu9576.guestbook.jdbc.JDBCDatabaseFacade;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/media/books"})
public class BookController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache");

        req.setAttribute("books", JDBCDatabaseFacade.getAllBooks());
        req.getRequestDispatcher("books.jsp").forward(req, resp);
    }
}
