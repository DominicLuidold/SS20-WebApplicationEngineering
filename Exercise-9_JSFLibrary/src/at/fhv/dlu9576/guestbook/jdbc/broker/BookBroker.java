/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc.broker;

import at.fhv.dlu9576.guestbook.jdbc.model.Book;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class BookBroker extends BrokerBase<Book> {

    @Override
    public boolean checkIfExists(Book item) {
        throw new NotImplementedException();
    }

    @Override
    public void save(Book item) {
        throw new NotImplementedException();
    }

    @Override
    public List<Book> getAll() {
        List<Book> books = new LinkedList<>();

        try (Connection c = _connection) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM dlu9576_book");

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getString("index"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setLentThrough(rs.getString("lentThrough"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
}
