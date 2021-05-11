import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;


import repository.CookingClassRepository;
import repository.CookingClassRepositoryInFile;
import repository.SubscriptionRepositoryInFile;
import service.Service;
import service.ServiceException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("practic.fxml"));
            Parent root = loader.load();
            Controller ctrl = loader.getController();
            Service c=getService();
            ctrl.setService(c);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Practice Test");
            primaryStage.show();
        }catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText(""+e);
            System.out.println(e);
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    static Service getService() throws ServiceException {
        try {

            Properties properties = new Properties();
            properties.load(new FileInputStream("Project.properties"));
            String cookingClassRepositoryFile = properties.getProperty("CookingClassRepository");
            if (cookingClassRepositoryFile == null) {//the property does not exist in the file
                cookingClassRepositoryFile = "CookingClassRepository.txt";
                System.err.println("Requests file not found. Using default" + cookingClassRepositoryFile);
            }
            String subscriptionRepositoryFile = properties.getProperty("SubscriptionRepository");
            if (subscriptionRepositoryFile == null) {
                subscriptionRepositoryFile = "SubscriptionRepository.txt";
                System.err.println("RepairedForms file not found. Using default" + subscriptionRepositoryFile);
            }
            CookingClassRepository cookingClassRepository = new CookingClassRepositoryInFile(subscriptionRepositoryFile);

            SubscriptionRepositoryInFile subscriptionRepositoryInFile = new SubscriptionRepositoryInFile(cookingClassRepositoryFile, cookingClassRepository );
            return new Service(  cookingClassRepository, subscriptionRepositoryInFile );
        } catch (IOException ex) {
            System.err.println("Error reading the configuration file" + ex);
        }

        return null;

    }
}