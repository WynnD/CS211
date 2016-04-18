public class Bird extends Animal
{
  public Bird (String t)
  {
    super(t);
  }
  
  public void move()
  {
    System.out.println ("I am flying.");
  }
  
  @Override public void say()
  {
    System.out.println ("Tweet. Tweet.");
  }
}