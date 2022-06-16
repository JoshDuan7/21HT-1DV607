package model.visitor;

import model.rules.AmericanNewGameStrategy;
import model.rules.BasicHitStrategy;
import model.rules.InternationalNewGameStrategy;
import model.rules.SoftAndBasicHitStrategy;
import model.rules.TieDealerWinsStrategy;
import model.rules.TiePlayerWinsStrategy;

/**
 * Implement RuleElementVisitor with Swedish presentation.
 */
public class PrintSwedishRuleVisitor implements RuleElementVisitor {
  @Override
  public void visit(SoftAndBasicHitStrategy hitStrategy) {
    System.out.println("-Croupieren ska använda soft17 reglerna. ");
  }

  @Override
  public void visit(BasicHitStrategy hitStrategy) {
    System.out.println("-Croupieren ska använda Basic reglerna.");
  }

  @Override
  public void visit(TiePlayerWinsStrategy tieWhoWins) {
    System.out.println("-Om bada Spelaren och Croupieren har samma kort, Spelaren ska vinna.");
  }

  @Override
  public void visit(TieDealerWinsStrategy tieWhoWins) {
    System.out.println("-Om bada Spelaren och Croupieren har samma kort, Croupieren ska vinna.");
  }

  @Override
  public void visit(AmericanNewGameStrategy regionRules) {
    System.out.println("-Blackjack med Amerikanska reglerna.");
  }

  @Override
  public void visit(InternationalNewGameStrategy regionRules) {
    System.out.println("-Blackjack med internationella reglerna.");
  }
}

