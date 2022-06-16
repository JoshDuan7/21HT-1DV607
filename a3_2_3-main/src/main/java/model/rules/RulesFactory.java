package model.rules;

/** Creates concrete rules. */
public class RulesFactory {

  /**
   * Creates the rule to use for the dealer's hit behavior.
   *
   * @return The rule to use
   */
  public HitStrategy getHitRule() {
    return new SoftAndBasicHitStrategy(); // BasicHitStrategy();
  }

  /** Used to determine whether the player or the dealer should win if a tie were to take place. */
  public TieRule getTieRule() {
    return new TiePlayerWinsStrategy(); // TieDealerWinsStrategy();
  }

  /**
   * Crates the rule to use when starting a new game.
   *
   * @return The rule to use.
   */
  public NewGameStrategy getNewGameRule() {
    return new AmericanNewGameStrategy(); // InternationalNewGameStrategy();
  } 
}
