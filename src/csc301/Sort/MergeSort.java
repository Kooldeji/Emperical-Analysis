/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc301.Sort;
import GraphicalEmpericalAnalysis.SortPane;
import com.sun.glass.ui.View;
import csc301.EmpricalAnalyser;
import java.util.Arrays;
/**
 *
 * @author kooldeji
 */
public class MergeSort extends EmpricalAnalyser{
    private int[] mArray;
    public MergeSort(int[] array, SortPane pane) {
        super(pane);
        reset(array);
    }
    public void sort(int p, int r){
        
        if(p < r){
            int q = Math.floorDiv(p+r, 2);
            sort(p, q);
            if (state==0)return;
            sort(q+1, r);
            if (state==0)return;
            merge(p, q, r);
            
        }
}

    public void merge(int p, int q, int r)
    {
        int n1 = q-p+1;
        int n2 = r - q;
        int[] lA = new int[n1+1];
        int[] rA = new int[n2+1];
        for (int i=0; i<n1; i++){
            lA[i] = mArray[p+i];
        }
        for (int i=0; i<n2; i++){
            rA[i] = mArray[q+1+i];
        }
 
        lA[n1] = (int) Double.POSITIVE_INFINITY;
        rA[n2] = (int) Double.POSITIVE_INFINITY;
        int i =0;
        int j=0;
        for (int k=p; k<=r; k++){
            if (lA[i]<=rA[j]){
                mArray[k] = lA[i];
                i++;
            }else{
                mArray[k]=rA[j];
                j++;
            }
        }
        mCurrent = r;
        pause();
        if (state==0)return;
        
    }

    @Override
    public void runAlgorithm() {
        sort(0, mArray.length-1);
    }

    @Override
    public void reset(int[] array, Object...args) {
        super.reset(array);
        mArray = Arrays.copyOf(array, array.length);
        mTempArray = mArray;
    }
}


