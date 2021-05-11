package repository;
import java.io.*;
import Models.*;

public class PatientRepositoryFile extends PatientRepositoryInMemory {
    private String fileName;
    private static int idGenerator=0;
    public PatientRepositoryFile(String str) {
        this.fileName = str;
        readFromFile();
    }
    private void readFromFile() {
        try (BufferedReader buffer = new BufferedReader(new FileReader(fileName))) {
            String line=buffer.readLine();

            try{
                idGenerator=Integer.parseInt(line);
            }catch (NumberFormatException ex){
                System.err.println("Invalid Value for idGenerator, starting from 0 ");
            }
            while((line = buffer.readLine()) != null) {
                String[] el = line.split(";");
                if (el.length != 5)
                {
                   System.err.println("Line is not valid!...:" + line);
                   continue;
                }

                try{

                    Patient p = new Patient(el[1], el[2], el[3], el[4]);
                    int id=Integer.parseInt(el[0]);
                    p.setID(id);
                    super.add(p);
                }
                catch(NumberFormatException exception){
                    System.err.println("Invalid id format!!" + el[0]);
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
            for (Patient p: findAll()){
                String s= p.getID() + ";" + p.getName()+ ";" +p.getSurname()+ ";"+p.getCnp() + ";" + p.getPhoneNumber();
                print.println(s);
            }
        }
        catch (IOException exception){throw new RepositoryException("Writing error"+ exception );}
    }

    @Override
    public void add(Patient p)
    {
        try{
            p.setID(getNextId());
            super.add(p);
        writeToFile();
        }
        catch(RuntimeException e)
        { throw new RepositoryException (e);}
    }

    @Override
    public void delete(Patient p)
    {
        try{
            super.delete(p);
            writeToFile();
        }
        catch(RuntimeException e){throw new RepositoryException(e);}

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
    private static int getNextId(){
        return idGenerator++;
    }
}




