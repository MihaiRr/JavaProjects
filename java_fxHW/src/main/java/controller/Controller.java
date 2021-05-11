package controller;
import Models.*;
import repository.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Controller {
    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;
    public Controller(PatientRepository patientRepository, AppointmentRepository appointmentRepository){
        this.appointmentRepository=appointmentRepository;
        this.patientRepository=patientRepository;
    }

    public List<Appointment> filterByDate(String date){
        return appointmentRepository.byDate(date);

    }

    public int addPatientController( String name, String surname, String cnp, String phoneNumber) throws ControllerException {
        try {
            Patient p = new Patient(name, surname, cnp, phoneNumber);
            patientRepository.add(p);
            return p.getID();
        } catch (RepositoryException ex) {
            throw new ControllerException("Error adding patient" + ex);
        }
    }
    public int addAppointmentController(int patientId, String hour, String date, String problem) throws ControllerException {
        try {
            Patient p = new Patient();
            for (Patient elem : patientRepository.findAll()) {
                if (elem.getID() == patientId) {
                    p = elem;
                }
            }
            Appointment ap = new Appointment(p, hour, date, problem);
            appointmentRepository.add(ap);
            return ap.getID();


        }catch (RepositoryException ex) {
            throw new ControllerException("Error adding appointment"+ex);

        }
    }
    public void deletePatientController(int id){
        Patient elem= patientRepository.findByID(id);
        patientRepository.delete(elem);

    }

    public void deletePatientController(Patient p) throws ControllerException {

        try{
            patientRepository.delete(p);
        }catch (RepositoryException ex) {
            throw new ControllerException("Error deleting patient" + ex);
        }


    }

    public void deleteAppointmentController(int id) throws ControllerException {
        try {
            Appointment elem = appointmentRepository.findByID(id);

            appointmentRepository.delete(elem);
        }catch (RepositoryException ex) {
            throw new ControllerException("error deleting appointment"+ex);
        }
    }
    public void updatePatientController(int newId, String newName, String newSurname, String newCnp, String newPhoneNumber, int oldID){

        patientRepository.update(new Patient(newId, newName, newSurname, newCnp, newPhoneNumber), oldID);

    }
    public void updatePatientController( String newName, String newSurname, String newCnp, String newPhoneNumber, int oldID) throws ControllerException {
        try {
            patientRepository.update(new Patient(oldID, newName, newSurname, newCnp, newPhoneNumber), oldID);
        }catch (RepositoryException ex) {
            throw new ControllerException("Error updating patient" + ex);
        }
    }

    public void updateAppointmentController(int newID, int patientID, String hour, String date,String problem, int oldID) throws ControllerException {
        try {
            Patient p = patientRepository.findByID(patientID);

            appointmentRepository.update(new Appointment(newID, p, hour, date, problem), oldID);
        }catch (RepositoryException ex){
            throw new ControllerException("" +ex);
        }
    }

    public Iterable<Patient> printAllPatient(){
        return patientRepository.findAll();
    }
    public List<Patient> getList(){
        return patientRepository.getAll().stream().collect(Collectors.toList());
    }
    public Iterable<Appointment> printAllAppointments(){
        return  appointmentRepository.findAll();
    }

//    public void records(){
//        try {
//            System.out.println("input:");
//            InputStreamReader streamReader = new InputStreamReader(System.in);
//            BufferedReader bufferedReader = new BufferedReader(streamReader);
//            Scanner in = new Scanner(System.in);
//            int record = in.nextInt();
//            if (record == 1) {
//                patientRepository.getAll().stream().forEach(System.out::println);
//            } else {
//                if (record == 2) {
//                    appointmentRepository.getAll().stream().forEach(System.out::println);
//                } else {
//                    if (record == 3) {
//                        System.out.println("Patient name:");
//                        String name = bufferedReader.readLine();
//                        patientRepository.getAll().stream().filter(patient -> {
//                            return patient.getName().equals(name);
//                        }).forEach(System.out::println);
//                    } else {
//                        if (record == 4) {
//                            System.out.println("input date");
//                            String date = bufferedReader.readLine();
//                            appointmentRepository.getAll().stream().filter(appointment -> {
//                                return appointment.getDate().equals(date);
//                            }).forEach(System.out::println);
//                        } else {
//                            if (record == 5) {
//                                patientRepository.getAll().stream().sorted().forEach(System.out::println);
//                            }
//                            else {
//                                if (record == 6) {
//                                    appointmentRepository.getAll().stream().sorted().forEach(System.out::println);
//                                }
//
//                            }
//                        }
//                    }
//
//
//                }
//            }
//        }

//        catch (IOException ex){
//            throw new RuntimeException("something went wrong");
//        }
//    }
}
