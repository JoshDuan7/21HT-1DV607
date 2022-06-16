package model.rules;

/** Factory used to create rule set from DealerAdvantageRuleSet. */
public class DealerAdvantageRuleFactory implements AbstractFactoryRuleSetInterface {

  /** Returns Rules for Dealer advantage. */
  @Override
  public GameRuleSetInterface createRuleSet() {
    return new DealerAdvantageRuleSet();
  }

  
  
}
