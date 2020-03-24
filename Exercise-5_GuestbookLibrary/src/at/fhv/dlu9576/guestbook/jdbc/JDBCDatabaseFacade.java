/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc;

import at.fhv.dlu9576.guestbook.jdbc.broker.GuestbookEntryBroker;
import at.fhv.dlu9576.guestbook.jdbc.model.GuestbookEntry;

import java.util.List;

public class JDBCDatabaseFacade {

    public static List<GuestbookEntry> getAllGuestbookEntries() {
        return new GuestbookEntryBroker().getAll();
    }

    public static <T> void save(T item) {
        if (item instanceof GuestbookEntry) {
            new GuestbookEntryBroker().save((GuestbookEntry) item);
        }
    }
}
