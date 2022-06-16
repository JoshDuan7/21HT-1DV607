package model.rules;

import model.Dealer;
import model.Player;
import model.visitor.RuleElement;
import model.visitor.RuleElementVisitor;

/**
 * This class is used for realizing international new game strategy.
 */
public class InternationalNewGameStrategy implements NewGameStrategy, RuleElement {

  /**
   * This method is used for initiating international new game.
   *
   * @param dealer The dealer to deal cards to.
   * @param player The player to deal cards to.
   * @return True when finish the initial dealing.
   */
  public boolean newGame(Dealer dealer, Player player) {
    dealer.cardDealt(player, true);
    dealer.cardDealt(player, true);
    return true;
  }

  @Override
  public void accept(RuleElementVisitor visitor) {
    visitor.visit(this);
  }
}
