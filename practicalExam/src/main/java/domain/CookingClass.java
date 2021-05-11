package domain;

public class CookingClass implements Identifiable<Integer> {
    private int cID;
    private String name;
    private String type;
    private int price;
    private int maxPlaces;
    private String date;

    public CookingClass(int cID, String name, String type, int price, int maxPlaces, String date){
        this.cID=cID;
        this.name=name;
        this.type=type;
        this.price=price;
        this.maxPlaces=maxPlaces;
        this.date=date;
    }
    public CookingClass( String name, String type, int price, int maxPlaces, String date){
        this.name=name;
        this.type=type;
        this.price=price;
        this.maxPlaces=maxPlaces;
        this.date=date;
    }
    public CookingClass(){
        this.cID=0;
        this.name="";
        this.type="";
        this.price=0;
        this.maxPlaces=0;
        this.date="";
    }

    @Override
    public Integer getID() {return this.cID;}
    @Override
    public void setID(Integer id) {this.cID=id;}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public int getMaxPlaces() {
        return maxPlaces;
    }

    public void setMaxPlaces(int maxPlaces) {
        this.maxPlaces = maxPlaces;
    }

    @Override
    public String toString() {
        return
                 cID + " " +
                 name + " " +
                type + " " +
                 price + " " +
                 maxPlaces + " " +
                  date  ;

    }
}
