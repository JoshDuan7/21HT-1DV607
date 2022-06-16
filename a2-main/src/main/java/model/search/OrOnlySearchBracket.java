package model.search;

import java.util.ArrayList;
import java.util.List;
import model.domain.Member;
import wniemiec.data.java.Pair;

/**
 * Where multiple search conditions can be collected, as if nested in brackets.
 */
public class OrOnlySearchBracket extends SearchBracket {
  private final List<Pair<Operator, MemberSearch>> searchConditions;

  public OrOnlySearchBracket() {
    searchConditions = new ArrayList<>();
  }

  /**
   * Method that adds extra rules to the searcher.
   *
   * @param memberSearch The search or filter option.
   * @return true to avoid toppling search options over each other.
   */
  public boolean add(MemberSearch memberSearch) {
    if (searchConditions.size() == 0) {
      searchConditions.add(new Pair<>(Operator.None, memberSearch));
      return true;
    }
    return false;
  }

  /**
   * Method that is used to add the filter with the operator.
   *
   * @param operator     The operator to be added to the filter.
   * @param memberSearch The filter itself.
   * @return false if there is no filter added before.
   */
  public boolean add(Operator operator, MemberSearch memberSearch) {
    if (searchConditions.size() != 0 && operator != Operator.None) {
      if (operator != Operator.And) {
        searchConditions.add(new Pair<>(operator, memberSearch));
      } else {
        int lastIdx = searchConditions.size() - 1;
        MemberSearch last = searchConditions.get(lastIdx).getSecond();
        if (last instanceof AndOnlySearchBracket) {
          ((AndOnlySearchBracket) last).add(memberSearch);
        } else {
          Operator lastOperator = searchConditions.get(lastIdx).getFirst();
          Pair<Operator, MemberSearch> leftCondition = searchConditions.get(lastIdx);
          Pair<Operator, MemberSearch> rightCondition = new Pair(operator, memberSearch);
          AndOnlySearchBracket andBracket = new AndOnlySearchBracket(leftCondition, rightCondition);
          searchConditions.set(lastIdx, new Pair<>(lastOperator, andBracket));
        }
      }
      return true;
    }
    return false;
  }

  public List<Pair<Operator, MemberSearch>> getSearchConditions() {
    return searchConditions;
  }

  @Override
  public List<Member> search(List<Member> members) {
    List<Member> found = new ArrayList<Member>();
    // Now the ors
    for (Pair<Operator, MemberSearch> searchCondition : searchConditions) {
      safeAdd(searchCondition.getSecond().search(members), found);
    }
    return found;
  }

  /**
   * Will add members of a List to another without duplicates.
   *
   * @param fromMems The original list to be iterated through.
   * @param toMems   The list to be without duplicates.
   */
  private void safeAdd(List<Member> fromMems, List<Member> toMems) {
    for (Member member : fromMems) {
      if (! toMems.contains(member)) {
        toMems.add(member);
      }
    }
  }

}
