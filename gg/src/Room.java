import java.util.ArrayList;

public class Room {
    private int x, y;
    private String description;
    private RoomType type;
    private Player enemy;
    private int healAmount;
    private Item rewardItem;
    private Weapon rewardWeapon;   // FIX: added weapon field

    public Room(int x, int y, String description, RoomType type) {
        this.x = x;
        this.y = y;
        this.description = description;
        this.type = type;
    }

    public static Room createEnemyRoom(int x, int y, String description, Player enemy) {
        Room room = new Room(x, y, description, RoomType.ENEMY);
        room.enemy = enemy;
        return room;
    }

    public static Room createRestRoom(int x, int y, String description, int healAmount) {
        Room room = new Room(x, y, description, RoomType.REST);
        room.healAmount = healAmount;
        return room;
    }

    public static Room createItemRoom(int x, int y, String description, Item item) {
        Room room = new Room(x, y, description, RoomType.ITEM);
        room.rewardItem = item;
        return room;
    }

    // FIX: factory method for weapon rooms
    public static Room createWeaponRoom(int x, int y, String description, Weapon weapon) {
        Room room = new Room(x, y, description, RoomType.WEAPON);
        room.rewardWeapon = weapon;
        return room;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public String getDescription() { return description; }
    public RoomType getType() { return type; }
    public Player getEnemy() { return enemy; }
    public int getHealAmount() { return healAmount; }
    public Item getRewardItem() { return rewardItem; }
    public Weapon getRewardWeapon() { return rewardWeapon; }   // FIX: getter

    public void clearEnemy() {
        if (type == RoomType.ENEMY) {
            type = RoomType.NORMAL;
            enemy = null;
            description = "An empty room, the enemy has been defeated.";
        }
    }

    // FIX: clear item method
    public void clearItem() {
        if (type == RoomType.ITEM) {
            type = RoomType.NORMAL;
            rewardItem = null;
            description = "You have taken the item here.";
        }
    }

    // FIX: clear weapon method
    public void clearWeapon() {
        if (type == RoomType.WEAPON) {
            type = RoomType.NORMAL;
            rewardWeapon = null;
            description = "You have taken the weapon here.";
        }
    }
}