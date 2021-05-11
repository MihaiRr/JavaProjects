package controller;

import domain.CookingClass;
import domain.Subscription;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.Service;
import service.ServiceException;


import java.time.format.DateTimeFormatter;
import java.util.List;

public class Controller {
    private Service service;
    public void setService(Service service) {this.service=service;}


    // for Cooking Class
    @FXML
    private TextField name, type, price, maxPlaces;
    @FXML
    private DatePicker date, filterDate;
    @FXML
    private TableView<CookingClass> cookingTable;

    // for Subscription

    @FXML
    private TextField personName, phoneNumber, address, cookingID, filterS;
    @FXML
    private TableView<Subscription> subscriptionTable;

    @FXML
    public void addCookingClassHandler(){
        String cName=name.getText();
        String cType=type.getText();
        int cPrice=Integer.parseInt(price.getText());
        int cMaxPlaces=Integer.parseInt(maxPlaces.getText());
        String cDate=date.getValue().format(dateFormatter);
        try {
            int id=service.addCookingClassController(cName,cType,cPrice,cMaxPlaces,cDate);
            showNotification("Cooking Class added succesfully", Alert.AlertType.INFORMATION);


        }catch(ServiceException ex){
            showNotification(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void addSubscriptionHandler(){
        String sPersonName=personName.getText();
        String sPhoneNumber=phoneNumber.getText();
        String sAddress=address.getText();
        int sCookingID=Integer.parseInt(cookingID.getText());
        CookingClass cookingClass=new CookingClass();
        for (CookingClass c:service.getAllCooking()){
            if (sCookingID==c.getID()){
                cookingClass=c;
            }
        }
        try {
            int id=service.addSubscriptionController(sPersonName,sPhoneNumber, sAddress, cookingClass);
            showNotification("Subscription added succesfully", Alert.AlertType.INFORMATION);


        }catch(ServiceException ex){
            showNotification(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void refreshButtonCListHandler(){
        cookingTable.getItems().clear();
        cookingTable.getItems().addAll(service.getAllCooking());

    }

    @FXML
    public void refreshButtonSListHandler(){
        subscriptionTable.getItems().clear();
        subscriptionTable.getItems().addAll(service.getAllSubscription());

    }

    @FXML
    public void filterSByClassHandler(){
        int oFilerSubs= Integer.parseInt(filterS.getText());
        try {
            CookingClass cookingClass=new CookingClass();
            for (CookingClass c:service.getAllCooking())
            {
                if (c.getID()==oFilerSubs){
                    cookingClass=c;
                }
            }
            List<Subscription> result=service.filterSByClass(cookingClass);
            subscriptionTable.getItems().clear();
            subscriptionTable.getItems().addAll(result);

        }catch (Exception e){
            showNotification(e.getMessage(), Alert.AlertType.WARNING);
        }

    }
    @FXML
    public void filterCByDateHandler(){
        String cDate=filterDate.getValue().format(dateFormatter);
        try {
            List<CookingClass> results=service.filterCByDate(cDate);
            cookingTable.getItems().clear();
            cookingTable.getItems().addAll(results);

        }catch (Exception ex){
            showNotification(ex.getMessage(), Alert.AlertType.WARNING);
        }
    }














    private boolean checkString(String s){
        return s != null && !s.isEmpty();
    }

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private void showNotification(String message, Alert.AlertType type){
        Alert alert=new Alert(type);
        alert.setTitle("Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
