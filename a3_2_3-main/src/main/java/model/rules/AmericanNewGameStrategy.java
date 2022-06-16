package model.rules;

import model.Dealer;
import model.Player;
import model.visitor.RuleElement;
import model.visitor.RuleElementVisitor;

/**
 * This class is used to realize American new game strategy.
 */
public class AmericanNewGameStrategy implements NewGameStrategy, RuleElement {

  /**
   * This method is used to initiate American new game.
   *
   * @param dealer The dealer to deal cards to.
   * @param player The player to deal cards to.
   * @return True when finish the initial dealing.
   */
  public boolean newGame(Dealer dealer, Player player) {
    dealer.cardDealt(player, true);
    dealer.cardDealt(dealer, true);
    dealer.cardDealt(player, true);
    dealer.cardDealt(dealer, false);
    return true;
  }

  @Override
  public void accept(RuleElementVisitor visitor) {
    visitor.visit(this);
  }
}
