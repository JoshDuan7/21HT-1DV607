package model.rules;

/** Factory used to create rule set from PlayerAdvantageRuleSet. */
public class PlayerAdvantageRuleFactory implements AbstractFactoryRuleSetInterface {

  /** Returns Rules for player advantage. */
  @Override
  public GameRuleSetInterface createRuleSet() {
    return new PlayerAdvantageRuleSet();
  }
  
  
}
