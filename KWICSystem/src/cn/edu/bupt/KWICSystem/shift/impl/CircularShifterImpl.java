package cn.edu.bupt.KWICSystem.shift.impl;

import cn.edu.bupt.KWICSystem.line.LineStorage;
import cn.edu.bupt.KWICSystem.line.impl.LineStorageImpl;
import cn.edu.bupt.KWICSystem.shift.CircularShifter;

import java.util.List;

public class CircularShifterImpl implements CircularShifter {

    private LineStorage shifts_ = new LineStorageImpl();

    @Override
    public void setup(String line) {
        String[] words = line.split("\\s+");

        // iterate through all words of the current line
        for(int j = 0; j < words.length; j++){

            // add a new empty line for the current shift
            shifts_.addEmptyLine();

            // add all words of the current shift
            for(int k = j; k < (words.length + j); k++)

                // add current word to the last line
                // index is the remainder of dividing k and line.length
                shifts_.addWord(words[k % words.length], shifts_.getLineCount() - 1);

        }
    }

    @Override
    public void setup(List<String> fileLines) {
        if (fileLines == null) {
            System.out.println("读取文件失败！");
        }
        for (String line : fileLines) {
            setup(line);
        }
    }


    @Override
    public char getChar(int position, int word, int line) {
        return shifts_.getChar(position, word, line);
    }

    @Override
    public int getCharCount(int word, int line) {
        return shifts_.getCharCount(word, line);
    }

    @Override
    public String getWord(int word, int line) {
        return shifts_.getWord(word, line);
    }

    @Override
    public int getWordCount(int line) {
        return shifts_.getWordCount(line);
    }

    @Override
    public String[] getLine(int line) {
        return shifts_.getLine(line);
    }

    @Override
    public String getLineAsString(int line) {
        return shifts_.getLineAsString(line);
    }

    @Override
    public int getLineCount() {
        return shifts_.getLineCount();
    }
}
