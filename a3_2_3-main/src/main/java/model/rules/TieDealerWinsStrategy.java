package model.rules;

import model.visitor.RuleElement;
import model.visitor.RuleElementVisitor;

/** This rule allows the Dealer to win if a tie were to take place. */
public class TieDealerWinsStrategy implements TieRule, RuleElement {

  @Override
  public boolean tieWhoWins() {
    return true;
  }

  @Override
  public void accept(RuleElementVisitor visitor) {
    visitor.visit(this);
  }
}
