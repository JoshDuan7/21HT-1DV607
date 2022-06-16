package model.rules;

/** Factory used to create rule set from DefaultGameRuleSet. */
public class DefaultGameRuleFactory implements AbstractFactoryRuleSetInterface {

  /** Returns Default Rules. */
  @Override
  public GameRuleSetInterface createRuleSet() {
    return new DefaultGameRuleSet();
  }
}
