import java.awt.*;

/**  Initial attempt at creating some code to get things going for Project 6
  * 
  * THis code contains 2 classes
  *   Island - the 20x20 area for the Creatures
  *   Beetle - ojects that not quite what is required but give me a good starting point
  */

public class wdraho2Proj7
{
  public static void main (String[] args)
  {
    // create my island
    Island island = new Island();
    
    // create 20 beetles
    for (int i = 0 ; i < 20 ; i++)
    {
      Beetle b = new Beetle(island);
    }
    
    // run simulation for 20 days
    for (int i = 0 ; i < 20 ; i++)
    {
      GridDisplay.mySleep ( 500 );
      island.nextDay();
    }
  }
}

class Island
{
  private GridDisplay disp;
  private Beetle[][] grid;
  private int dayCount;
  private int rows;
  private int cols;
  
  public Island ()
  {
    rows = 20;
    cols = 20;
    
    disp = new GridDisplay(rows, cols);
    grid = new Beetle[rows][cols];
    
    for (int i = 0 ; i < rows ; i++)
      for (int j = 0 ; j < cols ; j++)
         grid[i][j] = null;
    
    dayCount = 0;
  }
  
  public int getRows()
  {
    return rows;
  }
  
  public int getCols()
  {
    return cols;
  }
  
  public boolean isOccupied (int i, int j)
  {
    if (grid[i][j] == null)
      return false;
    else
      return true;
  }
  
  public void nextDay ()
  {
    dayCount++;
    System.out.println ("DayCount: " + dayCount);
    
    // loop through the grid positions
    for (int i = 0 ; i < rows ; i++)
      for (int j = 0 ; j < cols ; j++)
      {
        // System.out.println (i + " " + j + ", " + grid[i][j] != null );
        
        // make sure grid location contains a beetle and the beetle has not moved this day
        if ( (isOccupied(i,j) == true)  && (grid[i][j].getDayLastMoved() != dayCount) )
        {
          // System.out.println (i + " " + j + ", " + grid[i][j].getDayLastMoved() );
          
          // Access the beetle at this position and try to move it
          Beetle b = grid[i][j];
          b.move();
          b.setDayLastMoved (dayCount);
        }
      }
  }
  
  public boolean addBeetle (Beetle b, int x, int y)
  {
    // make sure x and y are valid
    if (x < 0 || x >= rows || y < 0 || y >= cols)
      return false;
    
    // make sure no beetle is already at that space
    if ( isOccupied(x, y) == true)
      return false;
    
    grid[x][y] = b;
    disp.setColor (x, y, Color.RED);
    return true;
  }
  
  public boolean moveBeetle (int currX, int currY, int nextX, int nextY)
  {
    // make sure x and y positions are both valid
    if (currX < 0 || currX >= rows || currY < 0 || currY >= cols)
      return false;
    if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols)
      return false;
    
    // make sure a beetle exists at the CURR space
    if ( isOccupied(currX, currY) == false)
      return false;
    // make sure no beetle is already at the NEXT space
    if ( isOccupied(nextX, nextY) == true)
      return false;
    
    // move the beetle to the new position
    grid[nextX][nextY] = grid[currX][currY];
    grid[currX][currY] = null;
            
    // update the GridDisplay colors to show the Beetle has moved
    disp.setColor(nextX,nextY, Color.RED);
    disp.setColor(currX,currY, Color.WHITE);
    return true;
  }
}

class Beetle
{
  private Island isl;
  private int x;
  private int y;
  private int dayLastMoved;

  public Beetle (Island island)
  {
    isl = island;
    x = (int)(Math.random() * island.getRows() );
    y = (int)(Math.random() * island.getCols() );
    dayLastMoved = 0;
  
    island.addBeetle (this, x, y);
  }
  
  public void move ()
  {
    int nextX = -999;
    int nextY = -999;
    int direction = (int)(Math.random() * 4);
    if (direction == 0) // attempt to move up
    {
      nextX = x; nextY = y-1;
    }
    if (direction == 1) // attempt to move down
    {
      nextX = x; nextY = y+1;
    }
    if (direction == 2) // attempt to move left
    {
      nextX = x-1; nextY = y;
    }
    if (direction == 3) // attempt to move right
    {
      nextX = x+1; nextY = y;
    }
          
    //System.out.println ("Try to move from (" + x + ", " + y +") to (" + nextX + ", " + nextY + ")" );
    
    // try to move the beetle to the NEXT position
    if ( isl.moveBeetle ( x, y, nextX, nextY ) == true)
    {
      x = nextX;
      y = nextY;
    }
  }
  
  public void setDayLastMoved (int dlm)
  {
    dayLastMoved = dlm;
  }
  
  public int getDayLastMoved ()
  {
    return dayLastMoved;
  }
}