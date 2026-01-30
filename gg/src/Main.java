public class Main {
    public static void main(String[] args) {
        World world =  new World();
        world.setName("Teyvat");
        Player hero = new Player("Daniel", 100, 20,1, world, true);

       Player monster = new Player("monster", 150, 5, 10, world, false);

        Item BloodPack = new Item("Blood Pack", "Can treat you with 10hp", 10);

        Weapon Sword = new Weapon("Sword",20, 20 );
        Weapon Axe = new Weapon("Axe", 10, 15 );

        hero.equipWeapon(Sword);
        hero.giveItem(BloodPack);

        monster.equipWeapon(Axe);

        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.println("Welcome to " + world.getName() + "!");

        while (hero.isAlive() && monster.isAlive()){
            System.out.println("Type LOOK or ATTACK or HEAL (or QUIT):");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("look")) {
                world.describeWorld(world, hero, monster, BloodPack);
            }
             if(input.equals("attack")){
                  System.out.println("hero damage monster" + hero.getatk());
                  monster.attack(monster, hero);
                  hero.attack(hero, monster);
            }
             if(input.equals("heal")){
                 int a = hero.gethp() + BloodPack.getheal();
                 System.out.println("Your hp is now :" + a);
            }
             if(input.equals("quit")){
                 System.out.println("Goodbye!");

            }

             if(!monster.isAlive()){
                System.out.println();
                System.out.println();
                System.out.println("Hero save the world" + " " + world.getName() + "!");

             }
            if(!hero.isAlive()){
                System.out.println("Monster damage the world" + world.getName());

            }



        }
    }
}