/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCManager {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://w0182181.kasserver.com:3306/d031a965", "d031a965", "PZQPq9YU2aqQ52oZ");
    }
}
