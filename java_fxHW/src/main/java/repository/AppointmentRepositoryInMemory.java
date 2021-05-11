package repository;
import Models.Appointment;
import Models.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class AppointmentRepositoryInMemory extends AbstractRepository<Integer, Appointment> implements AppointmentRepository {
    public AppointmentRepositoryInMemory() {
    }

    @Override
    public List<Appointment> byDate(String date){
        return getAll().stream().filter(x->x.getDate().equals(date)).collect(Collectors.toList());
    }


}
