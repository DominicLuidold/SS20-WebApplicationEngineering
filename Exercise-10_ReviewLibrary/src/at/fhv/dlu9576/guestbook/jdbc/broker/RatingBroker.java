/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc.broker;

import at.fhv.dlu9576.guestbook.bean.RatingBean;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class RatingBroker extends BrokerBase<RatingBean> {

    @Override
    public boolean checkIfExists(RatingBean item) {
        throw new UnsupportedOperationException();
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
        List<RatingBean> ratings = new LinkedList<>();

        try (Connection c = _connection) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM dlu9576_rating");

            while (rs.next()) {
                RatingBean rating = new RatingBean();
                rating.setUserID(rs.getString("userID"));
                rating.setMediaType(rs.getString("mediaType"));
                rating.setMediaElement(rs.getString("mediaElement"));
                rating.setRating(rs.getString("rating"));
                rating.setForChild(rs.getBoolean("child"));
                rating.setForTeen(rs.getBoolean("teen"));
                rating.setForAdult(rs.getBoolean("adult"));
                rating.setComment(rs.getString("comment"));
                ratings.add(rating);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ratings;
    }
}
