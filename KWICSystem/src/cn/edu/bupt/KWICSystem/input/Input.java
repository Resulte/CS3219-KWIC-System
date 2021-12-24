package cn.edu.bupt.KWICSystem.input;

import java.util.List;

public interface Input {
    String readLine();
    List<String> readFile(String fileName);
}
