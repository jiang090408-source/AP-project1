public class Weapon {


    String name;
    int atk;
    int durability;


    public Weapon(String name, int atk, int durability) {
        this.name=name;
        this.atk=atk;
        this.durability = durability;
    }
    public String getName() {
        return name;
    }
    public int getAtk() {
        return atk;
    }
    public int getDurability() {
        return durability;
    }
    public static void useWeapon(Weapon weapon){
        weapon.durability--;
    }
    public String toString(){
        return "Weapon name: "+name+" Damage: "+atk+" Durability: "+durability;
    }

}
