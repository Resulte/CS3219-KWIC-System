package cn.edu.bupt.KWICSystem.sort.impl;

import cn.edu.bupt.KWICSystem.shift.CircularShifter;
import cn.edu.bupt.KWICSystem.sort.Alphabetizer;

public class AlphabetizerImpl implements Alphabetizer {

    private int sorted_[];

    private CircularShifter shifter_;

    @Override
    public void alpha(CircularShifter shifter) {
        shifter_ = shifter;

        // initialize the index array
        sorted_ = new int[shifter_.getLineCount()];
        for(int i = 0; i < sorted_.length; i++)
            sorted_[i] = i;

        // create heap
        for(int i = (sorted_.length / 2 - 1); i >= 0; i--)
            siftDown(i, sorted_.length);

        // remove the root and recreate the heap
        for(int i = (sorted_.length - 1); i >= 1; i--){

            // remove the root
            int tmp = sorted_[0];
            sorted_[0] = sorted_[i];
            sorted_[i] = tmp;

            // recreate the heap
            siftDown(0, i);
        }
    }

    private void siftDown(int root, int bottom){
        int max_child = root * 2 + 1;

        while(max_child < bottom){
            if((max_child + 1) < bottom)
                if(shifter_.getLineAsString(sorted_[max_child + 1]).compareTo(shifter_.getLineAsString(sorted_[max_child])) > 0)
                    max_child++;

            if(shifter_.getLineAsString(sorted_[root]).compareTo(shifter_.getLineAsString(sorted_[max_child])) < 0){
                int tmp = sorted_[root];
                sorted_[root] = sorted_[max_child];
                sorted_[max_child] = tmp;
                root = max_child;
                max_child = root * 2 + 1;
            }else
                break;
        }
    }

    @Override
    public String[] getLine(int line) {
        return shifter_.getLine(sorted_[line]);
    }

    @Override
    public String getLineAsString(int line) {
        return shifter_.getLineAsString(sorted_[line]);
    }

    @Override
    public int getLineCount() {
        return shifter_.getLineCount();
    }
}
