package view;

import model.domain.BoatType;

/** Methods that will be used for Member and Boat related stuff on the UI. */
public interface MemberBoatUi {

  // Member related getters regarding input

  int getMemberId();

  String getFirstName();

  String getLastName();

  String getPersonNum();

  String getName();

  int getBoatId();

  double getLength();

  BoatType getType();

  String getUsername();

  String getPassword();

  MemberOption getMemberOptions();
}
