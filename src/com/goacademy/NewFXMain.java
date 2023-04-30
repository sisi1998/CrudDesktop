/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.goacademy;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ousse
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        //Parent root= FXMLLoader.load(getClass().getResource("CompetitionFront.fxml"));
        Parent root=FXMLLoader.load(getClass().getResource("/GUI/ManageCategoBack.fxml"));
        primaryStage.setTitle("GOAcademyGo");
        primaryStage.setScene(new Scene(root, 800,600));
        primaryStage.show();
    }
     
    public static void main(String[] args) {
        launch(args);
    }
    
}
