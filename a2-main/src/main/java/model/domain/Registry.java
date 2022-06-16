package model.domain;

import java.util.ArrayList;
import java.util.List;
import model.persistence.JsonPersistence;
import model.persistence.TxtPersistence;

/** Registry used to store club related information, such as members and their boats. */
public class Registry {
  private final Admin admin;
  private List<Member> members;
  private Helper helper;
  private boolean jsonFlag = false;


  //Json variables to test out
  private JsonPersistence jsonPersistence;

  private TxtPersistence txtPersistence;

  /** Creates a new registry. */
  public Registry() {
    admin = new Admin();
    jsonPersistence = new JsonPersistence(this);
    txtPersistence = new TxtPersistence(this);

    // Creating the arraylist for boats owned by the member
    members = new ArrayList<>();

    // load registry data from json file, if you want txt -- comment out jsonPersistence
    //members = txtPersistence.load();
    members = jsonPersistence.load();
    helper = new Helper();
  }

  /**
   * Adds a new member to the registry only if admin is logged in.
   *
   * @param firstName Member's first name.
   * @param lastName Member's last name.
   * @param personNum Member's personal number.
   * @param ownerBoatList List of Boats owned by the member.
   */
  public void addNewMember(
          String firstName, String lastName, String personNum, List<Boat> ownerBoatList) {
    if (admin.isLoggedIn()) {
      Member m =
              new Member(helper.generateId(members), firstName, lastName, personNum, ownerBoatList);
      members.add(m);
    }
  }

  /**
   * Adds a new member to the registry only if admin is logged in.
   *
   * @param firstName Member's first name.
   * @param lastName Member's last name.
   * @param personNum Member's personal number.
   * @param ownerBoatList List of Boats owned by the member.
   */
  public void addNewMember(
          int id, String firstName, String lastName, String personNum, List<Boat> ownerBoatList) {
    if (admin.isLoggedIn()) {
      Member m = new Member(id, firstName, lastName, personNum, ownerBoatList);
      members.add(m);
    }
  }


  /**
   * Adds a new boat to the registry (with custom id).
   *
   * @param member The member who owns the boat.
   * @param name Name of the boat.
   * @param type Type of the boat.
   * @param length Length of the boat.
   */
  public void addNewBoat(Member member, String name, BoatType type, double length) {
    if (admin.isLoggedIn()) {
      Boat b = new Boat(name, helper.generateId(member.getBoats()), type, length);
      member.getBoats().add(b);
    }
  }

  /**
   * Adds a new boat to the registry (with custom id).
   *
   * @param member The member who owns the boat.
   * @param name Name of the boat.
   * @param type Type of the boat.
   * @param length Length of the boat.
   */
  public void addNewBoat(Member member, String name, int id, BoatType type, double length) {
    if (admin.isLoggedIn()) {
      Boat b = new Boat(name, id, type, length);
      member.getBoats().add(b);
    }
  }

  /**
   * Updates a boat's information.
   *
   * @param member The member who owns the boat.
   * @param oldBoat The boat that will be edited.
   * @param name Name of the boat.
   * @param type Type of the boat.
   * @param length Length of the boat.
   */
  public void updateBoat(Member member, Boat oldBoat, String name, BoatType type, double length) {
    if (admin.isLoggedIn()) {
      Boat updatedBoat = new Boat(name, oldBoat.getId(), type, length);
      removeBoat(member, oldBoat);
      addNewBoat(
              member,
              updatedBoat.getName(),
              updatedBoat.getId(),
              updatedBoat.getType(),
              updatedBoat.getLength());
    }
  }

  /**
   * Updates a member's information in the registry.
   *
   * @param oldMember The member whose information will be changed.
   * @param firstName The member's first name.
   * @param lastName The member's last name.
   * @param personNum The member's personal number.
   */
  public void updateMember(Member oldMember, String firstName, String lastName, String personNum) {
    if (admin.isLoggedIn()) {
      Member updatedMember =
              new Member(oldMember.getId(), firstName, lastName, personNum, oldMember.getBoats());
      deleteMember(oldMember);
      addNewMember(
              updatedMember.getId(),
              updatedMember.getFirstName(),
              updatedMember.getLastName(),
              updatedMember.getPersonNum(),
              updatedMember.getBoats());
    }
  }

  /**
   * Deletes a member from registry.
   *
   * @param deleteMember The target member object for delete.
   */
  public void deleteMember(Member deleteMember) {
    if (admin.isLoggedIn()) {
      members.removeIf(
              curMember ->
                      curMember.getFirstName().equalsIgnoreCase(deleteMember.getFirstName())
                              && curMember.getLastName().equalsIgnoreCase(deleteMember.getLastName()));
    }
  }

  /**
   * Remove a boat from the member's registry.
   *
   * @param m The target member object regarding the boat remove operation.
   * @param boat The boat object for removing.
   */
  public void removeBoat(Member m, Boat boat) {
    if (admin.isLoggedIn()) {
      m.getBoats().removeIf(el -> el.getName().equalsIgnoreCase(boat.getName()));
    }
  }

  public Admin getAdmin() {
    return admin;
  }

  public boolean getLoginStatus() {
    return admin.isLoggedIn();
  }

  public List<Member> getMembers() {
    return members;
  }

  public void setJsonFlag() {
    jsonFlag = true;
  }

  public boolean getJsonFlag() {
    return jsonFlag;
  }

  public JsonPersistence getJsonPersistence() {
    return jsonPersistence;
  }

  public TxtPersistence getTxtPersistence() {
    return txtPersistence;
  }
}
