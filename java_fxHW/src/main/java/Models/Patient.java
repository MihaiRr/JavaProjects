package Models;

import java.io.Serializable;

public class Patient implements Identifiable<Integer>, Serializable, Comparable<Patient> {
    private Integer pid;
    private String name;
    private String surname;
    private String phoneNumber;
    private String cnp;


    public Patient(int id,String name,String surname, String cnp, String phoneNumber){
        this.pid=id;
        this.name=name;
        this.surname=surname;
        this.cnp=cnp;
        this.phoneNumber=phoneNumber;
    }

    public Patient(String name,String surname, String cnp, String phoneNumber){
        this.name=name;
        this.surname=surname;
        this.cnp=cnp;
        this.phoneNumber=phoneNumber;
    }

    public Patient(){
        this.pid=0;
        this.name="";
        this.surname="";
        this.cnp="";
        this.phoneNumber="";
    }
    @Override
    public Integer getID() {
        return pid;
    }
    @Override
    public void setID(Integer ID){
        this.pid=ID;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getCnp() { return cnp; }
    public String getPhoneNumber() {return phoneNumber;}

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setCnp(String cnp) { this.cnp = cnp; }

    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    @Override
    public String toString()
    {
        return (this.pid + " " + this.name + " " + this.surname);
    }

    @Override
    public int compareTo(Patient o) {
       return 0;
    }
}
