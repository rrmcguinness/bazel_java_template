package example.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
    final List<String> state = new ArrayList<>();

    public boolean addId(String id) {
      if (!state.contains(id)) {
        state.add(id);
        return true;
      }
      return false;
    }
}