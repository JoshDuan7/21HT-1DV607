package model.rules;

import model.Player;
import model.visitor.RuleElementVisitor;

/** Rule interface that encapsulates when a player (the dealer) should take more cards. */
public interface HitStrategy {
  /**
   * Checks if the player (dealer) should take more cards.
   *
   * @param dealer the player to check.
   * @return True if the rule decided the player should take another card.
   */
  boolean doHit(Player dealer);

  /**
   * This method is used for enabling rule element visitor to access the hit strategy.
   *
   * @param visitor Rule element visitor which is used to access this strategy.
   */
  void accept(RuleElementVisitor visitor);
}
