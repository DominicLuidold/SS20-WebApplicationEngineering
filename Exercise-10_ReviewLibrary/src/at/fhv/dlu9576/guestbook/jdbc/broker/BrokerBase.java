/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc.broker;

import at.fhv.dlu9576.guestbook.jdbc.JDBCManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BrokerBase<T> {
    JDBCManager _manager;
    Connection _connection;

    public BrokerBase() {
        _manager = new JDBCManager();
        try {
            _connection = _manager.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public abstract boolean checkIfExists(T item);

    public abstract void save(T item);

    public abstract List<T> getAll();
}
