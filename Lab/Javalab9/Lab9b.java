public class Lab9b
{
  public static void main (String[] args)
  {
    MyDate d = new MyDate();
    System.out.println ("Default Date: " + d);
    
    MyDate d2 = new MyDate();
    d2.setMonth(11);
    d2.setDay(26);
    d2.setYear(15);
    System.out.println ("Happy Thanksgiving: " + d2);
    
    /* delete this line to test Q 1
    // Create a date using a constructor with three integer values
    MyDate d3 = new MyDate( 11, 4, 2015);
    System.out.println ("Todays Date: " + d3);
       delete this line to test Q 1  */
    
    /* delete this line to test Q 3
    // Create a date using a copy constructor 
    MyDate d4 = new MyDate( d2 );
    System.out.println ("Thanksgiving Date: " + d4);
       delete this line to test Q 3  */
    
    // Set of Statements to explore static instance variables 
    System.out.println ("\n\nTesting out Static Instance Variables");
    d.setVal(5);
    System.out.println ("val via d:  " + d.getVal());
    d.modVal(2);
    System.out.println ("val via d:  " + d.getVal());
    d2.setVal(13);
    d2.modVal(12);
    System.out.println ("val via d2: " + d2.getVal());
    System.out.println ("val via d:  " + d.getVal());
    d.modVal(-4);
    System.out.println ("val via d:  " + d.getVal());
    System.out.println ("val via d2: " + d2.getVal());
  }
}

class MyDate
{
  private int month;
  private int day;
  private int year;
  static private int val;  // used to test out static instance variables
                           // val really has nothing to do with a date class 
  
  public static final String[] Months = {"NoMonth", "January", "February",
    "March", "April", "May", "June", "July", "August", "September",
    "October", "November", "December"};
  
  public static final int[] MaxDayCount = {0, 31, 28, 31, 30, 31, 30,
    31, 31, 30, 31, 30, 31};
    
  // this class does not have any constructors  
  
  MyDate() {
    month = 1;
    day = 1;
    year = 2001;
  }

  MyDate(int m, int d, int y) {
    month = m;
    day = d;
    year = y;
  }

  public void setMonth(int m)
  {
    if (m >= 1 && m <= 12)
      month = m;
  }
  
  public void setDay(int d)
  {
    if (month >= 1 && month <= 12 && d >= 1 && d <= MaxDayCount[month])
      day = d;
  }
  
  public void setYear(int y)
  {
    if (y >= 0 && y <= 99)
      year = 2000 + y;
    else
      year = y;
  }
  
  // code to convert an instance of the MyDate class to a String
  public String toString ()  
  {
    //return (month+1) + "/" + day + "/" + year;   // prints in MM/DD/YYYY format
    return Months[month] + " " + day + ", " + year; // prints in MDY format
  }
  
  // code testing out a static instance variable 
  public static void setVal(int v)
  {
    val = v;
  }
  
  public static int getVal()
  {
    return val;
  }
  
  public static void modVal (int v)
  {
    val = val + v;
  }
}