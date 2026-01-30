public class World {
    private int count;
    private Player player;
    private Player monster;
    private String name;
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setName(String a){
        name=a;
    }
    public void setMonster(Player monster) {
        this.monster = monster;
    }
    public boolean isturnover(){
        if(player.gethp()>=0&&monster.gethp()>=0){
            return false;
        }
        return true;
    }
    public Player Winner(){
        if(isturnover()==true){
            if(player.isAlive()) {
                return player;
            }else{
                return monster;
            }
        }else{
            return null;
        }
    }
    public String getName(){
        return name;
    }

    public void describeWorld(World a, Player b, Player c, Item d){
        System.out.println("World: "+a.getName()+" Player: "+ b.getName() + " Player: " + c.getName() + " Item: " + d.getdescription());
    }
}
