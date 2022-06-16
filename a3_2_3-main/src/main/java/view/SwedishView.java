package view;

import model.rules.GameRuleSetInterface;
import model.visitor.PrintSwedishRuleVisitor;

/** Implements a Swedish console view. */
public class SwedishView extends GeneralView {

  /**
   * Constructor for creating Swedish UI.
   */
  public SwedishView() {
    setWelcomeText("Hej Black Jack Världen");
    setInstructionsText("Skriv 'p' för att Spela, 'h' för nytt kort, 's' för att stanna 'q' för att avsluta\n");
    setPlayerText("Spelare");
    setDealerText("Croupier");
    setHasText(" Har: ");
    setScoreText("Poäng: ");
    setGameOverText("Slut: ");
    setDealerWinText("Croupiern Vann!");
    setUserWinText("Du vann!");
  }

  /**
   * Displays a Swedish card.
   *
   * @param card The card to display.
   */
  public void displayCard(model.Card card) {
    if (card.getColor() == model.Card.Color.Hidden) {
      System.out.println("Dolt Kort");
    } else {
      String[] colors = {"Hjärter", "Spader", "Ruter", "Klöver"};
      String[] values = {
        "två", "tre", "fyra", "fem", "sex", "sju", "åtta", "nio", "tio", "knekt", "dam", "kung",
        "ess"};
      System.out.println(
          "" + colors[card.getColor().ordinal()] + " " + values[card.getValue().ordinal()]);
    }
  }

  /**
   * Displays all the rules of the game.
   *
   * @param rules Print all the rules used in this game.
   */
  @Override
  public void showRules(GameRuleSetInterface rules) {
    System.out.println("Reglerna som tillämpas i detta spel är följande: ");
    rules.showGameRules(new PrintSwedishRuleVisitor());
  }
}
