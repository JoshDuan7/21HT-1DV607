package model.rules;

import model.visitor.RuleElementVisitor;

/** Interface that returns GameRuleSetInterface. */
public interface GameRuleSetInterface {

  public HitStrategy getHitRule();

  public TieRule getTieRule();
  
  public NewGameStrategy getNewGameRule();

  public void showGameRules(RuleElementVisitor visitor);
  
}


