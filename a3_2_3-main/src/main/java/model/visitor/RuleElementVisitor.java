package model.visitor;

import model.rules.AmericanNewGameStrategy;
import model.rules.BasicHitStrategy;
import model.rules.InternationalNewGameStrategy;
import model.rules.SoftAndBasicHitStrategy;
import model.rules.TieDealerWinsStrategy;
import model.rules.TiePlayerWinsStrategy;


/**
 * Implement rule element visitor interface.
 */
public interface RuleElementVisitor {

  void visit(SoftAndBasicHitStrategy hitStrategy);

  void visit(BasicHitStrategy hitStrategy);

  void visit(TiePlayerWinsStrategy tieWhoWins);

  void visit(TieDealerWinsStrategy tieWhoWins);

  void visit(AmericanNewGameStrategy regionRules);

  void visit(InternationalNewGameStrategy regionRules);
}
