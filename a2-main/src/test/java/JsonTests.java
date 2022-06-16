import model.domain.Member;
import model.domain.Registry;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTests {
  Registry reg = new Registry();
  List<Member> members = new ArrayList<>();

  @Test
  public void readData(){
    members = reg.getJsonPersistence().load();
    assertEquals(members.size(), 3);
  }
}
