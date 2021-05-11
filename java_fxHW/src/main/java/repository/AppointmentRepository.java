package repository;

import Models.Appointment;

import java.util.List;

public interface AppointmentRepository extends Repository<Integer, Appointment>{
    List<Appointment> byDate(String date);
}
