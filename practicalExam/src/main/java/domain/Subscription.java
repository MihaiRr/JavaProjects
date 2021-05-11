package domain;

public class Subscription implements Identifiable<Integer>{
    private int sID;
    private String personName;
    private String phoneNumber;
    private String address;
    private CookingClass cookingClass;

    public Subscription(int sID, String personName, String phoneNumber, String address, CookingClass cookingClass){
        this.sID=sID;
        this.personName=personName;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.cookingClass=cookingClass;
    }

    public Subscription( String personName, String phoneNumber, String address, CookingClass cookingClass){
        this.personName=personName;
        this.phoneNumber=phoneNumber;
        this.address=address;
        this.cookingClass=cookingClass;
    }

    public Subscription(){
        this.sID=0;
        this.personName="";
        this.phoneNumber="";
        this.address="";
        this.cookingClass=new CookingClass();
    }

    @Override
    public Integer getID() {return this.sID;}
    @Override
    public void setID(Integer id) {this.sID=id;}

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public CookingClass getCookingClass() {
        return cookingClass;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCookingClass(CookingClass cookingClass) {
        this.cookingClass = cookingClass;
    }

    @Override
    public String toString() {
        return sID + " " + personName + " " +address + " " + phoneNumber + " " + cookingClass;
    }
}
