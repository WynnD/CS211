public class Lab9Combined
{
  
  public static void main (String[] args)
  {
    mainLab9b();
    mainLab9c();
  }
  
  public static void mainLab9b ()
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
  
 public static void mainLab9c ()
  {
    Fraction f1, f2, f3;
    f1 = new Fraction (-42, -56);
    System.out.println ("f1: " + f1);
    f2 = new Fraction (52, -13);
    System.out.println ("f2: " + f2);
    
    f3 = f1.add(f2);
    System.out.println ("f3: " + f3);
    System.out.println ("f1 must not be changed as a result of calling add(): " + f1);
    System.out.println ("f2 must not be changed as a result of calling add(): " + f2);
    
    /* Commented out block of code
    // uncomment for Q7 (delete above comment line)
    f1 = new Fraction ();     
    f2 = new Fraction (0);
    System.out.println ("\nEquality Testing Part 1\nf1: " + f1 + ", f2: " + f2 );
    if ( f1 == f2 )
      System.out.println (" f1 == f2 ");
    else
      System.out.println (" f1 != f2 ");
    
    if ( f1.equals(f2) == true )
      System.out.println (" f1 equals f2 ");
    else
      System.out.println (" f1 does not equal f2 ");
      
    f2 = f1;
    System.out.println ("\nEquality Testing Part 2\nf1: " + f1 + ", f2: " + f2 );
    if ( f1 == f2 )
      System.out.println (" f1 == f2 ");
    else
      System.out.println (" f1 != f2 ");
    
    if ( f1.equals(f2) == true )
      System.out.println (" f1 equals f2 ");
    else
      System.out.println (" f1 does not equal f2 ");
    // end of comment block (delete below comment line)
    */
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




/** Fraction Class  - represent a rational number as a fraction using an integer numerator and denominator 
  * 
  *  instance variables
  *     num - the integer numerator part of the fraction
  *     den - the integer denominator part of the fraction
  * 
  * Restrictions on the class
  * - the denominator should never be a value of 0
  * - the denominator should never be negative 
  * - the fraction should be reduced to its lowest terms
  */

class Fraction
{
  private int num;
  private int den;
  
  // Default Constructor: set the fraction to 0/1
  public Fraction()
  {
  }
  
  // Single integer Constructor: set the fraction to val/1
  public Fraction (int val)
  {
  }
  
  // Double integer Constructor: set the fraction to val1/val2
  //   if val2 is negative, normalize (invert sign on both val1 and val2)
  //   reduce the fraction to its lowest terms
  public Fraction (int val1, int val2)
  {
    num = val1;
    den = val2;
    normalize();
    reduce();
  }
  
  
  public Fraction add (Fraction f2)
  {
    Fraction result = new Fraction ();
    
    return result;
  }
  
  
  public boolean equals (Fraction f2)
  { // true if num1*den2 == num2*den1
    if ( num*f2.den == f2.num*den )
      return true;
    else
      return false;
  }
  
  public String toString()
  { // used with System.out.print() methods
    return num + "/" + den;
  }
  
  private void normalize ()
  {
    if ( den < 0 )
    {
      num = -num;
      den = -den;
    }
  }
  
  private void reduce ()
  {
    int a = num;
    int b = den;
    
    // take the absolute value of both a and b (make sure a and b are postive numbers)
    if (a < 0)
      a = -a;
    if (b < 0)
      b = -b;
    
    int gcd = greatestCommonDivisor (a, b);
    
    num = num / gcd;
    den = den /gcd;
  }
  
  private static int greatestCommonDivisor (int a, int b)
  {
    // computer Greatest Common Divisor recursively via Euclid's algorithm
    if (b == 0)
      return a;
    else 
      return greatestCommonDivisor (b, a%b);
  }
  
}