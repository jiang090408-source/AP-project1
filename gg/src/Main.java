import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static GameMap gameMap;
    private static Player hero;
    private static boolean inBattle = false;
    private static Player currentEnemy = null;

    public static void main(String[] args) {
        gameMap = new GameMap(5, "Teyvat");
        hero = new Player("Hero", 120, 20, 10, true);
        hero.setLocation(gameMap.getRoom(0, 0));

        hero.equipWeapon(new Weapon("Starter Sword", 10, 15));
        hero.giveItem(new Item("Health Potion", "Restores 15 HP", 15));

        System.out.println("=== Welcome to " + gameMap.getWorldName() + " ===");
        System.out.println("Your goal is to explore the dungeon, defeat all enemies and find the ultimate treasure!");
        System.out.println("Type 'help' for commands.\n");

        while (hero.isAlive()) {
            if (hero.getLocation().getX() == 4 && hero.getLocation().getY() == 4) {
                System.out.println("Game over - You reached the final room!");

                break;
            }
            if (!inBattle) {
                Room current = hero.getLocation();
                System.out.println("\nCurrent position: (" + current.getX() + "," + current.getY() + ")");
                System.out.println(current.getDescription());
                System.out.print("> ");
                String cmd = scanner.nextLine().trim().toLowerCase();

                if (cmd.equals("help")) {
                    showHelp();
                } else if (cmd.equals("status")) {
                    showStatus();
                } else if (cmd.equals("inventory")) {
                    showInventory();
                } else if (cmd.startsWith("move ")) {
                    handleMove(cmd);
                } else if (cmd.equals("quit")) {
                    System.out.println("Game over.");
                    return;
                } else {
                    System.out.println("Unknown command. Type 'help'.");
                }
            } else {
                System.out.println("\n⚔️ BATTLE MODE ⚔️");
                System.out.println("Enemy: " + currentEnemy);
                System.out.println(hero);
                System.out.print("[attack / heal / flee] > ");
                String action = scanner.nextLine().trim().toLowerCase();

                if (action.equals("attack")) {
                    hero.attack(currentEnemy);
                    if (!currentEnemy.isAlive()) {
                        System.out.println("You defeated " + currentEnemy.getName() + "!");
                        Room room = hero.getLocation();
                        room.clearEnemy();
                        inBattle = false;
                        currentEnemy = null;
                        continue;
                    }
                    currentEnemy.attack(hero);
                    if (!hero.isAlive()) {
                        System.out.println("You were defeated... Game over.");
                        return;
                    }
                } else if (action.equals("heal")) {
                    hero.heal();
                    currentEnemy.attack(hero);
                    if (!hero.isAlive()) {
                        System.out.println("You were defeated... Game over.");
                        return;
                    }
                } else if (action.equals("flee")) {
                    if (Math.random() < 0.5) {
                        System.out.println("You successfully fled! You return to the start room.");
                        inBattle = false;
                        hero.setLocation(gameMap.getRoom(0, 0));
                        currentEnemy = null;
                    } else {
                        System.out.println("Failed to flee! The enemy attacks!");
                        currentEnemy.attack(hero);
                        if (!hero.isAlive()) {
                            System.out.println("You were defeated... Game over.");
                            return;
                        }
                    }
                } else {
                    System.out.println("In battle you can only use: attack / heal / flee");
                }
            }
        }
        System.out.println("Game over.");
    }

    private static void handleMove(String cmd) {
        String[] parts = cmd.split(" ");
        if (parts.length < 2) {
            System.out.println("Usage: move <direction> (north/south/east/west)");
            return;
        }

        Direction dir;
        if (parts[1].equals("north")) {
            dir = Direction.NORTH;
        } else if (parts[1].equals("south")) {
            dir = Direction.SOUTH;
        } else if (parts[1].equals("east")) {
            dir = Direction.EAST;
        } else if (parts[1].equals("west")) {
            dir = Direction.WEST;
        } else {
            System.out.println("Invalid direction.");
            return;
        }

        Room current = hero.getLocation();
        int newX = current.getX();
        int newY = current.getY();

        if (dir == Direction.NORTH) {
            newX--;
        } else if (dir == Direction.SOUTH) {
            newX++;
        } else if (dir == Direction.EAST) {
            newY++;
        } else if (dir == Direction.WEST) {
            newY--;
        }

        Room newRoom = gameMap.getRoom(newX, newY);
        if (newRoom == null) {
            System.out.println("A wall blocks the way!");
            return;
        }

        hero.setLocation(newRoom);
        System.out.println("You moved to a new room.");
        triggerRoomEvent(newRoom);
    }
    private static void triggerRoomEvent(Room room) {
        if (room.getType() == RoomType.ENEMY) {
            if (room.getEnemy() != null) {
                System.out.println("⚠️ " + room.getDescription());
                currentEnemy = room.getEnemy();
                inBattle = true;
            } else {
                System.out.println("This room once held an enemy, but it's empty now.");
            }
        } else if (room.getType() == RoomType.REST) {
            int heal = room.getHealAmount();
            hero.sethp(Math.min(hero.gethp() + heal, hero.getmhp()));
            System.out.println("You rested and recovered " + heal + " HP. Current HP: " + hero.gethp());
        } else if (room.getType() == RoomType.ITEM) {
            Item item = room.getRewardItem();
            if (item != null) {
                System.out.println("You found " + item.getname() + "!");
                if (hero.getMyItem() == null) {
                    hero.giveItem(item);
                    room.clearItem();
                } else {
                    System.out.println("Inventory full, cannot pick up.");
                }
            }
        } else if (room.getType() == RoomType.WEAPON) {
            Weapon weapon = room.getRewardWeapon();
            if (weapon != null) {
                System.out.println("You found " + weapon.getName() + "!");
                hero.equipWeapon(weapon);
                room.clearWeapon();
            }
        }
    }

    private static void showStatus() {
        System.out.println(hero);
        if (hero.getMyWeapon() != null)
            System.out.println("Equipped weapon: " + hero.getMyWeapon());
        else
            System.out.println("No weapon equipped");
    }

    private static void showInventory() {
        if (hero.getMyItem() != null)
            System.out.println("Item: " + hero.getMyItem());
        else
            System.out.println("No items carried");
    }

    private static void showHelp() {
        System.out.println("===== Commands =====");
        System.out.println("status        - Show character status");
        System.out.println("inventory     - Show carried items");
        System.out.println("move <dir>    - Move (north/south/east/west)");
        System.out.println("quit          - Exit game");
        System.out.println("help          - Show this help");
        System.out.println("Battle commands: attack / heal / flee");
        System.out.println("===================");
    }
}