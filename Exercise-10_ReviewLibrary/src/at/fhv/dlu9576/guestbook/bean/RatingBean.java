/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.bean;

import at.fhv.dlu9576.guestbook.jdbc.JDBCDatabaseFacade;
import at.fhv.dlu9576.guestbook.jdbc.model.Audio;
import at.fhv.dlu9576.guestbook.jdbc.model.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

@ManagedBean(name = "rating")
@SessionScoped
public class RatingBean implements Serializable {
    private static final long serialVersionUID = 7794473689376703258L;

    private int _id;
    private String _userID;
    private String _mediaType;
    private String _mediaElement;
    private String _rating;
    private boolean _child;
    private boolean _teen;
    private boolean _adult;
    private String _comment;

    private final List<String> _availableMediaElements = new LinkedList<>();

    // Show rating stuff
    private List<RatingBean> _ratingsFromDB = new LinkedList<>();
    private List<RatingBean> _shownRatings = new LinkedList<>();
    private LinkedList<String> _selectedCriteria = new LinkedList<>();
    private boolean _showRating = false;
    private boolean _showAge = false;
    private boolean _showComment = false;

    public void prepareMediaElements() {
        _availableMediaElements.clear();
        switch (_mediaType) {
            case "Books":
                for (Book book : JDBCDatabaseFacade.getAllBooks()) {
                    _availableMediaElements.add(book.getTitle());
                }
                break;
            case "Audio":
                for (Audio audio : JDBCDatabaseFacade.getAllAudio()) {
                    _availableMediaElements.add(audio.getTitle());
                }
                break;
        }
    }

    public void save() {
        JDBCDatabaseFacade.save(this);

        // Reset bean
        _userID = null;
        _mediaType = null;
        _mediaElement = null;
        _rating = null;
        _child = false;
        _teen = false;
        _adult = false;
        _comment = null;
    }

    public void listenToCriteriaChanges(AjaxBehaviorEvent event) {
        _ratingsFromDB.clear();
        _shownRatings.clear();

        _ratingsFromDB.addAll(JDBCDatabaseFacade.getAllRatings());
        for (RatingBean rating : _ratingsFromDB) {
            if (rating.getMediaElement().equals("Corpus Delicti")) {
                _shownRatings.add(rating);
            }
        }

        _showRating = _selectedCriteria.contains("rating");
        _showAge = _selectedCriteria.contains("age");
        _showComment = _selectedCriteria.contains("comment");
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        _id = id;
    }

    public String getUserID() {
        return _userID;
    }

    public void setUserID(String username) {
        _userID = username;
    }

    public String getMediaType() {
        return _mediaType;
    }

    public void setMediaType(String mediaType) {
        _mediaType = mediaType;
    }

    public String getMediaElement() {
        return _mediaElement;
    }

    public void setMediaElement(String mediaElement) {
        _mediaElement = mediaElement;
    }

    public String getRating() {
        return _rating;
    }

    public void setRating(String rating) {
        _rating = rating;
    }

    public boolean isForChild() {
        return _child;
    }

    public void setForChild(boolean isForChild) {
        _child = isForChild;
    }

    public boolean isForTeen() {
        return _teen;
    }

    public void setForTeen(boolean isForTeen) {
        _teen = isForTeen;
    }

    public boolean isForAdult() {
        return _adult;
    }

    public void setForAdult(boolean isForAdult) {
        _adult = isForAdult;
    }

    public String getComment() {
        return _comment;
    }

    public void setComment(String comment) {
        _comment = comment;
    }

    public List<String> getAvailableMediaElements() {
        return _availableMediaElements;
    }

    public List<String> getSelectedCriteria() {
        return _selectedCriteria;
    }

    public void setSelectedCriteria(LinkedList<String> selectedCriteria) {
        _selectedCriteria = selectedCriteria;
    }

    public List<RatingBean> getShownRatings() {
        return _shownRatings;
    }

    public boolean getShowRating() {
        return _showRating;
    }

    public boolean getShowAge() {
        return _showAge;
    }

    public boolean getShowComment() {
        return _showComment;
    }
}
