public class Item {
    private String name;
    private String description;
    private int heal;


    public Item(String name, String description, int heal){
        this.name = name;
        this.description = description;
        this.heal = heal;
    }
    public String getname(){
        return name;
    }
    public String getdescription(){
        return description;
    }
    public int getheal(){
        return heal;
    }
    public String toString(){
        return "Name:" + this.name + " Description:" + this.description + "heal:" + this.heal;
    }

}
