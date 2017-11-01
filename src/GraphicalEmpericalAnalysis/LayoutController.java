/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalEmpericalAnalysis;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import csc301.EmpricalAnalyser;
import csc301.Sort.BucketSort;
import csc301.Sort.CountingSort;
import csc301.Sort.MergeSort;
import csc301.Sort.QuickSort;
import csc301.Sort.RadixSort;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author kooldeji
 */
public class LayoutController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private final int SIMULATION_SIZE = 1000;
    private final int MINIMUM_SIZE = 10;
    private final int PEAK = 200;
    private final int FLOOR = 0;
    private final int DELAY = 50;
    
    private int[] mArray;
    private ArrayList<SortPane> sortPanes = new ArrayList();
    private ArrayList<EmpricalAnalyser> algos;
    private ArrayList<EmpricalAnalyser> activeAlgo;
    private ExecutorService executor;
    
    @FXML
    private FlowPane SMainPane;
    @FXML
    private JFXButton sResetBtn;
    @FXML
    private VBox SSideBar;
    @FXML
    private JFXButton sSolveBtn;

    @FXML
    private AnchorPane apane;

    @FXML
    private VBox vpane;

    @FXML
    private JFXComboBox<String> stateCb;

    @FXML
    private JFXTextField sizeTb;

    @FXML
    private JFXTextField peakTf;
    
    @FXML
    private JFXToggleButton graphicTg;

    @FXML
    private JFXTextField simSpeedTf;

    public LayoutController() {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        sSolveBtn.setOnMouseClicked(e -> {
            if (executor != null && !executor.isTerminated()) executor.shutdownNow();
            for (EmpricalAnalyser algo : algos){
                algo.reset(mArray, Integer.parseInt(peakTf.getText()));
            }
            sort();
        });
        sResetBtn.setOnMouseClicked(e ->{
            if (executor != null && !executor.isTerminated()) {
                executor.shutdownNow();
                try {
                    executor.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
                } catch (InterruptedException ex) {
                    Logger.getLogger(LayoutController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            for (EmpricalAnalyser algo : algos){
                algo.reset(mArray, Integer.parseInt(peakTf.getText()));
            }
        });
        simSpeedTf.textProperty().addListener(e ->{
            EmpricalAnalyser.sim_speed = Integer.parseInt(simSpeedTf.getText());
        });
        simSpeedTf.setText(""+DELAY);
        peakTf.setText(Integer.toString(PEAK));
        peakTf.focusedProperty().addListener((ObservableValue<? extends Boolean> e, Boolean o, Boolean n) ->{
            if (n==true) return;
            setPeak();
        });
        peakTf.setOnKeyPressed((KeyEvent e) ->{
            if (e.getCode()==KeyCode.ENTER) SMainPane.requestFocus();
        });
        sortPanes = new ArrayList<>();
        activeAlgo = new ArrayList<>();
        algos = new ArrayList<>();
        stateCb.getItems().addAll( "Unsorted", "Sorted", "Reverse");
        stateCb.getSelectionModel().select(0);
        sizeTb.setText(""+MINIMUM_SIZE);
        sizeTb.focusedProperty().addListener((ObservableValue<? extends Boolean> e, Boolean o, Boolean n) ->{
            if (n==false)setSize();
        });
        sizeTb.setOnKeyPressed((KeyEvent e) ->{
            if (e.getCode()==KeyCode.ENTER) SMainPane.requestFocus();
        });
        stateCb.selectionModelProperty().get().selectedItemProperty().addListener(e -> {
            generateArray();
        });
        graphicTg.selectedProperty().addListener(e -> {
            if (!graphicTg.isSelected()) {
                simSpeedTf.setText(""+0);
                simSpeedTf.setDisable(true);
            }else simSpeedTf.setDisable(false);
            
            for (SortPane pn:sortPanes){
                pn.setGraphic(graphicTg.isSelected());
            }
        });
        SMainPane.getChildren().addListener(new ListChangeListener<Node>() {
            @Override   
            public void onChanged(ListChangeListener.Change<? extends Node> c) {
                refresh();
            }
        });
        SMainPane.widthProperty().addListener(e ->{
            refresh();
        });
        SMainPane.heightProperty().addListener(e ->{
            refresh();
        });
        generateArray();
        SortPane pane;
        pane = new SortPane(mArray, graphicTg.isSelected());
        addSortOption("Merge Sort", new MergeSort(mArray, pane), pane);
        pane = new SortPane(mArray, graphicTg.isSelected());
        addSortOption("Quick Sort", new QuickSort(mArray, pane), pane);
        pane = new SortPane(mArray, graphicTg.isSelected());
        addSortOption("Counting Sort", new CountingSort(mArray, Integer.parseInt(peakTf.getText()), pane), pane);
        pane = new SortPane(mArray, graphicTg.isSelected());
        addSortOption("Bucket Sort", new BucketSort(mArray, pane), pane);
        pane = new SortPane(mArray, graphicTg.isSelected());
        addSortOption("Radix Sort", new RadixSort(mArray, pane), pane);
    }
    public void setSize(){
        int p = Integer.parseInt(peakTf.getText());
        int n = Integer.parseInt(sizeTb.getText());
        if (n<MINIMUM_SIZE) sizeTb.setText(""+MINIMUM_SIZE);
        generateArray();        
    }
    public void setPeak(){
        int p = Integer.parseInt(peakTf.getText());
        int n = Integer.parseInt(sizeTb.getText());
        if (p<FLOOR) peakTf.setText(""+FLOOR);
        generateArray();        
    }
    public void sort(){ 
        
        //Create an Executor
        System.out.println(activeAlgo.size());
        executor = Executors.newFixedThreadPool(activeAlgo.size());
        
        //Execute the runnables
        for (EmpricalAnalyser algo : activeAlgo){
            executor.execute(algo);
        }
        executor.shutdown();
    }
    void refresh(){
        for (Node pane : SMainPane.getChildren()){
            AnchorPane anchorPane = (AnchorPane) pane;
            anchorPane.prefWidthProperty().bind(SMainPane.widthProperty().subtract(20).divide(Math.ceil(SMainPane.getChildren().size()*1.0/2)) );
            anchorPane.prefHeightProperty().bind(SMainPane.heightProperty().subtract(20).divide((SMainPane.getChildren().size()<2)?1:2));
        }
    }
    
    void addSortOption(String title, EmpricalAnalyser algo, SortPane pane){
        Label option = new Label(title);
        JFXToggleButton selectOption = new JFXToggleButton();
        option.setGraphic(selectOption);
        pane.setTitle(title);
        algos.add(algo);
        selectOption.selectedProperty().addListener(e->{
            if (selectOption.isSelected()) {
                SMainPane.getChildren().add(pane.getRoot());
                activeAlgo.add(algo);
            }
            else {
                SMainPane.getChildren().remove(pane.getRoot());
                activeAlgo.remove(algo);
            }

        });
        SSideBar.getChildren().add(option);
        sortPanes.add(pane);
    }

    private void generateArray() {
       int size = Integer.parseInt(sizeTb.getText());
       String state = stateCb.getSelectionModel().getSelectedItem();
        
        if (size>SIMULATION_SIZE&&!graphicTg.isDisabled()){
            graphicTg.setSelected(false);
            graphicTg.setDisable(true);
        }else if (size<=SIMULATION_SIZE&&graphicTg.isDisabled()){
            if (graphicTg.isDisabled()) {
                graphicTg.setDisable(false);
                graphicTg.setSelected(true);
            }
        }
        mArray = new int[size];
        int peak = Integer.parseInt(peakTf.getText());
        int r = (peak-FLOOR)/size;
        r = (r<=0)?1:r;
        int c = (int)Math.ceil(size*1.0/(peak-FLOOR));
        int prev;
        switch (state){
            case "Sorted":
                prev = FLOOR;
                for (int i=0; i<size; i++){
                    mArray[i]=randRange(prev,FLOOR+r*(i/c+1));
                    prev = mArray[i];
                }
                break;
            case "Unsorted":
                for (int i=0; i<size; i++){
                    mArray[i]=randRange(FLOOR,peak);
                }
                break;
            case "Reverse":
                prev = FLOOR;
                for (int i=size-1; i>=0; i--){
                    mArray[i]=randRange(prev,FLOOR+r*(size-i)/c);
                    prev = mArray[i];
                }
                break;                
                
        }
        for (EmpricalAnalyser algo : algos){
            algo.reset(mArray, Integer.parseInt(peakTf.getText()));
        }
    }
    
    private static int randRange(int lower, int higher){
        return lower+(int)(Math.random()*(higher+1-lower));
    }
    
}
