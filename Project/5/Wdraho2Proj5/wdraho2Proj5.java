/**
 * This file contains the User Interface code for the Infix Evaluation 
 * Project, Project 5 for CS 211 for Spring 2016.
 * 
 * Date: 3/10/16
 * 
 * @Author - Pat Troy, UIC, CS Department
 * @Author - Wynn Drahorad - UIN: 663411123 - wdraho2@uic.edu
 * 
 */

 


import java.io.*;
import java.util.*;

public class wdraho2Proj5
{
  public static boolean debug;

  public static void main (String[] args)
  {

    debug = false;

    for (int i = 0; i < args.length; ++i) {
      if (args[i].equals("-d")) {
        debug = true;
      }
    }

    Token inputToken;
    TokenReader tr = new TokenReader();
    
    System.out.print ("Starting Expression Evaluation Program\n\n");
    System.out.print ("Enter Expression: ");
    

    inputToken = tr.getNextToken ();
    
    while (inputToken.equalsType(TokenType.QUIT) == false)
    {
      /* check first Token on Line of input */
      if(inputToken.equalsType(TokenType.HELP))
      {
       printCommands();
       tr.clearToEoln();
      }
      else if(inputToken.equalsType(TokenType.ERROR))
      {
       System.out.print ("Invalid Input - For a list of valid commands, type ?\n");
       tr.clearToEoln();
      }
      else if(inputToken.equalsType(TokenType.EOLN))
      {
       System.out.print ("Blank Line - Do Nothing\n");
       /* blank line - do nothing */
      }
      else 
      {
       processExpression(inputToken, tr);
      } 

      System.out.print ("\nEnter Expression: ");
      inputToken = tr.getNextToken ();
    }

  System.out.print ("Quitting Program\n");
  return;
    
  }

  public static int popAndEval(Stack ops, Stack vals) {

    if (vals.isEmpty()) {
      System.out.print("ERROR: Stack empty when attempting to popAndEval\n");
      return -999;
    } 

    char op = ops.top().getToken().getOperator();
    ops.pop();
    int val2 = vals.top().getVal();
    vals.pop();
    int val1 = vals.top().getVal();
    vals.pop();

    if (op != '*' && op != '/' && op != '+' && op != '-') {
      return -999;
    }

    int v3 = eval( val1, op, val2);

    vals.push(new Node(v3));

    return 1;
  }

  public static int eval(int v1, char op, int v2) {
    if (op == '+') {
      return v1+v2;
    } else if (op == '-') {
      return v1-v2;
    } else if (op == '*') {
      return v1*v2;
    } else if (op == '/') {
      return v1/v2;
    } else {
      System.out.println("ERROR: Eval operator is not valid");
    }

    return -999;
  }
  
public static void processExpression (Token inputToken, TokenReader tr)
{
 /**********************************************/
 /* Declare both stack head pointers here      */
 Stack vals = new Stack();
 Stack ops = new Stack();


 /* Loop until the expression reaches its End */
 while (inputToken.equalsType(TokenType.EOLN) == false)
   {
    /* The expression contains an OPERATOR */
    if (inputToken.equalsType(TokenType.OPERATOR))
      {
       /* make this a debugMode statement */
       if (debug) {
        System.out.print ("OP: " + inputToken.getOperator() + ", ");
      }

       if (inputToken.equalsOperator('(')) {
        ops.push(new Node(inputToken));
       }
       if (inputToken.equalsOperator('+') || inputToken.equalsOperator('-')) {
        while (!ops.isEmpty() && 
          !ops.top().getToken().equalsOperator(')') && 
          !ops.top().getToken().equalsOperator('(')) {
          popAndEval(ops,vals);
        }
        ops.push(new Node(inputToken));
       }

       if (inputToken.equalsOperator('*') || inputToken.equalsOperator('/')) {
        while (!ops.isEmpty() && 
          (ops.top().getToken().equalsOperator('*') || ops.top().getToken().equalsOperator('/'))) {
          popAndEval(ops,vals);
        }
        ops.push(new Node(inputToken));
       }

       if (inputToken.equalsOperator(')')) {
        while (!ops.isEmpty() && !ops.top().getToken().equalsOperator('(')) {
          popAndEval(ops,vals);
        }

        if (ops.isEmpty()) {
          System.out.println("ERROR: STACK EMPTY (error at line 160)");
        } else {
          ops.pop();
        }
       }

      }

    /* The expression contain a VALUE */
    else if (inputToken.equalsType(TokenType.VALUE))
      {
       /* make this a debugMode statement */
       if (debug){
       System.out.print ("Val: " + inputToken.getValue() + ", "); 
     }


       vals.push(
        new Node(
          inputToken.getValue()
          )
        );

      }
   
    /* get next token from input */
    inputToken = tr.getNextToken ();
   }
   while (!ops.isEmpty()) {
    popAndEval(ops,vals);
   } 
   System.out.println("Top of ValueStack: " + vals.top().getVal());
   vals.pop();
   if (!vals.isEmpty()) {
    System.out.println("ERROR: At least one value left over after evaluating expression");
   }

 /* The expression has reached its end */

 // add code to perform this operation here

 System.out.print ("\n");
}


/**************************************************************/
/*                                                            */
/*  The Code below this point should NOT need to be modified  */
/*  for this program.   If you feel you must modify the code  */
/*  below this point, you are probably trying to solve a      */
/*  more difficult problem that you are being asked to solve  */
/*                                                            */
/**************************************************************/




public static void printCommands()
{
 System.out.print ("The commands for this program are:\n\n");
 System.out.print ("q - to quit the program\n");
 System.out.print ("? - to list the accepted commands\n");
 System.out.print ("or any infix mathematical expression using operators of (), *, /, +, -\n");
}

}

class TokenReader
{
  private BufferedReader br;
  private String inline;
  private boolean needline;
  private int pos;
  
  // initialize the TokenReader class to read from Standard Input
  public TokenReader()
  {
    // set to read from Standard Input
    br = new BufferedReader (new InputStreamReader (
        System.in));
    inline = "";
    pos = 0;
    needline = true;
  }
  
  // Force the next getNextToken to read in a line of input
  public void clearToEoln()
  {
    needline = true;
  }
  
  // Return the next Token from the input line
  public Token getNextToken()
  {
    // get a new line of input from user
    if (needline)
    {
      try{
        inline = br.readLine();
      }
      catch (IOException ioe)
      {
        System.out.println ("Error in reading");
        return new Token (TokenType.EOF);
      }
      if (inline == null)
      { // End of File Encoutered - Should never be the case when reading 
        //   from standard input: System.in
        return new Token (TokenType.EOF);
      }
      
      inline = inline + " ";    // add a space at end to help deal with digit calculation
      needline = false;
      pos = 0;
    }
    
    // skip over any white space characters in the beginning of the input
    while ( pos < inline.length() && 
            Character.isWhitespace(inline.charAt(pos)))
    {
      pos++;
    }
    
    // check for the end of the current line of input
    if (pos >= inline.length())
    {  // at end of line
      needline = true;
      return new Token (TokenType.EOLN);
    }
      
    // Get the next character from the input line
    char ch = inline.charAt(pos); pos++;
    
    // check if 'q' or 'Q' was entered ==> QUIT Token
    if ( 'q' == ch || 'Q' == ch )
    {
      return new Token (TokenType.QUIT);
    }
    
    // check if "?" was entered ==> HELP Token
    if ( '?' == ch )
    {
      return new Token (TokenType.HELP);
    }
    
    // check for Operator values of: + - * / ( )  ==> OPERATOR Token
    if ( ('+' == ch) || ('-' == ch) || ('*' == ch) ||
      ('/' == ch) || ('(' == ch) || (')' == ch) )
    {
      Token t = new Token();
      t.setToOperator( ch );
      return t;
    }
     
    // check for a number  ==> VALUE Token
    if (Character.isDigit(ch))
    {
      int number = Character.digit(ch, 10);
      ch = inline.charAt(pos); pos++;
      while (Character.isDigit(ch))
      {
        number = number * 10 + Character.digit(ch, 10);
        ch = inline.charAt(pos); pos++;
      }
      pos--; // since number calcuation check one character after the last digit
      Token t = new Token();
      t.setToValue( number );
      return t; 
    }
   
    // Input in not valid if code get to this part ==> ERROR Token
    return new Token (TokenType.ERROR);
  }
    
}
  
// Enumarated Type specifying all of the Tokens
enum TokenType{
  ERROR, OPERATOR, VALUE, EOLN, QUIT, HELP, EOF
}

// Class to hold the Token information
class Token
{
  private TokenType type;
  private char      op;       // using '$' as undefined/error
  private int       val;      // using -999 as undefined/error
  
  // Default to initialize to the ERROR TokenType
  public Token()
  {
    type = TokenType.ERROR;
    op = '$'; 
    val = -999;
  }
  
  // Initialize to a specific TokenType
  public Token (TokenType t)
  {
    type = t;
    op = '$'; 
    val = -999;
  }
  
  // Set to a specific TokenType
  public void setToType(TokenType t)
  {
    type = t;
    op = '$'; 
    val = -999;
  }
  
  // Set to a OPERATOR TokenType with specific operator value
  public void setToOperator(char c)
  {
    type = TokenType.OPERATOR;
    op = c; 
    val = -999;
  }
  
  // Set to a VALUE TokenType with a specific numeric value
  public void setToValue(int v)
  {
    type = TokenType.VALUE;
    op = '$'; 
    val = v;
  }
  
  // return true if the Current Token is of the given TokenType
  public boolean equalsType(TokenType t)
  {
    if (type == t)
      return true;
    else
      return false;
  }
  
  // return true if the Current Token is of the OPERATOR TokenType
  //     and contains the given operator character
  public boolean equalsOperator(char c)
  {
    if (type == TokenType.OPERATOR && op == c)
      return true;
    else
      return false;
  }
  
  // Return the Operator for the current Token
  //   verify the current Token is of the OPERATOR TokenType
  public char getOperator ()
  {
    if (type == TokenType.OPERATOR)
      return op;
    else
      return '$';   // using $ to indicate an error value
  }
  
  // Return the Value for the current Token
  //   verify the current token is of the value TokenType
  public int getValue()
  {
    if (type == TokenType.VALUE)
      return val;
    else
      return -999;  // using -999 to indicate an error value
  }
}