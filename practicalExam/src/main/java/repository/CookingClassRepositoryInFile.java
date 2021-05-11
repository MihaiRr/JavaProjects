package repository;

import domain.CookingClass;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CookingClassRepositoryInFile extends CookingClassRepositoryInMemory{
    private String fileName;
    private static int idGenerator=0;
    public CookingClassRepositoryInFile(String str){
        this.fileName=str;
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
                if (el.length != 6)
                {
                    System.err.println("Line is not valid!...:" + line);
                    continue;
                }

                try{

                    CookingClass c =new CookingClass(Integer.parseInt(el[0]), el[1], el[2], Integer.parseInt(el[3]), Integer.parseInt(el[4]), el[5]);
                    int id=Integer.parseInt(el[0]);
                    c.setID(id);
                    super.add(c);
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
            for (CookingClass c : findAll()){
                String s= c.getID() + ";" + c.getName()+ ";" + c.getType()+ ";"+ c.getPrice()+";" +c.getMaxPlaces() + ";" + c.getDate();
                print.println(s);
            }
        }
        catch (IOException exception){throw new RepositoryException("Writing error"+ exception );}
    }

    @Override
    public void add(CookingClass c)
    {
        try{
            c.setID(getNextId());
            super.add(c);
            writeToFile();
        }
        catch(RuntimeException e)
        { throw new RepositoryException (e);}
    }

    private static int getNextId(){
        return idGenerator++;
    }
}
