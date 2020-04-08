/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.guestbook.bean;

public class LoginBean {
    private String _userID;
    private boolean _validLogin;

    public String getUserID() {
        return _userID;
    }

    public void setUserID(String userID) {
        _userID = userID;
    }

    public boolean isValidLogin() {
        return _validLogin;
    }

    public void setValidLogin(boolean validLogin) {
        _validLogin = validLogin;
    }
}
