/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc.broker;

import at.fhv.dlu9576.guestbook.jdbc.model.Audio;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class AudioBroker extends BrokerBase<Audio> {

    @Override
    public boolean checkIfExists(Audio item) {
        throw new NotImplementedException();
    }

    @Override
    public void save(Audio item) {
        throw new NotImplementedException();
    }

    @Override
    public List<Audio> getAll() {
        List<Audio> audios = new LinkedList<>();

        try (Connection c = _connection) {
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM dlu9576_audio");

            while (rs.next()) {
                Audio audio = new Audio();
                audio.setId(rs.getString("index"));
                audio.setTitle(rs.getString("title"));
                audio.setLength(rs.getString("length"));
                audio.setLentThrough(rs.getString("lentThrough"));
                audios.add(audio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return audios;
    }
}