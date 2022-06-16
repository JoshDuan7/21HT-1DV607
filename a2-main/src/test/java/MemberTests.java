import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import controller.RegistryController;
import model.domain.Admin;
import model.domain.Boat;
import model.domain.BoatType;
import model.domain.Member;
import model.domain.Registry;
import model.search.AndOnlySearchBracket;
import model.search.BoatCountMemberSearch;
import model.search.FullNameContainsMemberSearch;
import model.search.MemberSearch;
import model.search.Operator;
import model.search.OrOnlySearchBracket;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MemberTests {
  Registry testRegistry = new Registry();
  Admin testAdmin = testRegistry.getAdmin();
  Member testM = new Member(0, "testName", "tesTLN", "test", null);

  @Test
  public void addMember() {
    testAdmin.logIn("admin", "admin");
    testRegistry.deleteMember(testM);
    testRegistry.addNewMember(
            testM.getId(), testM.getFirstName(), testM.getLastName(), testM.getPersonNum(), testM.getBoats());
    for (Member m : testRegistry.getMembers()) {
      if (m.getFirstName().equalsIgnoreCase(testM.getFirstName())) {
        assertEquals(m.getFirstName(), "testName");
      }
    }
  }

  @Test
  public void BracketTest2() {
    Registry testRegistry = new Registry();
    Admin testAdmin = testRegistry.getAdmin();
    testAdmin.logIn("admin", "admin");
    testRegistry.getMembers().clear();

    Boat boat1 = new Boat("LolBoat", 1, BoatType.KAYAK, 3);
    Boat boat2 = new Boat("EpicBoat", 2, BoatType.KAYAK, 3);
    Boat boat3 = new Boat("EpicSaxBoat", 3, BoatType.KAYAK, 3);

    Member mem1 = new Member(1, "Jackeline", "tesTLN", "200011241111", new ArrayList<>()); // 20
    Member mem2 = new Member(2, "Jessica", "tesTLN", "200009201111", new ArrayList<>());
    Member mem3 = new Member(3, "Jak", "tesTLN", "200109201111", new ArrayList<>());
    Member mem4 = new Member(4, "Joe", "Mamma", "200010291111", new ArrayList<>());
    Member mem5 = new Member(5, "Abe", "Mamma", "200010291111", new ArrayList<>());

    mem1.getBoats().add(boat1);
    mem1.getBoats().add(boat2);
    mem2.getBoats().add(boat3);

    // Search = NameContains("Ja") && BoatCount(2) || NameContains("Abe") || BoatCount(1)
    // Should return Jackeline, Abe and Jessica
    OrOnlySearchBracket sB = new OrOnlySearchBracket();
    MemberSearch nameCond1 = new FullNameContainsMemberSearch("ja");
    assertTrue(sB.add(nameCond1));  // True means it was added successfully.
    MemberSearch boatCond1 = new BoatCountMemberSearch(2);
    assertTrue(sB.add(Operator.And, boatCond1));
    MemberSearch nameCond2 = new FullNameContainsMemberSearch("Abe");
    assertTrue(sB.add(Operator.Or, nameCond2));
    MemberSearch boatCond2 = new BoatCountMemberSearch(1);
    assertTrue(sB.add(Operator.Or, boatCond2));

    testRegistry.addNewMember(mem1.getId(), mem1.getFirstName(), mem1.getLastName(), mem1.getPersonNum(),
            mem1.getBoats());
    testRegistry.addNewMember(mem2.getId(), mem2.getFirstName(), mem2.getLastName(), mem2.getPersonNum(),
            mem2.getBoats());
    testRegistry.addNewMember(mem3.getId(), mem3.getFirstName(), mem3.getLastName(), mem3.getPersonNum(),
            mem3.getBoats());
    testRegistry.addNewMember(mem4.getId(), mem4.getFirstName(), mem4.getLastName(), mem4.getPersonNum(),
            mem4.getBoats());
    testRegistry.addNewMember(mem5.getId(), mem5.getFirstName(), mem5.getLastName(), mem5.getPersonNum(),
            mem5.getBoats());

    List<Member> testMembers = sB.search(testRegistry.getMembers());

    ArrayList<String> nameList = new ArrayList<>();
    for (Member member : testMembers) {
      nameList.add(member.getFirstName());
    }
    Collections.sort(nameList);

    String actualNames = "Abe, Jackeline, Jessica";
    String testNames = String.join(", ", nameList);

    assertEquals(actualNames, testNames, "Didn't work :/");

    assertEquals(actualNames, testNames, "Didn't work :/");

    testAdmin.logOut();
  }

  @Test
  public void BracketTest3() {
    Registry testRegistry = new Registry();
    Admin testAdmin = testRegistry.getAdmin();
    testAdmin.logIn("admin", "admin");
    testRegistry.getMembers().clear();

    // Search = NameContains("Ja") && ((NameContains("e") || NameContains("a")) && BoatCount(0)) || NameContains("Mamma")
    // Should return Jack, Joe and Abe
    OrOnlySearchBracket sB1 = new OrOnlySearchBracket();
    MemberSearch nameCond1 = new FullNameContainsMemberSearch("e");
    assertTrue(sB1.add(nameCond1));  // True means it was added successfully.
    MemberSearch nameCond2 = new FullNameContainsMemberSearch("a");
    assertTrue(sB1.add(Operator.Or, nameCond2));

    AndOnlySearchBracket sB2 = new AndOnlySearchBracket();
    MemberSearch boatCond1 = new BoatCountMemberSearch(0);
    sB2.add(sB1);
    sB2.add(boatCond1);

    OrOnlySearchBracket sB3 = new OrOnlySearchBracket();
    MemberSearch nameCond3 = new FullNameContainsMemberSearch("Ja");
    assertTrue(sB3.add(nameCond3));
    assertTrue(sB3.add(Operator.And, sB2));
    MemberSearch nameCond4 = new FullNameContainsMemberSearch("Mamma");
    assertTrue(sB3.add(Operator.Or, nameCond4));

    // Fill test registry.
    Boat boat1 = new Boat("LolBoat", 1, BoatType.KAYAK, 3);
    Boat boat2 = new Boat("EpicBoat", 2, BoatType.KAYAK, 3);
    Boat boat3 = new Boat("EpicSaxBoat", 3, BoatType.KAYAK, 3);

    Member mem1 = new Member(1, "Jackeline", "tesTLN", "200011241111", new ArrayList<>()); // 20
    Member mem2 = new Member(2, "Jessica", "tesTLN", "200009201111", new ArrayList<>());
    Member mem3 = new Member(3, "Jak", "tesTLN", "200109201111", new ArrayList<>());
    Member mem4 = new Member(4, "Joe", "Mamma", "200010291111", new ArrayList<>());
    Member mem5 = new Member(5, "Abe", "Mamma", "200010291111", new ArrayList<>());

    mem1.getBoats().add(boat1);
    mem1.getBoats().add(boat2);
    mem2.getBoats().add(boat3);

    testRegistry.addNewMember(mem1.getId(), mem1.getFirstName(), mem1.getLastName(), mem1.getPersonNum(),
            mem1.getBoats());
    testRegistry.addNewMember(mem2.getId(), mem2.getFirstName(), mem2.getLastName(), mem2.getPersonNum(),
            mem2.getBoats());
    testRegistry.addNewMember(mem3.getId(), mem3.getFirstName(), mem3.getLastName(), mem3.getPersonNum(),
            mem3.getBoats());
    testRegistry.addNewMember(mem4.getId(), mem4.getFirstName(), mem4.getLastName(), mem4.getPersonNum(),
            mem4.getBoats());
    testRegistry.addNewMember(mem5.getId(), mem5.getFirstName(), mem5.getLastName(), mem5.getPersonNum(),
            mem5.getBoats());

    List<Member> testMembers = sB3.search(testRegistry.getMembers());

    ArrayList<String> nameList = new ArrayList<>();
    for (Member member : testMembers) {
      nameList.add(member.getFirstName());
    }
    Collections.sort(nameList);

    String actualNames = "Abe, Jak, Joe";
    String testNames = String.join(", ", nameList);
    testAdmin.logOut();

    assertEquals(actualNames, testNames, "Didn't work :/");
  }

  @Test
  public void BracketTest4() {
    Registry testRegistry = new Registry();
    Admin testAdmin = testRegistry.getAdmin();
    testAdmin.logIn("admin", "admin");
    testRegistry.getMembers().clear();

    // Search = NameContains("J") && BoatCount(1) || NameContains("oe") && (BoatCount(0) || NameContains("Mamma"))
    // Should return Jessica and Joe
    OrOnlySearchBracket sB1 = new OrOnlySearchBracket();
    MemberSearch nameCond1 = new FullNameContainsMemberSearch("Mamma");
    assertTrue(sB1.add(nameCond1));  // True means it was added successfully.
    MemberSearch nameCond2 = new BoatCountMemberSearch(0);
    assertTrue(sB1.add(Operator.Or, nameCond2));

    OrOnlySearchBracket sB2 = new OrOnlySearchBracket();
    MemberSearch nameCond3 = new FullNameContainsMemberSearch("J");
    assertTrue(sB2.add(nameCond3));
    MemberSearch boatCond1 = new BoatCountMemberSearch(1);
    assertTrue(sB2.add(Operator.And, boatCond1));
    MemberSearch nameCond4 = new FullNameContainsMemberSearch("oe");
    assertTrue(sB2.add(Operator.Or, nameCond4));
    assertTrue(sB2.add(Operator.And, sB1));

    // Fill test registry.
    Boat boat1 = new Boat("LolBoat", 1, BoatType.KAYAK, 3);
    Boat boat2 = new Boat("EpicBoat", 2, BoatType.KAYAK, 3);
    Boat boat3 = new Boat("EpicSaxBoat", 3, BoatType.KAYAK, 3);

    Member mem1 = new Member(1, "Jackeline", "tesTLN", "200011241111", new ArrayList<>()); // 20
    Member mem2 = new Member(2, "Jessica", "tesTLN", "200009201111", new ArrayList<>());
    Member mem3 = new Member(3, "Jak", "tesTLN", "200109201111", new ArrayList<>());
    Member mem4 = new Member(4, "Joe", "Mamma", "200010291111", new ArrayList<>());
    Member mem5 = new Member(5, "Abe", "Mamma", "200010291111", new ArrayList<>());

    mem1.getBoats().add(boat1);
    mem1.getBoats().add(boat2);
    mem2.getBoats().add(boat3);

    testRegistry.addNewMember(mem1.getId(), mem1.getFirstName(), mem1.getLastName(), mem1.getPersonNum(),
            mem1.getBoats());
    testRegistry.addNewMember(mem2.getId(), mem2.getFirstName(), mem2.getLastName(), mem2.getPersonNum(),
            mem2.getBoats());
    testRegistry.addNewMember(mem3.getId(), mem3.getFirstName(), mem3.getLastName(), mem3.getPersonNum(),
            mem3.getBoats());
    testRegistry.addNewMember(mem4.getId(), mem4.getFirstName(), mem4.getLastName(), mem4.getPersonNum(),
            mem4.getBoats());
    testRegistry.addNewMember(mem5.getId(), mem5.getFirstName(), mem5.getLastName(), mem5.getPersonNum(),
            mem5.getBoats());

    List<Member> testMembers = sB2.search(testRegistry.getMembers());

    ArrayList<String> nameList = new ArrayList<>();
    for (Member member : testMembers) {
      nameList.add(member.getFirstName());
    }
    Collections.sort(nameList);

    String actualNames = "Jessica, Joe";
    String testNames = String.join(", ", nameList);

    assertEquals(actualNames, testNames, "Didn't work :/");
    testAdmin.logOut();
  }
}
