package model.rules;

import model.visitor.RuleElement;
import model.visitor.RuleElementVisitor;

/** This rule allows the player to win if a tie were to take place. */
public class TiePlayerWinsStrategy implements TieRule, RuleElement {

  @Override
  public boolean tieWhoWins() {
    return false;
  }

  @Override
  public void accept(RuleElementVisitor visitor) {
    visitor.visit(this);
  }
}
