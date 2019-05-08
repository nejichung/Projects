/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import UI.UserIO;
import java.util.Scanner;

/**
 *
 * @author Jacob
 */
    public class ConsoleIO implements UserIO {

    Scanner scn = new Scanner(System.in); // put in class before instead of in each method to prevent making a scanner each time

    @Override
    public void print(String msg) { // this will implement from UserIO
        System.out.print(msg);

    }

    @Override
    public String readString(String prompt) {
        print(prompt); // print the prompt that was inputted into the readString?
        String input = scn.nextLine(); // input variable is going to be the next line
        return input; // return the input from this method
    }

    @Override
    public double readDouble(String prompt) {
        double toReturn = Double.NaN; // first setting toReturn variable to a number that doesn't exist 
        boolean isCorrect = false; // starts as false since we haven't asked user yet
        while (!isCorrect){ // keep going as long as isCorrect is false
            String input = readString(prompt); // input variable is going to read string prompt??
            try{toReturn = Double.parseDouble(input);  // parse to convert Strings into in this case a double
            isCorrect = true; // make the isCorrect = true and takes us out of the while loop
            
            }catch(NumberFormatException e){ // for exceptions 
            }
    }
         return toReturn;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double toReturn = Double.NaN; // setting to a number that's not possible
        boolean isCorrect = false;
        while(!isCorrect){ // it will loop until isCorrect is true?
            toReturn = readDouble(prompt); // calling the double from the above one
            isCorrect = toReturn >= min && toReturn <= max;   // for correct
              isCorrect = true; // will set the isCorrect to true
        }
        return toReturn;
    }

    @Override
    public float readFloat(String prompt) {
       float toReturn = Float.NaN; // setting float to the min value?
       boolean isCorrect = false; // setting to false
       while(!isCorrect){
           String input = readString(prompt); // read the user input from the readString(prompt)
           try{toReturn = Float.parseFloat(input);
           isCorrect = true; // setting to true
           }catch(NumberFormatException e){ // idk whats this
           }
      }
        return toReturn; // returning
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float toReturn = Float.NaN;
        boolean isCorrect = false;
        while(!isCorrect){
            toReturn = readFloat(prompt);
            isCorrect = toReturn >= min && toReturn <= max; // ??
            isCorrect = true; 
        }
         return toReturn;
    }


    @Override
    public int readInt(String prompt) {
       int toReturn = Integer.MAX_VALUE ;
       boolean isCorrect = false;
       while(!isCorrect){
       String input = readString(prompt);
       try{toReturn = Integer.parseInt(input);
       isCorrect = true;
       }catch(NumberFormatException e){
           
       }
               }
       return toReturn;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int toReturn = Integer.MAX_VALUE;
        boolean isCorrect = false;
        while(!isCorrect){
            toReturn = readInt(prompt);
            isCorrect = toReturn >= min && toReturn <= max;
            isCorrect = true;
        }
        return toReturn;
    }

    @Override
    public long readLong(String prompt) {
       long toReturn = Long.MAX_VALUE;
       boolean isCorrect = false;
       while(!isCorrect){
           String input = readString(prompt);
           try{toReturn = Long.parseLong(input);
           isCorrect = true;
           }catch(NumberFormatException e){
               
           }
       }
       return toReturn;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long toReturn = Long.MAX_VALUE;
        boolean isCorrect = false;
        while(!isCorrect){
            toReturn = readLong(prompt);
            isCorrect = toReturn >= min && toReturn <= max;
            isCorrect = true;
        }
        return toReturn;

}
}



    

