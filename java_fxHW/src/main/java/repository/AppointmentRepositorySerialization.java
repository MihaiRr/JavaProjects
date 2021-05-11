package repository;
import java.io.*;
import java.util.Map;

import Models.*;

public class AppointmentRepositorySerialization extends AbstractRepository<Integer, Appointment>{
    private String fileName;
    public AppointmentRepositorySerialization(String str){
        this.fileName = str;
        writeToFile();
        readFromFile();
    }
    private void writeToFile() {
        try(ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(this.fileName))) {
            o.writeObject(this.M);
        }
        catch(IOException e) {
            throw new RepositoryException(e);
        }
    }

    private void readFromFile() {
        try (ObjectInputStream o = new ObjectInputStream( new FileInputStream(this.fileName))) {
            this.M = (Map<Integer, Appointment>) o.readObject();
        }

        catch (IOException | ClassNotFoundException e) {
            throw new RepositoryException(e);
        }

    }


    @Override
    public void add(Appointment a)
    {
        try{
            super.add(a);
            writeToFile();
        }
        catch(RuntimeException e){
            throw new RepositoryException (e);}
    }

    @Override
    public void delete(Appointment a)
    {
        try{
            super.delete(a);
            writeToFile();
        }
        catch(RuntimeException e){
            throw new RepositoryException(e);}

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

}