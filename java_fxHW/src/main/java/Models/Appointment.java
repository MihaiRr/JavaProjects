package Models;

import java.io.Serializable;

public class Appointment implements Identifiable<Integer>, Serializable, Comparable<Appointment> {
    private Integer aid;
    private Patient patient;
    private String hour;
    private String date;
    private String problem;

    public Appointment(int ID, Patient p, String hour, String date, String problem){
        this.aid=ID;
        this.patient=p;
        this.hour=hour;
        this.date=date;
        this.problem=problem;
    }
    public Appointment( Patient p, String hour, String date, String problem){
        this.patient=p;
        this.hour=hour;
        this.date=date;
        this.problem=problem;
    }
    public Appointment(){
        this.aid=0;
        this.patient= new Patient();
        this.hour="";
        this.date="";
        this.problem="";
    }

    public Integer getID() {
        return aid;
    }

    public Patient getPatient() { return patient; }

    public String getHour() {
        return hour;
    }

    public String getDate() {
        return date;
    }

    public String getProblem() { return problem;}

    @Override
    public void setID(Integer id) {
        this.aid=id;
    }

    public void setPatient(Patient patient) { this.patient = patient;    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setProblem(String problem) { this.problem = problem; }

    @Override
    public String toString() {
        return "Appointment{" +
                "aid=" + aid +
                ", patient=" + patient +
                ", hour:" + hour +
                ", date:" + date +
                ", problem:" + problem +
                " }";
    }

    @Override
    public int compareTo(Appointment o) {
        return 0;
    }
}
