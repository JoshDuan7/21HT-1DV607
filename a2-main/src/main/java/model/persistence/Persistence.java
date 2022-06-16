package model.persistence;

import java.util.ArrayList;
import java.util.List;

/** Loads and saves registry data between uses of the app for persistence. */
public interface Persistence {
  <T> List<T> load();

  void save();
}
