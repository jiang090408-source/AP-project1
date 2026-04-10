public class Player {
    private String name;
    private int HP;
    private int maxHP;
    private int ATK;
    private int DEF;
    private Weapon myWeapon;
    private Item myItem;
    private boolean main;
    private Room location;

    public Player(String name, int maxHP, int ATK, int DEF, boolean main) {
        this.name = name;
        this.maxHP = maxHP;
        this.ATK = ATK;
        this.DEF = DEF;
        this.HP = maxHP;
        this.main = main;
    }

    public void attack(Player target) {
        int damage = this.ATK - target.DEF;
        if (damage < 0) damage = 1;
        target.HP -= damage;
        if (target.HP < 0) target.HP = 0;

        // FIX: weapon durability logic – avoid double subtraction
        if (myWeapon != null) {
            if (myWeapon.getDurability() > 0) {
                myWeapon.use();
            }
            if (myWeapon.getDurability() <= 0) {
                System.out.println(this.name + "'s " + myWeapon.getName() + " broke!");
                this.ATK -= myWeapon.getAtk();
                myWeapon = null;
            }
        }

        System.out.println(this.name + " attacked " + target.name + " dealing " + damage + " damage! " +
                target.name + " HP: " + target.HP);
    }

    public void heal() {
        if (myItem != null) {
            int recover = myItem.getheal();
            this.HP += recover;
            if (this.HP > maxHP) this.HP = maxHP;
            System.out.println(this.name + " used " + myItem.getname() + ", recovered " + recover + " HP. Current HP: " + this.HP);
            myItem = null;
        } else {
            System.out.println("No healing item available!");
        }
    }

    public void equipWeapon(Weapon w) {
        if (myWeapon != null) {
            this.ATK -= myWeapon.getAtk();
        }
        myWeapon = w;
        this.ATK += w.getAtk();
        System.out.println(this.name + " equipped " + w.getName());
    }

    public void giveItem(Item i) {
        myItem = i;
        System.out.println(this.name + " obtained " + i.getname());
    }

    public boolean isAlive() { return HP > 0; }

    // Getters and Setters
    public String getName() { return name; }
    public int gethp() { return HP; }
    public int getatk() { return ATK; }
    public int getdef() { return DEF; }
    public int getmhp() { return maxHP; }
    public void sethp(int hp) { this.HP = hp; }
    public Room getLocation() { return location; }
    public void setLocation(Room location) { this.location = location; }
    public Weapon getMyWeapon() { return myWeapon; }
    public Item getMyItem() { return myItem; }
    public void setMyItem(Item i) { myItem = i; }

    @Override
    public String toString() {
        return name + " [HP:" + HP + "/" + maxHP + " ATK:" + ATK + " DEF:" + DEF + "]";
    }
}