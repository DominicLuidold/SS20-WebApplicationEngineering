/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.servlet;

import at.fhv.dlu9576.guestbook.jdbc.JDBCDatabaseFacade;
import at.fhv.dlu9576.guestbook.jdbc.model.GuestbookEntry;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/guestbook"})
public class Guestbook extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        /* HTML header */
        out.println("<html><head><meta charset='UTF-8'>");
        out.println("<title>Guestbook</title>");
        out.println("</head>");

        /* HTML body */
        out.println("<body>");
        out.println("<a href='../'>Zurück zur Übersicht</a>");

        /* Form */
        out.println("<h1>Guestbook</h1><h3>Create a new entry</h3>");
        out.println("<form method='post' action='guestbook'>\n" +
                "Name: <input type='text' name='name' placeholder='Name' required><br>\n" +
                "E-Mail: <input type='email' name='email' placeholder='E-Mail' required><br>\n" +
                "Message: <textarea rows='3' name='message' placeholder='Message' required></textarea><br>\n" +
                "<button type='submit'>Submit</button>\n" +
                "</form>");
        out.println("<hr>");

        /* Previous guestbook entries */
        out.println("<h3>Previous entries</h3>");
        for (GuestbookEntry entry : JDBCDatabaseFacade.getAllGuestbookEntries()) {
            out.println("<div style='border-style: dotted; margin-bottom: 1rem;'>");
            out.println("<p>" + entry.getName() + "</p>");
            out.println("<p>" + entry.getEmail() + "</p>");
            out.println("<p>" + entry.getMessage() + "</p>");
            out.println("</div>");
        }
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        /* Create new guestbook entry */
        GuestbookEntry entry = new GuestbookEntry();
        entry.setName(req.getParameter("name"));
        entry.setEmail(req.getParameter("email"));
        entry.setMessage(req.getParameter("message"));

        /* Save entry to database */
        JDBCDatabaseFacade.save(entry);

        // Show HTML content of GET after save
        doGet(req, resp);
    }
}
