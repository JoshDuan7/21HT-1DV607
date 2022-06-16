package model.rules;

import model.Dealer;
import model.Player;
import model.visitor.RuleElementVisitor;

/** Rule interface for mangaing the start of a new game. I.e. dealing the cards. */
public interface NewGameStrategy {
  /**
   * Encapsulates the start of a new game. I.e. should deal cards to dealer and player according to
   * some rule.
   *
   * @param dealer The dealer to deal cards to.
   * @param player The player to deal cards to.
   * @return True if the game could be started.
   */
  boolean newGame(Dealer dealer, Player player);

  /**
   * This method is used for enabling rule element visitor to access the new game strategy.
   *
   * @param visitor Rule element visitor which is used to access this strategy.
   */
  void accept(RuleElementVisitor visitor);
}
