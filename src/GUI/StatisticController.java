/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package GUI;

import Entities.Equipe;
import Entities.PerformanceEquipe;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.sql.Connection;
import Utils.DataSource;



/**
 * FXML Controller class
 *
 * @author Admin
 */
public class StatisticController implements Initializable {

   PreparedStatement st = null;
    ResultSet rs = null;
    Connection con = null;
        private Stage stage;

   @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Button btnPrint;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          String select = "SELECT count(performanceequipe.id), performance_equipe.equipeId, equipe.nom from performance, equipe where equip.id = performance_equipe.equipeId group by equipeId";
    //     con =  new DataSource().getCnx();
         
        try {
            Statement s = con.createStatement();
            rs = s.executeQuery(select);
                              XYChart.Series dataSeries1 = new XYChart.Series();

            while (rs.next() ) {
                dataSeries1.getData().add(new XYChart.Data(rs.getString(3)  , rs.getInt(1)));

              
            }
                    xAxis.setMaxWidth(20);
                    barChart.setBarGap(5);

                            barChart.getData().add(dataSeries1);

      

// TODO
    }    catch (SQLException ex) {
            Logger.getLogger(AffichPerfEquipeController.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
          
    }
    
     public void chooseFile(Stage stage){
    this.stage = stage;
   }      

    @FXML
    private void print(ActionEvent event) {
                 PrinterJob job = PrinterJob.createPrinterJob();
    if(job != null){
                final Printer printer = job.getPrinter();

     job.showPrintDialog(this.stage); 
      final Paper paper = job.getJobSettings().getPageLayout().getPaper();
        final PageLayout pageLayout = printer.createPageLayout(paper,
                                                               PageOrientation.LANDSCAPE,
                                                               Printer.MarginType.DEFAULT);
        final double scaleX = pageLayout.getPrintableWidth() / 494.0 ;
        final double scaleY = pageLayout.getPrintableHeight() / 330 ;
             final double scale = Math.min(scaleX, scaleY);

     job.printPage(barChart);
     job.endJob();
 } 
    }
}