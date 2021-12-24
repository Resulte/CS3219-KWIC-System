package cn.edu.bupt.KWICSystem.sort;

import cn.edu.bupt.KWICSystem.shift.CircularShifter;

public interface Alphabetizer {
    void alpha(CircularShifter shifter);
    String[] getLine(int line);
    String getLineAsString(int line);
    int getLineCount();
}
