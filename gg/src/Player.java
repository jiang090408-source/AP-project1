public class Player {
    private String name;
    private int HP;
    private int maxHP;
    private int ATK;
    private int DEF;
    private Weapon myWeapon;
    private World myWorld;
    private Item myItem;
    private boolean main;



    public Player(String name, int maxHP, int ATK, int DEF, World world, boolean main) {
        this.name = name;
        this.maxHP = maxHP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.myWorld = world;
        this.HP = maxHP;
        this.main=main;
    }
    public int getatk(){
        return ATK;
    }
    public int gethp() {
        return HP;
    }
    public int getdef(){
        return DEF;
    }
    public int getmhp(){
        return maxHP;
    }
    public void sethp(int a){ HP=a;}

    public Weapon getMyWeapon() {
        return myWeapon;
    }

    public void setWeapon (Weapon weapon){
        this.myWeapon = weapon;
        this.ATK+= weapon.getAtk();
    }
    public void setItem (Item item){
        this.myItem = item;
    }
    public String getName(){
        return name;
    }
    public static void attack(Player a, Player b){
        int c = a.getatk();
        int d = b.getdef();
        b.HP-=(c-d);
        if (b.gethp() <0){b.sethp(0);}
        Weapon.useWeapon(a.getMyWeapon());
        System.out.println(a.getName()+" attacked "+b.getName()+" and dealt "+(c-d)+"damage! "+b.getName()+" now has "+b.HP+"HP!");
    }
    public void useItem(){
        this.HP+=this.myItem.getheal();
        this.myItem=null;
    }
    public boolean isAlive(){
        return HP>0;
    }
    public void equipWeapon(Weapon a){
        setWeapon(a);
    }
    public void giveItem(Item a){
            setItem(a);
        }

    public String toString(){
        return "Name: " + this.name + " | HP: " + this.HP + " | Weapon: " + this.myWeapon +" | Item: " + this.myItem;
    }

}
