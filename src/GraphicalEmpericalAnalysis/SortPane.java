/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalEmpericalAnalysis;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author kooldeji
 */
public class SortPane implements Initializable {
    private final Color NORMAL_COLOR = Color.CYAN;
    private final Color DISSECT_COLOR = Color.BLUE;
    private final Color STROKE_COLOR = Color.WHITE;
    private int[] mArray;
    private HBox mPane;
    private JFXTextField mRuntimeTF;
    private Label mTitle;
    private VBox mVBox;
    private AnchorPane mRoot;
    private int mHighest;
    private int dissect;
    final int[] dummy = {150, 120, 34, 95, 67, 45, 78, 93, 45, 90, 132, 190, 69, 89, 90, 98, 99, 78};
    private boolean mGraphic;
    private HBox mCntrlBox;
    private ArrayList<ChangeListener> heightListeners = new ArrayList<>();

    public SortPane(int [] list, boolean graphic, String[]...args) {
        
        try {
            FXMLLoader fXMLLoader = new FXMLLoader(getClass().getResource("SortPane.fxml"));
            mRoot = fXMLLoader.load();
            Controller c = fXMLLoader.getController();
            mPane = c.getPane();
            mPane.widthProperty().addListener((Observable e) -> {
                update();
            });
            mPane.heightProperty().addListener((Observable e) -> {
                update();
            });
            mRuntimeTF = c.getRuntime();
            mTitle = c.getTitle();
            mVBox = c.getVBox();
            setArray(list);
            setGraphic(graphic);
            mCntrlBox = c.getCntrlBox();
            for (String[] arg:args) {
                Label lb = new Label(arg[0]);
                lb.setGraphic(new JFXTextField(arg[1]));
                lb.setGraphicTextGap(5);
                lb.setContentDisplay(ContentDisplay.RIGHT);
                mCntrlBox.getChildren().add(lb);
            }
        } catch (Exception ex) {
            Logger.getLogger(SortPane.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public SortPane(boolean graphic) {
        this(new int[1], graphic);
    }

    public void setDissect(int dissect) {
        this.dissect = dissect;
    }
    
    public void setArray(int[] list) {
        mHighest = 0;
        mArray = new int[list.length];
        for (int i = 0; i < list.length; i++) {
            int nos = list[i];
            mArray[i] = nos;
            if (nos > mHighest) {
                mHighest = nos;
            }
        }
        update();
    }

    private void update() {
        if (mGraphic == true){
            mPane.getChildren().clear();
            for (ChangeListener l:heightListeners){
                mPane.heightProperty().removeListener(l);
            }
            heightListeners.clear();
            for (int i = 0; i < mArray.length; i++) {
                int no = mArray[i];
                VBox bar = new VBox();
                //bar.setStyle("-fx-border-color: black");
                //HBox.setHgrow(bar, Priority.SOMETIMES);
                bar.setAlignment(Pos.BOTTOM_LEFT);
                Label lb = new Label("" + no);
                //lb.setRotate(90);
                Rectangle rect = new Rectangle();
                ChangeListener e = (a, b, c) -> {
                    double h2 = (no * 1.0 / mHighest)*mPane.getHeight()-18;
                    if (h2<1) rect.setHeight(1);
                    else rect.setHeight(h2);
                };
                e.changed(null, null, null);
                heightListeners.add(e);
                mPane.heightProperty().addListener(e);
//                rect.heightProperty().bind(mPane.heightProperty().multiply(no * 1.0 / mHighest).subtract(18));
                rect.widthProperty().bind(mVBox.widthProperty().divide(mArray.length).subtract(4));
                if (i == dissect) {
                    rect.setFill(DISSECT_COLOR);
                } else {
                    rect.setFill(NORMAL_COLOR);
                }
                rect.setStroke(Color.BLACK);
                rect.setArcHeight(10);
                rect.setArcWidth(5);
                bar.getChildren().addAll(lb, rect);
                mPane.getChildren().add(bar);
            }
        }
    }

    public boolean isGraphic() {
        return mGraphic;
    }
    
    public AnchorPane getRoot() {
        return mRoot;
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setRuntime(double runtime) {
        if (runtime >= 1000000){
            runtime /= 1000000;
            if (runtime >= 1000){
                runtime /= 1000;
                mRuntimeTF.setText( runtime+ "s");
            }else mRuntimeTF.setText( runtime+ "ms");
        }else mRuntimeTF.setText(runtime + "ns");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    void setGraphic(boolean b) {
        mGraphic = b;
        ObservableList<Node> children = ((VBox) mRoot.getChildren().get(0)).getChildren();
        if (b == false && children.contains(mPane)) {
            children.remove(mPane);
        } else if (b == true && !children.contains(mPane)) {
            children.add(1, mPane);
        }
    }
    
}
