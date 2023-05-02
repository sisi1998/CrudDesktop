/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package GUI;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Ousse
 */
public class mainGui extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
      Parent root=FXMLLoader.load(getClass().getResource("login.fxml"));
    // Parent root=FXMLLoader.load(getClass().getResource("motDePasseOublie1.fxml"));
     //  Parent root=FXMLLoader.load(getClass().getResource("ProfileUser.fxml"));
    //  Parent root=FXMLLoader.load(getClass().getResource("UserIndex.fxml"));
     
     
        
        Scene scene=new Scene(root);
      //  scene.getStylesheets().add(getClass().getResource("balha.css").toExternalForm());
      //  String css= "getClass().getResource(\"balha.css\").toExternalForm()";
      //  scene.getStylesheets().add(css);
        primaryStage.setTitle("GoAcademy");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
