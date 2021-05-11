package repository;
import Models.Patient;

import java.util.List;
import java.util.stream.Collectors;

public class PatientRepositoryInMemory extends AbstractRepository<Integer, Patient> implements PatientRepository{
    public PatientRepositoryInMemory(){}

    public List<Patient> filter(){
        return getAll().stream().collect(Collectors.toList());
    }


}
