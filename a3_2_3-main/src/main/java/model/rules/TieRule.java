package model.rules;

import model.visitor.RuleElementVisitor;

/** Rule interface that determines who would would win if a tie were to happen in game. */
public interface TieRule {

  boolean tieWhoWins();

  void accept(RuleElementVisitor visitor);
}
