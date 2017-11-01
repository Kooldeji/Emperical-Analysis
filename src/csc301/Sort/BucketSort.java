package csc301.Sort;

import GraphicalEmpericalAnalysis.SortPane;
import csc301.EmpricalAnalyser;
import java.util.Arrays;
import java.util.Random;


public class BucketSort extends EmpricalAnalyser{
    private int[] mArray;
    private int maxValue;

    public BucketSort(int[] array, SortPane pane) {
        super(pane);
        reset(array);
    }
    public void sort(){
        int[]Bucket= new int[maxValue + 1];
        for (int i= 0; i<mArray.length; i++)
            Bucket[mArray[i]]++;
        int out_Pos= 0;
        for (int i=0; i<Bucket.length; i++)
            for (int j=0; j<Bucket[i]; j++){
                mArray[out_Pos++]=i;
                mCurrent = out_Pos;
                pause();
                if (state==0)return;
            }
    }
    public int maxValue(){
        int maxValue= 0;
        for (int i=0; i<mArray.length; i++)
            if (mArray[i]>maxValue)
                maxValue= mArray[i];
        return maxValue;
        
    }

    @Override
    public void runAlgorithm() {
        sort();
    }

    @Override
    public void reset(int[] array, Object...args) {
        super.reset(array);
        mArray = Arrays.copyOf(array, array.length);
        maxValue = maxValue();
        mTempArray = mArray;
        
    }
    public static void main(String[] args) {
     //   new Bucket_Sort(null).runAlgorithm();
    }
}
