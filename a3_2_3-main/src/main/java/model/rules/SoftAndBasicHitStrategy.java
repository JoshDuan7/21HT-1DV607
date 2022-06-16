package model.rules;

import model.Card;
import model.Card.Value;
import model.Player;
import model.visitor.RuleElement;
import model.visitor.RuleElementVisitor;

/**
 * This class is used for realizing SoftAndBasicHitStrategy.
 */
public class SoftAndBasicHitStrategy implements HitStrategy, RuleElement {

  private static final int hitLimit = 17;

  /**
   * This method is used for presenting do hit game behaviour.
   *
   * @param dealer the player to check.
   * @return True then dealer will take a card, false dealer stand.
   */
  public boolean doHit(Player dealer) {

    if (dealer.calcScore() >= hitLimit) {
      Iterable<Card> cards = dealer.getHand();
      for (Card card : cards) {
        if (card.getValue().equals(Value.Ace)) {
          if (dealer.calcScore() >= hitLimit) {
            return false;
          }
          return true;
        }
      }
    }

    return dealer.calcScore() < hitLimit;
  }

  @Override
  public void accept(RuleElementVisitor visitor) {
    visitor.visit(this);
  }
}
