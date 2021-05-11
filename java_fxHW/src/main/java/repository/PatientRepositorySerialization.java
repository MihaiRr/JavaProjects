package repository;
import java.io.*;
import java.util.Map;

import Models.*;

public class PatientRepositorySerialization extends AbstractRepository<Integer, Patient>{
    private String fileName;
    public PatientRepositorySerialization(String str){
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
            this.M = (Map<Integer, Patient>) o.readObject();
        }

        catch (IOException | ClassNotFoundException e) {
            throw new RepositoryException(e);
        }

    }


    @Override
    public void add(Patient p)
    {
        try{
        super.add(p);
        writeToFile();
        }
        catch(RuntimeException e){
            throw new RepositoryException (e);}
    }

    @Override
    public void delete(Patient p)
    {
        try{
        super.delete(p);
        writeToFile();
        }
        catch(RuntimeException e){
            throw new RepositoryException(e);}

    }

    @Override
    public void update(Patient p, Integer id)
    {
        try{
            super.update(p, id);
            writeToFile();
        }
        catch(RuntimeException ex){throw new RepositoryException(ex);}
    }

}
