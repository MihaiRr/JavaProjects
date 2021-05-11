package repository;
import java.io.*;
import Models.*;

public class AppointmentRepositoryFile extends AppointmentRepositoryInMemory {
    private String fileName;
    private PatientRepository patientRepository;
    private static int idGenerator=0;

    public AppointmentRepositoryFile(String str, PatientRepository patientRepository) {
        this.fileName = str;
        this.patientRepository=patientRepository;
        readFromFile();
    }

    private void readFromFile() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            String line=buffer.readLine();
            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator, starting from 0 app");
            }

            while((line = buffer.readLine()) != null) {
                String[] el = line.split(";");
                if (el.length != 5)
                {
                    System.err.println("Line is not valid!...:" + line);
                    continue;
                }

                try{
                    int id = Integer.parseInt(el[0]);
                    String[] str= el[1].split(" ");
                    int idP=Integer.parseInt(str[0]);
                    Patient p= patientRepository.findByID(idP);
                    Appointment a = new Appointment( p, el[2], el[3], el[4]);
                    a.setID(id);
                    super.add(a);
                }
                catch(NumberFormatException exception){
                    System.err.println("Invalid id format!..." + el[0]);
                }
            }
        }
        catch (IOException exception){
            throw new RepositoryException("File reading error" + exception);
        }
    }
    private void writeToFile(){
        try ( PrintWriter print= new PrintWriter(fileName)){
            print.println(idGenerator);
            for (Appointment a: findAll()){
                String s= a.getID() + ";" + a.getPatient()+ ";" + a.getHour() + ";" + a.getDate() + ";" +a.getProblem();
                print.println(s);
            }
        }
        catch (IOException exception){throw new RepositoryException("Writing error"+ exception );}
    }
    @Override
    public void add(Appointment a)
    {
        a.setID(getNextId());
        try{
            super.add(a);
            writeToFile();
        }
        catch(RuntimeException e)
        { throw new RepositoryException (e);}
    }

    @Override
    public void delete(Appointment a)
    {
        try{
            super.delete(a);
            writeToFile();
        }
        catch(RuntimeException e){throw new RepositoryException(e);}

    }

    @Override
    public void update(Appointment a, Integer id)
    {
        try{
            super.update(a, id);
            writeToFile();
        }
        catch(RuntimeException ex){throw new RepositoryException(ex);}
    }

    private static int getNextId(){
        return idGenerator++;
    }
}