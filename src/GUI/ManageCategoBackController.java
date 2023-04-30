package GUI;


import Entities.Categorie;

import Services.CategorieService;
import Utils.AlertUtils;
import Utils.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class ManageCategoBackController implements Initializable {

    @FXML
    public TextField nomTF;
    @FXML
    public Button btnAjout;
    @FXML
    public Text topText;

    Categorie currentCategorie;


    @Override
    public void initialize(URL url, ResourceBundle rb) {

        currentCategorie = ShowAllCategoBackController.currentCategorie;

        if (currentCategorie != null) {
            topText.setText("Modifier categorie");
            btnAjout.setText("Modifier");

            try {
                nomTF.setText(currentCategorie.getNom());

            } catch (NullPointerException ignored) {
                System.out.println("NullPointerException");
            }
        } else {
            topText.setText("Ajouter categorie");
            btnAjout.setText("Ajouter");
        }
    }

    @FXML
    private void manage(ActionEvent event) {

        if (controleDeSaisie()) {

            Categorie categorie = new Categorie(
                    nomTF.getText()
            );

            if (currentCategorie == null) {
                if (CategorieService.getInstance().add(categorie)) {
                    AlertUtils.makeSuccessNotification("Categorie ajouté avec succés");
                    MainWindowBackController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_CATEGORIE);
                } else {
                    AlertUtils.makeError("Error");
                }
            } else {
                categorie.setId(currentCategorie.getId());
                if (CategorieService.getInstance().edit(categorie)) {
                    AlertUtils.makeSuccessNotification("Categorie modifié avec succés");
                    ShowAllCategoBackController.currentCategorie = null;
                    MainWindowBackController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_CATEGORIE);
                } else {
                    AlertUtils.makeError("Error");
                }
            }

        }
    }


    private boolean controleDeSaisie() {


        if (nomTF.getText().isEmpty()) {
            AlertUtils.makeInformation("nom ne doit pas etre vide");
            return false;
        }


        return true;
    }
}