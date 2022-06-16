import model.domain.Member;
import model.domain.Registry;
import org.junit.jupiter.api.Test;
import model.domain.Boat;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoatTests {
  List<Boat> boatsTest = new ArrayList<>();
  Registry testRegistry = new Registry();
  Member memberT = new Member(999, "Test", "Test", "test", boatsTest);
  Boat boatT = new Boat("boatTestName", 0, null, 0);

  @Test
  public void addBoat() {
    testRegistry.deleteMember(memberT);
    testRegistry.addNewBoat(memberT, boatT.getName(), (int)boatT.getLength(),boatT.getType(), boatT.getLength());
    for(Boat b : memberT.getBoats()) {
      if(b.getName().equalsIgnoreCase(boatT.getName())) {
        assertEquals(b.getName(), "boatTestName");
      }
    }
    testRegistry.deleteMember(memberT);
  }

}
