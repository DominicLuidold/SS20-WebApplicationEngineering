/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc.model;

public class Audio {
    private String _id;
    private String _title;
    private String _length;
    private String _lentThrough;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getLength() {
        return _length;
    }

    public void setLength(String length) {
        _length = length;
    }

    public String getLentThrough() {
        return _lentThrough;
    }

    public void setLentThrough(String lentThrough) {
        _lentThrough = lentThrough;
    }
}
