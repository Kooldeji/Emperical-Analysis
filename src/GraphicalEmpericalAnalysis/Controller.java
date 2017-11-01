/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GraphicalEmpericalAnalysis;


import com.jfoenix.controls.JFXTextField;
import javafx.scene.shape.Rectangle;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Controller implements Initializable{
        
    @FXML
    private HBox mPane;
    @FXML
    private JFXTextField mRuntimeTF;
    @FXML
    private Label mTitle;
    @FXML
    private VBox mVBox;
    @FXML
    private HBox controlPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mPane.prefWidthProperty().bind(mVBox.widthProperty());
    }

    public HBox getPane() {
        System.out.println(mPane);
        return mPane;
    }

    public JFXTextField getRuntime() {
        return mRuntimeTF;
    }

    public Label getTitle() {
        return mTitle;
    }

    public VBox getVBox() {
        return mVBox;
    }
        
    public HBox getCntrlBox() {
        return controlPane;
    }
        
        
        
    }

