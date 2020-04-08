/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.jdbc.model;

public class User {
    private String _id;
    private String _firstName;
    private String _lastName;
    private String _password;
    private boolean _child;
    private boolean _adult;
    private boolean _admin;
    private boolean _employee;

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        _id = id;
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String firstName) {
        _firstName = firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String lastName) {
        _lastName = lastName;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    public boolean isChild() {
        return _child;
    }

    public void setChild(boolean isChild) {
        _child = isChild;
    }

    public boolean isAdult() {
        return _adult;
    }

    public void setAdult(boolean isAdult) {
        _adult = isAdult;
    }

    public boolean isAdmin() {
        return _admin;
    }

    public void setAdmin(boolean isAdmin) {
        _admin = isAdmin;
    }

    public boolean isEmployee() {
        return _employee;
    }

    public void setEmployee(boolean isEmployee) {
        _employee = isEmployee;
    }
}
