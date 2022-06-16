package model.visitor;

/**
 * This interface is used for RuleElementVisitor to visit rule elements.
 */
public interface RuleElement {
  public void accept(RuleElementVisitor visitor);
}
