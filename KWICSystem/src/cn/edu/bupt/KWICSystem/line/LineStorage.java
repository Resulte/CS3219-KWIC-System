package cn.edu.bupt.KWICSystem.line;

public interface LineStorage {
    void setChar(char c, int position, int word, int line);
    char getChar(int position, int word, int line);
    void addChar(char c, int word, int line);
    void deleteChar(int position, int word, int line);
    int getCharCount(int word, int line);
    void setWord(char[] chars, int word, int line);
    void setWord(String chars, int word, int line);
    String getWord(int word, int line);
    void addWord(char[] chars, int line);
    void addWord(String chars, int line);
    void addEmptyWord(int line);
    void deleteWord(int word, int line);
    int getWordCount(int line);
    void setLine(char[][] words, int line);
    void setLine(String[] words, int line);
    String[] getLine(int line);
    String getLineAsString(int line);
    void addLine(char[][] words);
    void addLine(String[] words);
    void addEmptyLine();
    void deleteLine(int line);
    int getLineCount();
}
