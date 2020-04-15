/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.bean;

import java.time.LocalDateTime;

public class HistoryBean {
    private String _title;
    private String _url;
    private LocalDateTime _timeVisited;

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }

    public LocalDateTime getTimeVisited() {
        return _timeVisited;
    }

    public void setTimeVisited(LocalDateTime timeVisited) {
        _timeVisited = timeVisited;
    }
}
