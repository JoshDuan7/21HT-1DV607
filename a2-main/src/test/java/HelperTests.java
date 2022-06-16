import model.domain.Helper;
import java.util.List;
import model.domain.Member;
import model.domain.Registry;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelperTests {
  Registry reg = new Registry();
  Helper helper = new Helper();

  @Test
  public void generateId() {
    Iterable<Member> tempMembers = reg.getJsonPersistence().load();
    List<Member> members = (List<Member>) tempMembers;
    assertEquals(helper.generateId(members), 4);
  }
}
