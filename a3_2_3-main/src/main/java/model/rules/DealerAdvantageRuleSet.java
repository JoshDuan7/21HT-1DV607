package model.rules;

import model.visitor.RuleElementVisitor;

/** Rules for that provide a slight advantage to the dealer. */
public class DealerAdvantageRuleSet implements GameRuleSetInterface {

  private HitStrategy dealerHitRule;
  private TieRule tieWinner;
  private NewGameStrategy regionalStrategy;

  /** Constructor for DealerAdvantageRuleSet. */
  public DealerAdvantageRuleSet() {
    dealerHitRule = new SoftAndBasicHitStrategy();
    tieWinner = new TieDealerWinsStrategy();
    regionalStrategy = new InternationalNewGameStrategy();
  }

  /** Returns HitRule for dealer advantage. */
  @Override
  public HitStrategy getHitRule() {
    return dealerHitRule;
  }

  /** Returns TieRule for dealer advantage. */
  @Override
  public TieRule getTieRule() {
    return tieWinner;
  }

  /** Returns NewGameStrategy for dealer advantage. */
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
