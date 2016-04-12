import java.io.*;
import java.util.*;

/**
 * Wynn Drahorad - wdraho2
 * Project 6 - Airport Directification (my name for it)
 */

public class Wdraho2proj6
{

  static Airport[] airports = new Airport[10];
  static FileChain files = new FileChain();

 public static void main (String[] args)
 {
     for(int i = 0; i < 10; ++i) {
         airports[i] = new Airport(i+1);
     }   // set up an instance of the BufferedReader class to read from standard input
   BufferedReader br = new BufferedReader (new InputStreamReader (
        System.in));

   // set up the data needed for the airport adjcency list
   Wdraho2proj6 airportData = new Wdraho2proj6();


   // call the method that reads and parses the input
   airportData.processCommandLoop (br);
   
   System.out.println ("Goodbye");
 }
   
   
  public void processCommandLoop (BufferedReader br)
  {

    try {  //try-catch clauses are needed since BufferedReader and Scanner classes
           //  throw exceptions on errors
    String inline = br.readLine();   // get a line of input
    Scanner sc;

   // loop until all lines are read from the input
   while (inline != null)
   {
     sc = new Scanner (inline);   // process each line of input using the Scanner iterators
     String command = sc.next();
     System.out.println ("*" + command + "*");
     
     if (command.equals("q") == true)
       System.exit(1);
     
     else if (command.equals("?") == true)
       showCommands();
     
     else if (command.equals("t") == true)
       doTravel(sc);
     
     else if (command.equals("r") == true)
       doResize(sc);

     else if (command.equals("i") == true)
       doInsert(sc);

     else if (command.equals("d") == true)
       doDelete(sc);

     else if (command.equals("l") == true)
       doList(sc);

     else if (command.equals("f") == true)
       doFile(sc);

     else if (command.equals("#") == true)
       ;
     
     else
       System.out.println ("Command is not known: " + command);
     
     inline = br.readLine();   // get the next line of input


   }
  }
  catch (Exception ioe)
  {
    System.out.println ("Error in Reading - Assuming End of File");
  }
 }
 
 public void showCommands()
 {
   System.out.println ("The commands for this project are:");
   System.out.println ("  q ");
   System.out.println ("  ? ");
   System.out.println ("  # ");
   System.out.println ("  t <int1> <int2> ");
   System.out.println ("  r <int> ");
   System.out.println ("  i <int1> <int2> ");
   System.out.println ("  d <int1> <int2> ");
   System.out.println ("  l ");
   System.out.println ("  f <filename> ");
 }
 
 public void doTravel(Scanner sc)
 {
   int val1 = 0;
   int val2 = 0;
   
   if ( sc.hasNextInt() == true )
     val1 = sc.nextInt();
   else
   {
     System.out.println ("Integer value expected");
     return;
   }
   
   if ( sc.hasNextInt() == true )
     val2 = sc.nextInt();
   else
   {
     System.out.println ("Integer value expected");
     return;
   }
   
   depthFirstSearchHelper(val1, val2);
   
 }
 
 public void doResize(Scanner sc)
 {
   int val1 = 0;
   if ( sc.hasNextInt() == true ) {
    val1 = sc.nextInt();

   } else
   {
     System.out.println ("Integer value expected");
     return;
   }
   
    System.out.println ("Performing the Resize Command with " + val1 );
    System.out.println("In doResize, Val1: " + val1);
    airports = new Airport[val1];
     for(int i = 0; i < val1; ++i) {
         airports[i] = new Airport(i+1);
     }

  }

 
 public void doInsert(Scanner sc)
 {
  int val1, val2;

  if (sc.hasNextInt()) {
    val1 = sc.nextInt();
    if (sc.hasNextInt()) {
      val2 = sc.nextInt();
    } else {
      return;
    }

    // if the val1 and val2 are not too large
    System.out.println(val1 + " " +val2);
    if (airports.length >= val1 && airports.length >= val2 && val1 > 0 && val2 > 0) {
      // then add flight
      airports[val1-1].addFlight(airports[val2-1]);
    } else {
        System.out.println("Numbers specified are out of range");
    }



  } else {
    return;
  }


 }
 
 public void doDelete(Scanner sc)
 {
  int val1, val2;

  if (sc.hasNextInt()) {
    val1 = sc.nextInt();
    if (sc.hasNextInt()) {
      val2 = sc.nextInt();
    } else {
      return;
    }

    // if the val1 and val2 are not too large
    if (airports.length >= val1 && airports.length >= val2 && val1 > 0 && val2 > 0) {
      // then add flight
      airports[val1-1].removeFlight(airports[val2-1]);
    } else {
        System.out.println("Numbers specified are out of range");
    }




  } else {
    return;
  }
 }
 
 public void doList(Scanner sc)
 {
  int counter = 0;
   for (int i = 0; i < airports.length; ++i) {
     Airport ap = airports[i];
     System.out.println("Airport " + (i+1) + " connects to:");
     Flight temp = ap.head;

    while (temp != null) {

      System.out.println("  -Airport " + temp.dest.number);
      temp = temp.next;
    }
   }
 }
 
 public void doFile(Scanner sc)
 {

   String fname = null;
   boolean error = false;
   String error_str = "";
   
   if ( sc.hasNext() == true )
     fname = sc.next();
   else
   {
     System.out.println ("Filename expected");
     return;
   }
   
   System.out.println ("Performing the File command with file: " + fname);
   
   if (files.contains(fname)) {
      error = true;
      error_str = new String("ERROR: File '" + fname +  "' already in use");
   }

   if (error) {
    System.out.println(error_str);
    return;
   }
  try {
     BufferedReader buffer = new BufferedReader(new FileReader (fname));
     files.add(fname);
     processCommandLoop(buffer);
     buffer.close();
     files.rem(fname);
 } catch (IOException i) {
    System.out.println("ERROR: File '" + fname +  "' not found");
 }

   // next steps:  (print an error message and return if any step fails) 
   //  1. verify the file name is not currently in use
   //  2. open the file
   //  3. create a new instance of BufferedReader
   //        BufferedReader br = new BufferedReader (new FileReader ("MyFileReader.txt"));
   //  4. recursively call processCommandLoop() with this new instance of BufferedReader as the parameter
   //  5. close the file when processCommandLoop() returns

 }

 public void markAllUnvisited() {
  for (int i = 0; i < airports.length; i++) {
    airports[i].visited = false;
  }

  return;
 }

 public void depthFirstSearchHelper(int x, int y) {
  markAllUnvisited();
  if (dfs(x,y) == true) {
    System.out.println("You can get from airport " + x + " to airport " + y + " in one or more flights.");
  } else {
    System.out.println("You can NOT get from airport " + x + " to airport " + y + " in one or more flights.");
  }
 }


 public boolean dfs(int a, int b) {
  Airport origin = airports[a-1];
  Airport dest = airports[b-1];
  Airport subject;
  for (Flight f = origin.head; f != null; f = f.next) {
    subject = f.dest;
    if (subject == dest) {
      return true;
    }  
    if (!subject.visited) {
      subject.visited = true;
      if (dfs(subject,b)) {
        return true;
      }
    }
  }

  return false;
 }


  public boolean dfs(Airport a, int b) {
    Airport origin = a;
    Airport dest = airports[b-1];
    Airport subject;
    for (Flight f = origin.head; f != null; f = f.next) {
      subject = f.dest;
      if (subject == dest) {
        return true;
      }  
      if (!subject.visited) {
        subject.visited = true;
        if (dfs(subject,b)) {
          return true;
        }
      }
    }
    return false;
   }
}




