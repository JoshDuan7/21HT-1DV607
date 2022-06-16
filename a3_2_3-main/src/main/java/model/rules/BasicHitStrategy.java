package model.rules;

import model.Player;
import model.visitor.RuleElement;
import model.visitor.RuleElementVisitor;

/**
 * This class is used to realize basic hit strategy.
 */
public class BasicHitStrategy implements HitStrategy, RuleElement {
  private static final int hitLimit = 17;

  public boolean doHit(Player dealer) {
    return dealer.calcScore() < hitLimit;
  }

  @Override
  public void accept(RuleElementVisitor visitor) {
    visitor.visit(this);
  }
}
