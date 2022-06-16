import controller.RegistryController;
import model.domain.Registry;
import view.Console;
import view.MemberBoatUi;

/** This class starts up the application. */
public class RegistryApp {
  /** This is the entrance of the Registry App. */
  public static void main(String[] args) {
    Registry registry = new Registry();
    MemberBoatUi ui = new Console(registry);
    if (registry.getJsonFlag()) {
      registry.getJsonPersistence().load();
    } else {
      registry.getTxtPersistence().load();
    }
    RegistryController rc = new RegistryController(registry.getAdmin(), registry, ui);
    rc.handleMembers();
  }
}
