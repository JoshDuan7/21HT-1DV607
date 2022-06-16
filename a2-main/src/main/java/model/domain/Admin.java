package model.domain;

/**
 * Admin class to realize a simple login function.
 */
public class Admin {

  private boolean loggedIn = false;

  /**
   * Admin constructor.
   */
  public Admin() {
  }

  /**
   * Check if user login.
   *
   * @return True or false.
   */
  protected boolean isLoggedIn() {
    return loggedIn;
  }

  /**
   * Login method, when username and password are corrected, return login status is true.
   *
   * @param username the login user name
   * @param password the login user password
   */
  public void logIn(String username, String password) {
    String pass = "admin";
    String user = "admin";
    if (username.equalsIgnoreCase(user) && password.equalsIgnoreCase(pass)) {
      loggedIn = true;
    }
  }

  /**
   * When logout, return login status is false.
   */
  public void logOut() {
    loggedIn = false;
  }
}