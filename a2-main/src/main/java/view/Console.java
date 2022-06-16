package view;

import java.util.List;
import java.util.Scanner;
import model.domain.Boat;
import model.domain.BoatType;
import model.domain.Member;
import model.domain.Registry;
import model.search.FullNameContainsMemberSearch;
import model.search.MemberSearch;

/**
 * This class is used for presenting console-based UI.
 */
public class Console implements MemberBoatUi {
  private final Registry reg;

  // Admin variables
  private String username;
  private String password;

  // Member variables
  private int memberId;
  private String firstName;
  private String lastName;
  private String personNum;
  private MemberOption chosen;

  // Boat variables
  private String name;
  private int boatId;
  private BoatType type;
  private double length;

  // Search variables
  private MemberSearch memberSearch;

  /**
   * Constructor for Console class.
   *
   * @param registry The registry info for console showing.
   */
  public Console(Registry registry) {
    username = password = "";
    this.reg = registry;
    type = null;
    firstName = lastName = personNum = name = "";
    memberId = 0;
    boatId = 0;
    length = 0;
  }

  /**
   * According to the registry shows the verbose list of the member.
   *
   * @param members The members that will be presented.
   */
  private void showVerboseList(List<Member> members) {
    for (Member m : members) {
      showMemberVerbose(m);
      System.out.println("--------------------------------------------\n");
    }
  }

  private void personNumError() {
    System.out.println("Please write numbers only! Redoing the process... \n");
  }

  /**
   * According to the registry shows the compact list of the member.
   *
   * @param members The members that will be presented.
   */
  private void showCompactList(List<Member> members) {
    for (Member ms : members) {
      System.out.println("Member name: " + ms.getFirstName() + " " + ms.getLastName());
      System.out.println("Member ID: " + ms.getId());
      System.out.println("Number of boats owned: " + ms.getBoats().size());
      System.out.println("-----------------------------------------\n");
    }
  }

  /**
   * UI for adding a member.
   */
  private boolean addMemberOption(Registry registry) {
    boolean addMemberFlag = true;
    close: {
      while (addMemberFlag) {
        System.out.print("Input member's first name: ");
        firstName = String.valueOf(getInput());
        if (firstName.equalsIgnoreCase("")) {
          error();
          break;
        }
        System.out.print("Input member's last name: ");
        lastName = String.valueOf(getInput());
        if (lastName.equalsIgnoreCase("")) {
          error();
          break;
        }

        System.out.print("Input member's personal number (12 digit num format): ");
        personNum = String.valueOf(getInput());

        if (isPersonNum(personNum)) {
          for (Member m : registry.getMembers()) {
            if (personNum.equalsIgnoreCase(m.getPersonNum())) {
              System.out.println("Member with same personal num exists. Choose another one! ");
              break close;
            }
          }
          addMemberFlag = false;
        }
      }
    }
    return addMemberFlag;
  }

  private void deleteMemberConfirmation() {
    System.out.println("Member has been deleted.");
  }

  private void addMemberConfirmation() {
    System.out.println("Member has been added. ");
  }

  private void deleteBoatConfirmation() {
    System.out.println("Boat has been deleted.");
  }

  /**
   * UI for adding new boat.
   */
  private boolean addBoatOption(Registry registry) {
    boolean addBoatFlag = true;
    close: {
      while (addBoatFlag) {
        System.out.print("Input boat name: ");
        name = String.valueOf(getInput());
        if (name.equalsIgnoreCase("")) {
          error();
          memberSpecificAction();
        }
        System.out.print("Motorsailer, Kayak or Sailboat? Enter one of the following: ");
        String boatType = String.valueOf(getInput());
        if (boatType.equalsIgnoreCase("Kayak")) {
          type = BoatType.KAYAK;
        } else if (boatType.equalsIgnoreCase("Sailboat")) {
          type = BoatType.SAILBOAT;
        } else if (boatType.equalsIgnoreCase("Motorsailer")) {
          type = BoatType.MOTORSAILER;
        } else {
          error();
          memberSpecificAction();
        }
        System.out.print("Boat length (meters): ");
        String boatL = getInput();
        if (isDouble(boatL)) {
          length = Double.parseDouble(boatL);
          System.out.println("Boat added. \n");
        } else {
          error();
          memberSpecificAction();
        }
        for (Member m : registry.getMembers()) {
          for (Boat b : m.getBoats()) {
            if (b.getName().equalsIgnoreCase(name) || b.getId() == boatId) {
              System.out.println("Member with same personal num exists. Choose another one! ");
              break close;
            }
          }
        }
        addBoatFlag = false;
      }
    }
    return addBoatFlag;
  }

  /**
   * Used to check if input is number.
   */
  private boolean isNumber(String text) {
    try {
      Integer.parseInt(text);
      return true;
    } catch (NumberFormatException exception) {
      return false;
    }
  }

  /**
   * Used to check if input is double.
   */
  private boolean isDouble(String text) {
    try {
      Double.parseDouble(text);
      return true;
    } catch (NumberFormatException exception) {
      return false;
    }
  }

  private boolean isPersonNum(String text) {
    if (text.matches("[0-9]+") && text.length() > 0 && text.length() == 12) {
      return true;
    } else {
      personNumError();
    }
    return false;
  }

  /**
   * UI for updating boat information.
   */
  private boolean updateBoatOption(Registry registry) {
    name = "";
    type = null;
    length = 0;
    close: {
      while (true) {
        boolean boatExists = false;
        System.out.println("Please enter an existing boat id to update boat: ");
        String id = getInput();
        if (isNumber(id)) {
          for (Member m : registry.getMembers()) {
            if (m.getId() == (memberId)) {
              for (Boat b : m.getBoats()) {
                if (b.getId() == (Integer.parseInt(id))) {
                  boatId = Integer.parseInt(id);
                  boatExists = true;
                }
              }
            }
          }
        } else {
          error();
          memberSpecificAction();
          return false;
        }
        if (! boatExists) {
          error();
          memberSpecificAction();
          return false;
        }

        System.out.println("\n1. Update boat name");
        System.out.println("2. Update boat type");
        System.out.println("3. Update boat length");
        System.out.println("4. Go back");

        String updBoat = getInput();

        switch (updBoat) {
          case "1":
            System.out.println("Enter updated boat name: ");
            String n = getInput();
            if (n.equalsIgnoreCase("")) {
              error();
              memberSpecificAction();
              return false;
            }
            name = n;
            break;
          case "2":
            System.out.println("Select between the following --> Kayak, Sailboat, Motorsailer: ");
            String boType = getInput();
            if (boType.equalsIgnoreCase("Kayak")) {
              type = BoatType.KAYAK;
            } else if (boType.equalsIgnoreCase("Sailboat")) {
              type = BoatType.SAILBOAT;
            } else if (boType.equalsIgnoreCase("Motorsailer")) {
              type = BoatType.MOTORSAILER;
            } else {
              error();
              memberSpecificAction();
              return false;
            }
            break;
          case "3":
            System.out.println("Enter updated boat length: ");
            String l = getInput();
            if (isDouble(l)) {
              length = Double.parseDouble(l);
            } else {
              error();
              memberSpecificAction();
              return false;
            }
            break;
          case "4":
            break close;
          default:
        }
        System.out.println("Do you want to continue updating boat? y/n");
        String conti = getInput();
        if (! conti.equalsIgnoreCase("y")) {
          break close;
        }
      }
    }
    memberSpecificAction();
    return true;
  }

  /**
   * UI for deleting boat.
   */
  private boolean deleteBoatOption(Registry registry) {
    boolean boatExists = true;
    System.out.println("Please enter an existing boat id to delete it: ");
    String boiD = String.valueOf(getInput());

    while (boatExists) {
      if (isNumber(boiD)) {
        for (Member m : registry.getMembers()) {
          if (m.getId() == (memberId)) {
            for (Boat b : m.getBoats()) {
              if (b.getId() == (Integer.parseInt(boiD))) {
                boatId = Integer.parseInt(boiD);
                boatExists = false;
              }
            }
          }
        }
      } else {
        error();
        memberSpecificAction();
        return false;
      }
      if (boatExists) {
        error();
        memberSpecificAction();
        return false;
      }
    }
    deleteBoatConfirmation();
    return true;
  }

  /**
   * UI for showing member list choice.
   *
   * @param members The members that will be displayed.
   */
  private void showMemberListChoice(List<Member> members) {
    System.out.println("Press 1 to display compact member list");
    System.out.println("Press 2 to display verbose member list");
    String option = getInput();
    if (option.equals("1")) {
      showCompactList(members);
    } else if (option.equals("2")) {
      showVerboseList(members);
    } else {
      error();
    }
  }

  private void showMemberVerbose(Member m) {

    System.out.println("Member name: " + m.getFirstName() + " " + m.getLastName());
    System.out.println("Member personal number: " + m.getPersonNum());
    System.out.println("Member ID: " + m.getId());

    System.out.println("\n----Boats owned----");
    for (Boat b : m.getBoats()) {
      System.out.println("Boat name: " + b.getName());
      System.out.println("Boat length in meters: " + b.getLength());
      System.out.println("Boat type: " + b.getType());
      System.out.println("Boat ID: " + b.getId());
      System.out.println("\n");
    }
  }

  /**
   * UI for prompting to enter member id.
   */
  private boolean pickMemberOption(Registry registry) {
    boolean memberExistsFlag = true;
    System.out.print("Enter existing member id please: \n");
    memberId = Integer.parseInt(getInput());

    for (Member m : registry.getMembers()) {

      if (memberId == m.getId()) {
        showMemberVerbose(m);
        memberExistsFlag = false;
      }
    }
    if (memberExistsFlag) {
      System.out.println("Member not found returning to menu");
    }
    return memberExistsFlag;
  }

  /**
   * UI for updating member information.
   */
  private void updateMemberMenu() {
    firstName = lastName = personNum = "";
    close: {
      while (true) {
        System.out.println("What would you like to update? ");
        System.out.println("1. Update firstname");
        System.out.println("2. Update lastname");
        System.out.println("3. Update personal number");
        System.out.println("4. return to previous menu");
        boolean updateMemberFlag = true;
        label:
        while (updateMemberFlag) {
          String updInput = getInput();
          switch (updInput) {
            case "1":
              System.out.println("Enter updated first name: ");
              firstName = String.valueOf(getInput());
              if (! firstName.equalsIgnoreCase("")) {
                System.out.println("First name updated.");
              } else {
                error();
              }
              updateMemberFlag = false;
              break;
            case "2":
              System.out.println("Enter updated last name: ");
              lastName = String.valueOf(getInput());
              if (! lastName.equalsIgnoreCase("")) {
                System.out.println("Last name updated.");
              } else {
                error();
              }
              updateMemberFlag = false;
              break;
            case "3":
              System.out.println("Enter updated person number: ");
              personNum = String.valueOf(getInput());

              if (! personNum.equalsIgnoreCase("")) {
                if (isPersonNum(personNum)) {
                  System.out.println("Personal number updated.");
                  updateMemberFlag = false;
                  break;
                }
              } else {
                error();
              }
            case "4":
              break close;
            default:
              error();
              break label;
          }
        }
        System.out.println("\n");
      }
    }
    System.out.println("\n");
    memberSpecificAction();
  }

  /**
   * Will ask the user how they would like to search.
   */
  private void showMemberSearchChoice() {
    System.out.println("Select one of the search options: ");
    System.out.println("Press 1 to search for members with full names containing your input");
    close: {
      while (true) {
        String input = getInput();
        switch (input) {
          case "1":
            memberSearch = new FullNameContainsMemberSearch();
            break close;
          default:
            error();
            break;
        }
      }
    }
    List<Member> foundMembers;
    System.out.println("Search (enter search condition): ");
    memberSearch = new FullNameContainsMemberSearch(getInput());
    foundMembers = memberSearch.search(reg.getMembers());
    showMemberListChoice(foundMembers);
  }

  /**
   * UI for showing the specific action that a member can manipulate.
   */
  private void memberSpecificAction() {
    System.out.println("Select one of the specific actions for member: ");
    System.out.println("Press 1 to update member details");
    System.out.println("Press 2 to delete member");
    System.out.println("Press 3 to add a boat to member");
    System.out.println("Press 4 to update boat of member");
    System.out.println("Press 5 to delete boat of member");
    System.out.println("Press 6 to return to the previous menu");

    String input = getInput();

    switch (input) {
      case "1":
        updateMemberMenu();
        chosen = MemberOption.UpdateMember;
        break;
      case "2":
        chosen = MemberOption.DeleteMember;
        deleteMemberConfirmation();
        break;
      case "3":
        if (! addBoatOption(reg)) {
          chosen = MemberOption.AddBoat;
        } else {
          chosen = MemberOption.None;
        }
        break;
      case "4":
        if (updateBoatOption(reg)) {
          chosen = MemberOption.UpdateBoat;
        } else {
          chosen = MemberOption.None;
        }
        break;
      case "5":
        if (deleteBoatOption(reg)) {
          chosen = MemberOption.DeleteBoat;
        } else {
          chosen = MemberOption.None;
        }
        break;
      case "6":
        chosen = MemberOption.Return;
        break;
      default:
        error();
        chosen = MemberOption.None;
        break;
    }
  }

  private void logIn() {
    System.out.println("Please enter your username: ");

    username = String.valueOf(getInput());
    if (username.equalsIgnoreCase("")) {
      error();
    }

    System.out.println("Please enter your password: ");
    password = String.valueOf(getInput());
    if (password.equalsIgnoreCase("")) {
      error();
    }
  }

  private void adminError() {
    System.out.println("Feature is locked. Please log in as an admin to access it! ");
  }

  /**
   * UI for reminding exit.
   */
  private void quit() {
    System.out.println("Thanks for using this program");
  }

  /**
   * UI for reminding invalid input.
   */
  private void error() {
    System.out.println("Invalid input. Please try again with a valid one!  \n");
  }

  /**
   * Receive user input from UI.
   *
   * @return the user's input from the console.
   */
  private String getInput() {
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  public int getMemberId() {
    return memberId;
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

  public String getName() {
    return name;
  }

  public int getBoatId() {
    return boatId;
  }

  public BoatType getType() {
    return type;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public double getLength() {
    return length;
  }

  @Override
  public MemberOption getMemberOptions() {
    if (!reg.getLoginStatus()) {
      System.out.println("\n-----------WELCOME TO THE MAIN MENU-----------\n");
      System.out.println("Press 1 to create a new member");
      System.out.println("Press 2 to show a list of all members");
      System.out.println("Press 3 to pick a member");
      System.out.println("Press 4 to search for multiple members");
      System.out.println("Press 5 to log in");
      System.out.println("Press 6 to close the program");
    } else {
      System.out.println("\n-----------WELCOME TO THE MAIN MENU, ADMIN-----------\n");
      System.out.println("Press 1 to create a new member");
      System.out.println("Press 2 to show a list of all members");
      System.out.println("Press 3 to pick a member");
      System.out.println("Press 4 to search for multiple members");
      System.out.println("Press 5 to log out");
      System.out.println("Press 6 to close the program");
    }
    String option = getInput();

    switch (option) {
      case "1":
        if (!reg.getLoginStatus()) {
          adminError();
          chosen = MemberOption.None;
        } else {
          if (! addMemberOption(reg)) {
            chosen = MemberOption.AddNewMember;
            addMemberConfirmation();
          } else {
            chosen = MemberOption.None;
          }
        }
        break;
      case "2":
        showMemberListChoice(reg.getMembers());
        chosen = MemberOption.ShowMembers;
        break;
      case "3":
        if (!reg.getLoginStatus()) {
          adminError();
          chosen = MemberOption.None;
        } else {
          if (! pickMemberOption(reg)) {
            chosen = MemberOption.PickMember;
            memberSpecificAction();
          } else {
            chosen = MemberOption.None;
          }
        }
        break;
      case "4":
        showMemberSearchChoice();
        chosen = MemberOption.SearchMembers;
        break;
      case "5":
        if (!reg.getLoginStatus()) {
          logIn();
          chosen = MemberOption.AdminLogin;
        } else {
          chosen = MemberOption.AdminLogout;
        }
        break;
      case "6":
        quit();
        chosen = MemberOption.Quit;
        break;
      default:
        chosen = MemberOption.None;
        break;
    }
    return chosen;
  }
}
