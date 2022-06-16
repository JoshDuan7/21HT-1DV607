package model.domain;

import java.util.ArrayList;
import java.util.List;

/** Member class is used to describe a member's information. */
public class Member extends ClubEntity {
  private String firstName;
  private String lastName;
  private String personNum;
  private List<Boat> boats;

  /**
   *  Default constructor for creating a new member object.
   */
  public Member(){
  }

  /**
   * Creates a new member object.
   *
   * @param id The member's ID.
   * @param firstName The member's first name.
   * @param lastName The member's second name.
   * @param personNum The member's personal number.
   * @param boats The member's boats.
   */
  public Member(
      int id, String firstName, String lastName, String personNum, List<Boat> boats) {
    super(id);
    this.firstName = firstName;
    this.lastName = lastName;
    this.personNum = personNum;
    this.boats = boats;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getPersonNum() {
    return personNum;
  }

  public List<Boat> getBoats() {
    return boats;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setPersonNum(String personNum) {
    this.personNum = personNum;
  }

  public void setBoats(List<Boat> boats) {
    this.boats = boats;
  }
}
