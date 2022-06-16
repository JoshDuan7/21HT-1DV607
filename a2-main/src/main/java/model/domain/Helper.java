package model.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/** Provides some nice helping utility. */
public class Helper {

  /** Generates a suitable id for a new member/boat. */
  public int generateId(List<? extends ClubEntity> clubEntities) {
    List<Integer> ids = new ArrayList<>();
    for (ClubEntity clubEntity : clubEntities) {
      ids.add(clubEntity.getId());
    }
    Collections.sort(ids);

    int newId = 1;

    for (int i : ids) {
      if (i == newId) {
        newId += 1;
      } else {
        return newId;
      }
    }
    return newId;
  }
}
