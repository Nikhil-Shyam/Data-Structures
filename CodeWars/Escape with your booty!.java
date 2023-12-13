import java.util.ArrayList;

public class Course {
  
  public static boolean checkCourse(char[][] map) {
    ArrayList<NavalShip> ships = new ArrayList<>();
    int x = 0;
    int y = 0;

    try{
      if (map[x-1][y] == 'N' || map[x-1][y+1] == 'N' || map[x][y+1] == 'N' || map[x+1][y] == 'N' || map[x+1][y+1] == 'N')
          return false;
    }catch(ArrayIndexOutOfBoundsException e){}

    for (int i = 0; i < map.length; i++){
      for (int j = 0; j < map[i].length; j++){
        if (i == 0 && map[i][j] == 'N')
          ships.add(new NavalShip(map, i, j, true));
        if (i == map.length-1 && map[i][j] == 'N')
          ships.add(new NavalShip(map, i, j, false));
        if (map[i][j] == 'X'){
          x = i;
          y = j;
        }
      }
    }

    while (y != map[0].length-1){
      map[x][y+1] = 'X';
      map[x][y] = '0';
      y++;

      for (NavalShip i: ships)
        i.move();

      try{
        if (map[x-1][y-1] == 'N' || map[x-1][y] == 'N' || map[x-1][y+1] == 'N' || map[x][y-1] == 'N' || map[x][y+1] == 'N' || map[x+1][y-1] == 'N' || map[x+1][y] == 'N' || map[x+1][y+1] == 'N')
          return false;
      }catch(ArrayIndexOutOfBoundsException e){}
    }
    return true;
  }
  
  public static class NavalShip{
    private boolean down;
    private int i;
    private int j;
    private char[][] map;
        
    public NavalShip(char[][] m, int i, int j, boolean d){
      map = m;
      this.i = i;
      this.j = j;
      down = d;
    }
        
    public void move(){
      if (i == 0)
        down = true;
      if (i == map.length-1)
        down = false;

      if (down){
        map[i+1][j] = 'N';
        map[i][j] = '0';
        i++;
      }else{
        map[i-1][j] = 'N';
        map[i][j] = '0';
        i--;
      }
    }
  }
}
