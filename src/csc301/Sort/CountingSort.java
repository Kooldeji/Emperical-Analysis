/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc301.Sort;

import GraphicalEmpericalAnalysis.SortPane;
import csc301.EmpricalAnalyser;
import java.util.Arrays;

/**
 *
 * @author Bolu Okunaiya
 */
public class CountingSort extends EmpricalAnalyser {
    /**
     * @param args the command line arguments
     */
    private int[] mArray;
    private int mRange;
    private int[] mSorted;

    public CountingSort(int[] array, int range, SortPane pane) {
        super(pane);
        reset(array, range);
    }
    public void sort() {
        int[] count = new int[mRange + 1];
        for (int i = 0; i < mRange + 1; i++) {
            count[i] = 0;
        }
        for (int j = 0; j < mArray.length; j++) {
            count[mArray[j]] += 1;
        }
        for (int i = 1; i < mRange + 1; i++) {
            count[i] += count[i - 1];
        }
        int i = 0;
        for (i = mArray.length-1; i>=0 ; i--) {
            mCurrent = count[mArray[i]] - 1;
            pause();
            if (state ==0) return;
            mSorted[count[mArray[i]] - 1] = mArray[i];
            count[mArray[i]] -= 1;
            
        }
        pause();
    }

    @Override
    public void runAlgorithm() {
        sort();
    }

    @Override
    public void reset(int[] array, Object...args) {
        super.reset(array);
        mArray = Arrays.copyOf(array, array.length);
        mSorted = Arrays.copyOf(mArray, mArray.length);
        mTempArray = mSorted;
        mRange = (int) args[0];
    }

}
