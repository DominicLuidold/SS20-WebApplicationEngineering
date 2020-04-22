/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc.broker;

import at.fhv.dlu9576.guestbook.bean.RatingBean;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RatingBroker extends BrokerBase<RatingBean> {

    @Override
    public boolean checkIfExists(RatingBean item) {
        throw new NotImplementedException();
    }

    @Override
    public void save(RatingBean item) {
        try (Connection c = _connection) {
            PreparedStatement stmt = c.prepareStatement("INSERT INTO dlu9576_rating(userID, mediaType, mediaElement, rating, child, teen, adult, comment) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, item.getUserID());
            stmt.setString(2, item.getMediaType());
            stmt.setString(3, item.getMediaElement());
            stmt.setString(4, item.getRating());
            stmt.setBoolean(5, item.isForChild());
            stmt.setBoolean(6, item.isForTeen());
            stmt.setBoolean(7, item.isForAdult());
            stmt.setString(8, item.getComment());
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<RatingBean> getAll() {
        throw new NotImplementedException();
    }
}
