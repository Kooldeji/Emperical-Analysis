/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csc301;
import GraphicalEmpericalAnalysis.SortPane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;

/**
 *
 * @author akan
 */
public abstract class EmpricalAnalyser implements Runnable{
    private double startTime;
    private double runningTime;
    private SortPane mPane;
    protected int mCurrent;
    protected int[] mTempArray;
    public static int sim_speed = 50;
    protected int state = 1;
    public EmpricalAnalyser(SortPane pane){
        mPane = pane;
    }
    public double getRunTime(){
        return runningTime;
    }
    
    public void simulate(){
        startTime = System.nanoTime();
        runAlgorithm();
        runningTime += System.nanoTime()-startTime;
        Platform.runLater(() ->{
            mPane.setRuntime(runningTime);
        });
    }
    public void pause(){
        if (!mPane.isGraphic()) return;
        try {
            runningTime += System.nanoTime()-startTime;
            Platform.runLater(() ->{
               mPane.setArray(mTempArray);
               mPane.setDissect(mCurrent);
               mPane.setRuntime(runningTime);
            });
            Thread.sleep(sim_speed);
            startTime = System.nanoTime();
        } catch (InterruptedException ex) {
            state = 0;
        }
    }
    
    public abstract void runAlgorithm();
    
    public void reset(int [] array, Object...args){
        state = 1;
        runningTime = 0;
        mPane.setArray(array);
        mPane.setRuntime(runningTime);
    }

    @Override
    public void run() {
        if (state == 1) simulate();
        else return;
    }

}
