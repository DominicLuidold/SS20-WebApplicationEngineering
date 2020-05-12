/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc.broker;

import at.fhv.dlu9576.guestbook.jdbc.model.GuestbookEntry;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class GuestbookEntryBroker extends BrokerBase<GuestbookEntry> {

    @Override
    public boolean checkIfExists(GuestbookEntry item) {
        throw new NotImplementedException();
    }

    @Override
    public void save(GuestbookEntry item) {
        try (Connection c = _connection) {
            PreparedStatement stmt = c.prepareStatement("INSERT INTO guestbook(name, mail, message) VALUES(?, ?, ?)");
            stmt.setString(1, item.getName());
            stmt.setString(2, item.getEmail());
            stmt.setString(3, item.getMessage());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GuestbookEntry> getAll() {
        List<GuestbookEntry> entries = new LinkedList<>();

        try (Connection c = _connection) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM guestBook");

            while (rs.next()) {
                GuestbookEntry entry = new GuestbookEntry();
                entry.setName(rs.getString("name"));
                entry.setEmail(rs.getString("mail"));
                entry.setMessage(rs.getString("message"));
                entries.add(entry);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return entries;
    }
}
