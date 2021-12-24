package cn.edu.bupt.KWICSystem.line;

public interface Line {
    void setChar(char ch, int pos, int whichWord);
    char getChar(int pos, int whichWord);
    void addChar(char ch, int whichWord);
    void deleteChar(int pos, int whichWord);
    int getCharCount(int whichWord);
    void setWord(char[] word, int whichWord);
    void setWord(String word, int whichWord);
    String getWord(int whichWord);
    void addWord(char[] word);
    void addWord(String word);
    void addEmptyWord();
    void deleteWord(int whichWord);
    int getWordCount();
}
