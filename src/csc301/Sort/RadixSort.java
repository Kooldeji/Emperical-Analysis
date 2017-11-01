/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc301.Sort;
import GraphicalEmpericalAnalysis.SortPane;
import csc301.EmpricalAnalyser;

/**
 *
 * @author kooldeji
 */
public class RadixSort extends EmpricalAnalyser {
    private int[] mArray;
    private int mRange;
    
    public RadixSort(int[] array, SortPane pane) {
        super(pane);
        reset(array);
    }
    
    public void sort(){
        for (int d=0; d<mRange; d++){
            if (state==0)break;
            countSort(d);
            if (state==0)return;
        }
    }

    private void countSort(int d) {
        int[] count = new int[10];
        int[] sorted = new int[mArray.length];
        mTempArray = sorted;
        for (int i = 0; i < 10; i++) {
            count[i] = 0;
        }
        for (int j = 0; j < mArray.length; j++) {
            count[get(mArray[j], d)] += 1;
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (int i = mArray.length-1; i>=0 ; i--) {
            int val = count[get(mArray[i], d)];
            mCurrent = val -1 ;
            pause();
            if (state ==0) return;
            sorted[val - 1] = mArray[i];
            count[get(mArray[i], d)] -= 1;
        }
        pause();
        mArray = sorted;
        
    }
    
    private int get(int nos, int i){
        int lent = (""+nos).length();
        if (lent-1<i) return 0;
        int x = Integer.parseInt(""+(""+nos).charAt(lent-1-i));
        return x;
    }

    @Override
    public void runAlgorithm() {
        sort();
    }
    
    @Override
    public void reset(int[] array, Object...args){
        super.reset(array, args);
        mArray = array;
        mTempArray = mArray;
        radix();
    }

    private void radix() {
        for (int nos:mArray){
            int lent = (""+nos).length();
            if (lent>mRange) mRange = lent;
        }
    }
    
    
}
