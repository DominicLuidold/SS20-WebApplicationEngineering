/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc.broker;

import at.fhv.dlu9576.guestbook.jdbc.model.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.List;

public class UserBroker extends BrokerBase<User> {

    public boolean login(String userId, String password) {
        try (Connection c = _connection) {
            PreparedStatement stmt = c.prepareStatement("SELECT COUNT(id) AS result FROM user WHERE id = ? AND password = ?");
            stmt.setString(1, userId);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            return rs.getInt("result") > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean checkIfExists(User item) {
        try (Connection c = _connection) {
            PreparedStatement stmt = c.prepareStatement("SELECT COUNT(id) AS result FROM user WHERE id = ?");
            stmt.setString(1, item.getId());
            ResultSet rs = stmt.executeQuery();

            rs.next();
            return rs.getInt("result") > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void save(User item) {
        try (Connection c = _connection) {
            PreparedStatement stmt = c.prepareStatement("INSERT INTO user(firstname, lastname, password, id, minor, adult, admin, worker) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, item.getFirstName());
            stmt.setString(2, item.getLastName());
            stmt.setString(3, item.getPassword());
            stmt.setString(4, item.getId());
            stmt.setBoolean(5, item.isChild());
            stmt.setBoolean(6, item.isAdult());
            stmt.setBoolean(7, item.isAdmin());
            stmt.setBoolean(8, item.isEmployee());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAll() {
        throw new NotImplementedException();
    }
}
