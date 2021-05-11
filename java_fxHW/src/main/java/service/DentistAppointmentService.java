package service;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import Models.Appointment;
import Models.Patient;
import controller.Controller;
import controller.ControllerException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DentistAppointmentService {
    @FXML
    private TextField name, surname, phoneNumber, cnp, update;
    @FXML
    private TableView<Patient> patientTableView;

    private Controller controller;

    public DentistAppointmentService() {}

    public void setService(Controller service){
        this.controller=service;
    }
    @FXML
    private TextField patientID, hour, problem, deleteID;
    @FXML
    private TableView<Appointment> appointmentTableView;
    @FXML
    private DatePicker date, date_search;
    @FXML
    public void addAppointmentHandler(ActionEvent actionEvent) {
        int id=Integer.parseInt(patientID.getText());
        String aHour=hour.getText();
        String aDate=date.getValue().format(dateFormatter);
        String aProblem=problem.getText();

        if (checkString(aHour)&&checkString(aDate)&&checkString(aProblem)){
            try {
                Patient p=new Patient();
                for (Patient elem: controller.printAllPatient()){
                    if (elem.getID()==id){
                        p=elem;
                    }
                }
                int pid = controller.addAppointmentController(id,aHour, aDate,aProblem);
                showNotification("Appointment successfully added! ", Alert.AlertType.INFORMATION);
            }catch(ControllerException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
        else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }
    @FXML
    public void addPatientHandler(ActionEvent actionEvent) {
        String pName= name.getText();
        String pSurname=surname.getText();
        String phone=phoneNumber.getText();
        String pCnp=cnp.getText();

        if (checkString(pName)&&checkString(pSurname)&&checkString(phone)&&checkString(pCnp)){
            try {
                int id = controller.addPatientController( pName, pSurname, pCnp, phone);
                showNotification("Patient successfully added! ", Alert.AlertType.INFORMATION);
            }catch(ControllerException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
        else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }
    @FXML
    public void addPatient(ActionEvent actionEvent) {

        List<Patient> results=controller.getList();
        patientTableView.getItems().clear();
        patientTableView.getItems().addAll(results);

    }
    @FXML
    public void deletePatientHandler(ActionEvent actionEvent)
    {
        String pName= name.getText();
        String pSurname=surname.getText();
        String phone=phoneNumber.getText();
        String pCnp=cnp.getText();

        if (checkString(pName)&&checkString(pSurname)&&checkString(phone)&&checkString(pCnp)){
            try {
                Patient p=new Patient();
                for (Patient elem: controller.printAllPatient()){
                    if (elem.getCnp().equals(pCnp)){
                        p=elem;
                    }
                }

                controller.deletePatientController(p);
                showNotification("Patient successfully deleted! ", Alert.AlertType.INFORMATION);
            }catch(ControllerException ex){
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
        else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }
    @FXML
    public void deleteAppointmentHandler(ActionEvent actionEvent) {
        int apID = Integer.parseInt(deleteID.getText());


        try {


            controller.deleteAppointmentController(apID);
            showNotification("Appointment successfully deleted! ", Alert.AlertType.INFORMATION);
        } catch (ControllerException ex) {
            showNotification(ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void updatePatientHandler(ActionEvent actionEvent) {
        String pName = name.getText();
        String pSurname = surname.getText();
        String phone = phoneNumber.getText();
        String pCnp = cnp.getText();
        int id=Integer.parseInt(update.getText());
        if (checkString(pName) && checkString(pSurname) && checkString(phone) && checkString(pCnp)) {
            try {


                controller.updatePatientController(pName, pSurname, pCnp, phone, id );
                showNotification("Patient successfully updated! ", Alert.AlertType.INFORMATION);
            } catch (ControllerException ex) {
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        } else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }
    @FXML
    public void updateAppointmentHandler(ActionEvent actionEvent) {

        int id=Integer.parseInt(deleteID.getText());
        int pid=Integer.parseInt(patientID.getText());
        String aHour=hour.getText();
        String aDate=date.getValue().format(dateFormatter);
        String aProblem=problem.getText();
        if (checkString(aHour)&&checkString(aDate)) {
            try {


                controller.updateAppointmentController(id, pid, aHour, aDate,aProblem,id);
                showNotification("Appointment successfully updated! ", Alert.AlertType.INFORMATION);
            } catch (ControllerException ex) {
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        } else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }



    @FXML
    public void clearFieldsHandler(ActionEvent actionEvent) {
        name.setText("");
        surname.setText("");
        phoneNumber.setText("");
        cnp.setText("");
    }

    private void showNotification(String message, Alert.AlertType type){
        Alert alert=new Alert(type);
        alert.setTitle("Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void searchRequestHandler(ActionEvent actionEvent) {
        String aDate=date_search.getValue().format(dateFormatter);
        if (!checkString(aDate)) {
            showNotification("You must introduce a date! ", Alert.AlertType.ERROR);
            return;
        }
        List<Appointment> results=controller.filterByDate(aDate);
        appointmentTableView.getItems().clear();
        appointmentTableView.getItems().addAll(results);

    }

    @FXML
    public void refreshButtonPatientHandler(ActionEvent actionEvent){
        patientTableView.getItems().clear();
        patientTableView.getItems().addAll(controller.getList());

    }

    @FXML
    public void autoFillPatientTableView(){
        try {
            Patient patient= patientTableView.getSelectionModel().getSelectedItem();
            patientID.setText(patient.getID().toString());


        }catch (Exception e){
            showNotification(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private boolean checkString(String s){
        return s==null || s.isEmpty()? false:true;
    }


}
