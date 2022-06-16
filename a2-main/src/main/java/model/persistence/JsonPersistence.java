package model.persistence;

import com.alibaba.fastjson.JSON;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import model.domain.Member;
import model.domain.Registry;

/**
 * This class is used to read off members from a JSON file.
 */

public class JsonPersistence implements Persistence {
  private List<Member> members;
  private Registry registry;

  public JsonPersistence(Registry reg) {
    this.registry = reg;
  }

  @Override
  public List<Member> load() {
    try {
      File file = new File("files/Registry.json");
      FileInputStream inputStream = new FileInputStream(file);
      int size = inputStream.available();
      byte[] buffer = new byte[size];
      inputStream.read(buffer);
      inputStream.close();
      String memberStr = new String(buffer, StandardCharsets.UTF_8);
      List<Member> members = JSON.parseArray(memberStr, Member.class);
      registry.setJsonFlag();
      return members;
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("IO Exception");
    }
  }

  @Override
  public void save() {
    List<Member> membersForSave = registry.getMembers();
    try {
      File file = new File("files/Registry.json");
      PrintWriter printer = new PrintWriter(file);
      if (!file.exists()) {
        file.createNewFile();
      }
      String registryStr = JSON.toJSONString(membersForSave);
      printer.print(registryStr);
      printer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
