public class Weapon {
    private String name;
    private int atk;
    private int durability;

    public Weapon(String name, int atk, int durability) {
        this.name = name;
        this.atk = atk;
        this.durability = durability;
    }

    public void use() {
        if (durability > 0) durability--;
    }

    public String getName() { return name; }
    public int getAtk() { return atk; }
    public int getDurability() { return durability; }

    @Override
    public String toString() {
        return name + " (dmg:" + atk + " dur:" + durability + ")";
    }
}