package model.rules;

/** Interface that provides GameRuleSets. */
public interface AbstractFactoryRuleSetInterface {
  
  /** Abstract factory method that returns Rules for the blackjack game. */
  public GameRuleSetInterface createRuleSet();
}