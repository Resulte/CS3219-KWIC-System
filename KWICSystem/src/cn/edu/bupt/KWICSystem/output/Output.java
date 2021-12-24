package cn.edu.bupt.KWICSystem.output;

import cn.edu.bupt.KWICSystem.sort.Alphabetizer;

import java.util.List;

public interface Output {
    List<String> print(Alphabetizer alphabetizer);
    void write(Alphabetizer alphabetizer);
}
