package GUI;

import Entities.Categorie;
import Services.CategorieService;
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

public class ShowAllCategoBackController implements Initializable {

    public static Categorie currentCategorie;

    @FXML
    public Text topText;
    @FXML
    public Button addButton;
    @FXML
    public VBox mainVBox;


    List<Categorie> listCategorie;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listCategorie = CategorieService.getInstance().getAll();

        displayData();
    }

    void displayData() {
        mainVBox.getChildren().clear();

        Collections.reverse(listCategorie);

        if (!listCategorie.isEmpty()) {
            for (Categorie categorie : listCategorie) {

                mainVBox.getChildren().add(makeCategorieModel(categorie));

            }
        } else {
            StackPane stackPane = new StackPane();
            stackPane.setAlignment(Pos.CENTER);
            stackPane.setPrefHeight(200);
            stackPane.getChildren().add(new Text("Aucune donnée"));
            mainVBox.getChildren().add(stackPane);
        }
    }

    public Parent makeCategorieModel(
            Categorie categorie
    ) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(Constants.FXML_BACK_MODEL_CATEGORIE)));

            HBox innerContainer = ((HBox) ((AnchorPane) ((AnchorPane) parent).getChildren().get(0)).getChildren().get(0));
            ((Text) innerContainer.lookup("#nomText")).setText("Nom : " + categorie.getNom());


            ((Button) innerContainer.lookup("#editButton")).setOnAction((event) -> modifierCategorie(categorie));
            ((Button) innerContainer.lookup("#deleteButton")).setOnAction((event) -> supprimerCategorie(categorie));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return parent;
    }

    @FXML
    private void ajouterCategorie(ActionEvent event) {
        currentCategorie = null;
        MainWindowBackController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_CATEGORIE);
    }

    private void modifierCategorie(Categorie categorie) {
        currentCategorie = categorie;
        MainWindowBackController.getInstance().loadInterface(Constants.FXML_BACK_MANAGE_CATEGORIE);
    }

    private void supprimerCategorie(Categorie categorie) {
        currentCategorie = null;

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmer la suppression");
        alert.setHeaderText(null);
        alert.setContentText("Etes vous sûr de vouloir supprimer categorie ?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.isPresent()) {
            if (action.get() == ButtonType.OK) {
                if (CategorieService.getInstance().delete(categorie.getId())) {
                    MainWindowBackController.getInstance().loadInterface(Constants.FXML_BACK_DISPLAY_ALL_CATEGORIE);
                } else {
                    AlertUtils.makeError("Could not delete categorie");
                }
            }
        }
    }


}
