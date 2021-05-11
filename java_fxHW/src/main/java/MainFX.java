import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import controller.Controller;
import controller.ControllerException;
import repository.*;
import service.DentistAppointmentService;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("example1.fxml"));
            Parent root = loader.load();
            DentistAppointmentService ctrl = loader.getController();
            Controller c=getService();
            ctrl.setService(c);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("homework");
            primaryStage.show();
        }catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText(""+e);
            alert.showAndWait();
        }
    }
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("example1.fxml"));
//            Parent root = loader.load();
//            DentistAppointmentService ctrl = loader.getController();
//            ctrl.setService(getService());
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.setTitle("Hello");
//            primaryStage.show();
//        }catch(Exception e){
//            Alert alert=new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error ");
//            alert.setContentText("Error while starting app "+e);
//            alert.showAndWait();
//        }
//    }

    public static void main(String[] args) {
        launch(args);
    }
    static Controller getService() throws ControllerException {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("DentistApp.properties"));
            String patientFileName = properties.getProperty("PatientFile");
            if (patientFileName == null) {//the property does not exist in the file
                patientFileName = "fileRP.txt";
                System.err.println("Requests file not found. Using default" + patientFileName);
            }
            String AppointmentFileName = properties.getProperty("AppointmentFile");
            if (AppointmentFileName == null) {
                AppointmentFileName = "fileApp.txt";
                System.err.println("RepairedForms file not found. Using default" + AppointmentFileName);
            }
            PatientRepository patientRepo = new PatientRepositoryFile(patientFileName);

            AppointmentRepositoryFile appRepo = new AppointmentRepositoryFile(AppointmentFileName, patientRepo);
            return new Controller(patientRepo, appRepo);
        } catch (IOException ex) {
            System.err.println("Error reading the configuration file" + ex);
        }

        return null;

    }

}