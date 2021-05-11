package repository;

import domain.CookingClass;
import domain.Subscription;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class SubscriptionRepositoryInFile extends SubscriptionRepositoryInMemory{
    private String fileName;
    private CookingClassRepository cookingClassRepository;
    private static int idGenerator=0;

    public SubscriptionRepositoryInFile(String str, CookingClassRepository cookingClassRepository){
        this.fileName=str;
        this.cookingClassRepository=cookingClassRepository;
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
                    int cID=Integer.parseInt(el[4]);
                    CookingClass cookingClass= cookingClassRepository.findByID(cID);
                    Subscription subscription =new Subscription(Integer.parseInt(el[0]),el[1], el[2], el[3], cookingClass);
                    int id=Integer.parseInt(el[0]);
                    subscription.setID(id);
                    super.add(subscription);
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
            for (Subscription s : findAll()){
                String str= s.getID() + ";" + s.getPersonName() + ";" + s.getPhoneNumber() + ";" + s.getAddress() + ";" + s.getCookingClass().getID();
                print.println(str);
            }
        }
        catch (IOException exception){throw new RepositoryException("Writing error"+ exception );}
    }

    @Override
    public void add(Subscription s)
    {
        try{
            s.setID(getNextId());
            super.add(s);
            writeToFile();
        }
        catch(RuntimeException e)
        { throw new RepositoryException (e);}
    }

    private static int getNextId(){
        return idGenerator++;
    }


}
