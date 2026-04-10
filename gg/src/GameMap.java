public class GameMap {
    private Room[][] rooms;
    private int size;
    private String worldName;

    public GameMap(int size, String worldName) {
        this.size = size;
        this.worldName = worldName;
        rooms = new Room[size][size];
        initMap();
    }

    private void initMap() {
        // Normal rooms
        rooms[0][0] = new Room(0, 0, "You stand in a dim stone chamber, the air smells of mildew.", RoomType.NORMAL);
        rooms[0][1] = new Room(0, 1, "A long corridor with ancient runes carved into the walls.", RoomType.NORMAL);
        rooms[0][2] = Room.createRestRoom(0, 2, "A warm rest chamber with a magical spring in the center.", 30);
        rooms[0][3] = Room.createItemRoom(0, 3, "An abandoned armory, a small shiny pouch in the corner.", new Item("Health Potion", "Restores 20 HP", 20));
        rooms[0][4] = Room.createEnemyRoom(0, 4, "A gargoyle statue comes to life before you!", createGargoyle());

        rooms[1][0] = Room.createEnemyRoom(1, 0, "A giant spider drops from the ceiling!", createSpider());
        rooms[1][1] = new Room(1, 1, "A damp passage, dripping water echoes.", RoomType.NORMAL);
        rooms[1][2] = new Room(1, 2, "A faded mural on the wall.", RoomType.NORMAL);
        rooms[1][3] = Room.createRestRoom(1, 3, "A ruined shrine with faint holy light.", 20);
        rooms[1][4] = Room.createEnemyRoom(1, 4, "A skeleton warrior charges at you with a sword!", createSkeleton());

        // FIX: changed to createWeaponRoom
        rooms[2][0] = Room.createWeaponRoom(2, 0, "Next to a fallen adventurer lies a shiny weapon.", new Weapon("Longsword", 15, 8));
        rooms[2][1] = Room.createEnemyRoom(2, 1, "A cave troll blocks the way!", createTroll());
        rooms[2][2] = new Room(2, 2, "An underground lake, the water is still as a mirror.", RoomType.NORMAL);
        rooms[2][3] = Room.createRestRoom(2, 3, "Elven ruins, a healing breeze blows.", 25);
        rooms[2][4] = Room.createEnemyRoom(2, 4, "A shadow assassin emerges from the darkness!", createAssassin());

        rooms[3][0] = new Room(3, 0, "A trap-filled passage, but you pass safely.", RoomType.NORMAL);
        rooms[3][1] = Room.createItemRoom(3, 1, "An ancient chest contains a health potion.", new Item("Health Potion", "Restores 15 HP", 15));
        rooms[3][2] = Room.createEnemyRoom(3, 2, "A fire golem radiates intense heat!", createGolem());
        rooms[3][3] = new Room(3, 3, "A gem-embedded door glows with magical energy.", RoomType.NORMAL);
        rooms[3][4] = Room.createRestRoom(3, 4, "A quiet garden, moonlight heals wounds.", 35);

        rooms[4][0] = Room.createEnemyRoom(4, 0, "A necromancer summons skeletons!", createNecromancer());
        rooms[4][1] = new Room(4, 1, "A library where books flip pages by themselves.", RoomType.NORMAL);
        // FIX: changed to createWeaponRoom
        rooms[4][2] = Room.createWeaponRoom(4, 2, "Next to a forge lies a fine weapon.", new Weapon("Battle Axe", 25, 10));
        rooms[4][3] = Room.createEnemyRoom(4, 3, "A dragonborn guard breathes fire!", createDragonkin());
        rooms[4][4] = new Room(4, 4, "This is the end of game", RoomType.NORMAL);
    }

    private Player createGargoyle() {
        return new Player("Gargoyle", 70, 12, 5, false);
    }
    private Player createSpider() {
        return new Player("Giant Spider", 50, 10, 3, false);
    }
    private Player createSkeleton() {
        Player s = new Player("Skeleton Warrior", 60, 14, 4, false);
        s.equipWeapon(new Weapon("Bone Sword", 12, 20));
        return s;
    }
    private Player createTroll() {
        Player t = new Player("Cave Troll", 100, 18, 6, false);
        t.equipWeapon(new Weapon("Club", 15, 15));
        return t;
    }
    private Player createAssassin() {
        Player a = new Player("Shadow Assassin", 55, 20, 2, false);
        a.equipWeapon(new Weapon("Dagger", 18, 12));
        return a;
    }
    private Player createGolem() {
        return new Player("Fire Golem", 120, 16, 8, false);
    }
    private Player createNecromancer() {
        Player n = new Player("Necromancer", 65, 15, 3, false);
        n.equipWeapon(new Weapon("Staff", 13, 10));
        return n;
    }
    private Player createDragonkin() {
        Player d = new Player("Dragonborn Guard", 110, 22, 7, false);
        d.equipWeapon(new Weapon("Dragonblade", 20, 12));
        return d;
    }

    public Room getRoom(int x, int y) {
        if (x >= 0 && x < size && y >= 0 && y < size)
            return rooms[x][y];
        return null;
    }

    public int getSize() { return size; }
    public String getWorldName() { return worldName; }
}