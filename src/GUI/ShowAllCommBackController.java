package GUI;

import Entities.Commande;

import Services.CommandeService;
import Utils.AlertUtils;
import Utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ShowAllCommBackController implements Initializable {

    public static Commande currentCommande;

    @FXML
    public Text topText;
    @FXML
    public VBox mainVBox;


    List<Commande> listCommande;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listCommande = CommandeService.getInstance().getAll();

        displayData();
    }

    void displayData() {
        mainVBox.getChildren().clear();

        Collections.reverse(listCommande);

        if (!listCommande.isEmpty()) {
            for (Commande commande : listCommande) {
                mainVBox.getChildren().add(makeCommandeModel(commande));
            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeCommandeModel(
            Commande commande
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_BACK_MODEL_COMMANDE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#produitText")).setText("Produit : " + commande.getProduit());
            ((Text) innerContainer.lookup("#nombreProduitText")).setText("NombreProduit : " + commande.getNombreProduit());
            ((Text) innerContainer.lookup("#prixTotalText")).setText("PrixTotal : " + commande.getPrixTotal());


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }
}
