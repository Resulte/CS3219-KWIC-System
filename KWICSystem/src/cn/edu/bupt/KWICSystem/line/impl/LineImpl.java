package cn.edu.bupt.KWICSystem.line.impl;

import cn.edu.bupt.KWICSystem.line.Line;

import java.util.ArrayList;

public class LineImpl implements Line {

    private ArrayList<String> words_ = new ArrayList<>();

    @Override
    public void setChar(char ch, int pos, int whichWord) {
        char[] word;

        if (whichWord < words_.size()) {
            word = words_.get(whichWord).toCharArray();
            word[pos] = ch;
            words_.set(whichWord, new String(word, 0, word.length));
        }
    }

    @Override
    public char getChar(int pos, int whichWord) {
        char result = 0;

        if (whichWord < words_.size()) {
            char[] word = words_.get(whichWord).toCharArray();
            result = word[pos];
        }

        return result;
    }

    @Override
    public void addChar(char ch, int whichWord) {
        char[] word;
        char[] new_word;

        if (whichWord < words_.size()) {
            word = words_.get(whichWord).toCharArray();
            new_word = new char[word.length + 1];
            new_word[word.length] = ch;
            words_.set(whichWord, new String(new_word, 0, word.length + 1));
        }
    }

    @Override
    public void deleteChar(int pos, int whichWord) {
        StringBuffer word;

        if (whichWord < words_.size()) {
            word = new StringBuffer(words_.get(whichWord));
            word.deleteCharAt(pos);
            words_.set(whichWord, word.toString());
        }
    }

    @Override
    public int getCharCount(int whichWord) {
        int count = -1;

        if (whichWord < words_.size()) {
            count = words_.get(whichWord).length();
        }

        return count;
    }

    @Override
    public void setWord(char[] word, int whichWord) {
        setWord(new String(word), whichWord);
    }

    @Override
    public void setWord(String word, int whichWord) {
        if (whichWord < words_.size()) {
            words_.set(whichWord, word);
        }
    }

    @Override
    public String getWord(int whichWord) {
        String word = null;

        if (whichWord < words_.size()) {
            word = words_.get(whichWord);
        }

        return word;
    }

    @Override
    public void addWord(char[] word) {
        addWord(new String(word));
    }

    @Override
    public void addWord(String word) {
        words_.add(word);
    }

    @Override
    public void addEmptyWord() {
        words_.add(new String());
    }

    @Override
    public void deleteWord(int whichWord) {
        if (whichWord < words_.size()) {
            words_.remove(whichWord);
        }
    }

    @Override
    public int getWordCount() {
        int count = 0;

        count = words_.size();

        return count;
    }
}
