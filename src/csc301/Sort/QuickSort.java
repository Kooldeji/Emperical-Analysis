package csc301.Sort;

import GraphicalEmpericalAnalysis.SortPane;
import csc301.EmpricalAnalyser;
import java.util.Arrays;

  public class QuickSort extends EmpricalAnalyser{
    private int[] mArray;

    public QuickSort(int[] array, SortPane pane) {
        super(pane);
        reset(array);
    }
    public static void quicksort() {
    }
    private void quicksort(int first, int last){
        if (last > first) {
            int pivotIndex = partition(first, last);
            mCurrent = pivotIndex;
            pause();
            if (state==0)return;
            quicksort(first, pivotIndex - 1);
            if (state==0)return;
            quicksort(pivotIndex + 1, last);
        }
    }
    private int partition(int first, int last){
        int pivot = mArray[first];
        int low = first + 1;
        int high = last;
        while (high >low){
            while (low <= high && mArray[low] <= pivot)
                low++;
            while(low <= high && mArray[high] > pivot)
                high--;
            if (high > low){
                int temp = mArray[high];
                mArray[high] = mArray[low];
                mArray[low] = temp;
            }
        }
        while (high > first && mArray[high] >= pivot)
            high--;
        if(pivot > mArray[high]){
            mArray[first] = mArray[high];
            mArray[high] = pivot;
            return high;
        }
        else{
            return first;
        }
    }

    @Override
    public void runAlgorithm() {
         quicksort(0, mArray.length - 1);
    }

    @Override
    public void reset(int[] array, Object...args) {
        super.reset(array);
        mArray = Arrays.copyOf(array, array.length);
        mTempArray = mArray;
        
        
    }
}
























