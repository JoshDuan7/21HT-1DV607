package model.rules;

import model.visitor.RuleElementVisitor;

/** Rules for that provide the default rules of blackjack. */
public class DefaultGameRuleSet implements GameRuleSetInterface {

  private HitStrategy dealerHitRule;
  private TieRule tieWinner;
  private NewGameStrategy regionalStrategy;
  
  /** Constructor for DefaultGameRuleSet. */
  public DefaultGameRuleSet() {
    dealerHitRule = new SoftAndBasicHitStrategy(); //smarter dealer
    tieWinner = new TieDealerWinsStrategy(); // dealer wins
    regionalStrategy = new AmericanNewGameStrategy(); 
  }

  /** Method that returns HitStrategy for DefaultGameRuleSet. */
  @Override
  public HitStrategy getHitRule() {
    return dealerHitRule;
  }
  
  /** Method that returns HitStrategy for DefaultGameRuleSet. */
  @Override
  public TieRule getTieRule() {
    return tieWinner;
  }
  
  /** Method that returns HitStrategy for DefaultGameRuleSet. */
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
