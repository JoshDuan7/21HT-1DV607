package model.search;

import java.util.List;
import model.domain.Member;

/**
 * Interface for strategy pattern implementations of different ways to search members.
 */
public interface MemberSearch {
  List<Member> search(List<Member> members);
}
