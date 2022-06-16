package model.visitor;

import model.rules.AmericanNewGameStrategy;
import model.rules.BasicHitStrategy;
import model.rules.InternationalNewGameStrategy;
import model.rules.SoftAndBasicHitStrategy;
import model.rules.TieDealerWinsStrategy;
import model.rules.TiePlayerWinsStrategy;

/**
 * Implement RuleElementVisitor with English presentation.
 */
public class PrintEnglishRuleVisitor implements RuleElementVisitor {
  @Override
  public void visit(SoftAndBasicHitStrategy hitStrategy) {
    System.out.println("-Dealer will follow Soft17 rule.");
  }

  @Override
  public void visit(BasicHitStrategy hitStrategy) {
    System.out.println("-Dealer will follow Basic hit rule.");
  }

  @Override
  public void visit(TiePlayerWinsStrategy tieWhoWins) {
    System.out.println("-In a tie the player will win.");
  }

  @Override
  public void visit(TieDealerWinsStrategy tieWhoWins) {
    System.out.println("-In a tie the dealer will win.");
  }

  @Override
  public void visit(AmericanNewGameStrategy regionRules) {
    System.out.println("-Blackjack with American rules.");
  }

  @Override
  public void visit(InternationalNewGameStrategy regionRules) {
    System.out.println("-Blackjack with International rules.");
  }
}
