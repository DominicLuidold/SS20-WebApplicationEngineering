/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc.model;

public class Book {
    private String _id;
    private String _title;
    private String _author;
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

    public String getAuthor() {
        return _author;
    }

    public void setAuthor(String author) {
        _author = author;
    }

    public String getLentThrough() {
        return _lentThrough;
    }

    public void setLentThrough(String lentThrough) {
        _lentThrough = lentThrough;
    }
}
