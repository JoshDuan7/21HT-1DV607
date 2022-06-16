package controller;

import java.util.ArrayList;
import model.domain.Admin;
import model.domain.Boat;
import model.domain.BoatType;
import model.domain.Member;
import model.domain.Registry;
import view.MemberBoatUi;
import view.MemberOption;

/**
 * Controls the View and the Model and manages communication between the two.
 */
public class RegistryController {
  private final Admin ad;
  private final MemberBoatUi ui;
  private final Registry reg;
  private Member curMember;
  private Boat curBoat;

  // TODO: The RegistryController should CREATE and START the Registry and UI.

  /**
   * Creates a new controller using a new register and console.
   *
   * @param reg      Registry where Member and Boat info will be stored in.
   * @param memberUi Interface which the user (secretary) will interact with console through it.
   */
  public RegistryController(Admin admin, Registry reg, MemberBoatUi memberUi) {
    this.ad = admin;
    this.ui = memberUi;
    this.reg = reg;
    curMember = null;
    curBoat = null;
  }

  /**
   * Adds, edits and removes members in the registry.
   */
  public void handleMembers() {

    close: {
      while (true) {
        while (true) {
          MemberOption choice = ui.getMemberOptions();

          switch (choice) {
            case AddNewMember:
              addNewMember();
              break;
            case DeleteMember:
              deleteCurMember();
              break;
            case UpdateMember:
              updateMember();
              break;
            case PickMember:
              setCurMember();
              break;
            case AddBoat:
              addBoat();
              break;
            case UpdateBoat:
              updateBoat();
              break;
            case DeleteBoat:
              deleteBoat();
              break;
            case AdminLogin:
              logIn();
              break;
            case AdminLogout:
              logOut();
              break;
            case Quit:
              if (reg.getJsonFlag()) {
                reg.getJsonPersistence().save();
              } else {
                reg.getTxtPersistence().save();
              }
              break close;
            default:
              break;
          }
        }
      }
    }
  }

  /** This method will take the input from view and log in if possible.*/
  private void logIn() {
    ad.logIn(ui.getUsername(), ui.getPassword());
    System.out.println("Username: " + ui.getUsername());
    System.out.println("Password: " + ui.getPassword());

  }

  /** This method will take the input from view and log out if possible.*/
  private void logOut() {
    ad.logOut();
  }


  /**
   * This method will search in registry and return whether the member was found.
   */

  private void setCurMember() {
    int memberId = ui.getMemberId();
    for (Member mem : reg.getMembers()) {
      if (mem.getId() == memberId) {
        curMember = mem;
      }
    }
  }

  /**
   * This method will search the target boat in registry and return whether the boat was found.
   */
  private void setCurBoat() {
    int boatId = ui.getBoatId();
    for (Boat boat : curMember.getBoats()) {
      if (boat.getId() == boatId) {
        curBoat = boat;
      }
    }
  }

  private void clearCurMember() {
    curMember = null;
  }

  private void clearCurBoat() {
    curBoat = null;
  }

  /**
   * This method is used for adding a new member in the registry.
   */
  private void addNewMember() {
    reg.addNewMember(ui.getFirstName(), ui.getLastName(), ui.getPersonNum(), new ArrayList<>());
  }

  /**
   * This method is used for adding a boat according to member's id.
   */
  private void addBoat() {
    setCurMember();
    reg.addNewBoat(curMember, ui.getName(), ui.getType(), ui.getLength());
    clearCurMember();
  }

  /**
   * This method is used for deleting a current member.
   */
  private void deleteCurMember() {
    setCurMember();
    reg.deleteMember(curMember);
    clearCurMember();
  }

  /**
   * This method is used for deleting a current boat according the member.
   */
  private void deleteBoat() {
    setCurMember();
    setCurBoat();

    reg.removeBoat(curMember, curBoat);

    clearCurMember();
    clearCurBoat();
  }

  /**
   * Used to edit the currently selected member's information.
   */
  private void updateMember() {
    setCurMember();

    String memName = ui.getFirstName();
    String memLastName = ui.getLastName();
    String memPersonNum = ui.getPersonNum();

    if (memName.equalsIgnoreCase("")) {
      memName = curMember.getFirstName();
    }

    if (memLastName.equalsIgnoreCase("")) {
      memLastName = curMember.getLastName();
    }

    if (memPersonNum.equalsIgnoreCase("")) {
      memPersonNum = curMember.getPersonNum();
    }

    reg.updateMember(curMember, memName, memLastName, memPersonNum);

    clearCurMember();
  }

  /**
   * Used to edit the currently selected boat's information.
   */
  private void updateBoat() {
    setCurMember();
    setCurBoat();

    String boatName = ui.getName();
    BoatType boatType = ui.getType();
    int boatLength = (int) ui.getLength();

    if (boatName.equals("")) {
      boatName = curBoat.getName();
    }
    if (boatType == null) {
      boatType = curBoat.getType();
    }
    if (boatLength == 0) {
      boatLength = (int) (curBoat.getLength());
    }
    reg.updateBoat(curMember, curBoat, boatName, boatType, boatLength);
    clearCurMember();
    clearCurBoat();
  }
}
