package model.rules;

import model.visitor.RuleElementVisitor;

/** Rules for that provide a slight advantage to the Player. */
public class PlayerAdvantageRuleSet implements GameRuleSetInterface {

  private HitStrategy dealerHitRule;
  private TieRule tieWinner;
  private NewGameStrategy regionalStrategy;
  
  /** Constructor for PlayerAdvantageRuleSet. */
  public PlayerAdvantageRuleSet() {
    dealerHitRule = new BasicHitStrategy();
    tieWinner = new TiePlayerWinsStrategy();
    regionalStrategy = new AmericanNewGameStrategy();
  }
  
  /** Returns HitRule for Player advantage. */
  @Override
  public HitStrategy getHitRule() {
    return dealerHitRule;
  }
  
  /** Returns HitRule for Player advantage. */
  @Override
  public TieRule getTieRule() {
    return tieWinner;
  }
  
  /** Returns HitRule for Player advantage. */
  @Override
  public NewGameStrategy getNewGameRule() {
    return regionalStrategy;
  }

  /**
   * This method is used for showing the rules of the current game.
   *
   * @param visitor Rule element visitor which is used to access game rules.
   */
  public void showGameRules(RuleElementVisitor visitor) {
    dealerHitRule.accept(visitor);
    tieWinner.accept(visitor);
    regionalStrategy.accept(visitor);
  }
}
