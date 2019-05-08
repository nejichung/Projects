/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.softwareguild.flooringmastery.ui;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author dsmelser
 */
public class ConsoleIO implements UserIO {

    Scanner scn = new Scanner(System.in);

    @Override
    public void print(String prompt) {
        System.out.print(prompt);
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        String toReturn = scn.nextLine();
        return toReturn;
    }

    @Override
    public double readDouble(String prompt) {
        double toReturn = Double.NaN;

        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);

            try {
                toReturn = Double.parseDouble(input);

                correctInput = true;
            } catch (NumberFormatException ex) {
                //normally do something to log the exception
                //but this is a normal case because of user
                //stupidity
            }

        }

        return toReturn;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double toReturn = Double.NaN;
        boolean correctInput = false;
        while (!correctInput) {
            toReturn = readDouble(prompt);
            correctInput = toReturn >= min && toReturn <= max;
        }

        return toReturn;
    }

    @Override
    public float readFloat(String prompt) {
        float toReturn = Float.NaN;

        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);

            try {
                toReturn = Float.parseFloat(input);

                correctInput = true;
            } catch (NumberFormatException ex) {
                //normally do something to log the exception
                //but this is a normal case because of user
                //stupidity
            }

        }

        return toReturn;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float toReturn = Float.NaN;
        boolean correctInput = false;
        while (!correctInput) {
            toReturn = readFloat(prompt);
            correctInput = toReturn >= min && toReturn <= max;
        }

        return toReturn;
    }

    @Override
    public int readInt(String prompt) {
        int toReturn = Integer.MIN_VALUE;

        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);

            try {
                toReturn = Integer.parseInt(input);

                correctInput = true;
            } catch (NumberFormatException ex) {
                //normally do something to log the exception
                //but this is a normal case because of user
                //stupidity
            }

        }

        return toReturn;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        int toReturn = Integer.MIN_VALUE;
        boolean correctInput = false;
        while (!correctInput) {
            toReturn = readInt(prompt);
            correctInput = toReturn >= min && toReturn <= max;
        }

        return toReturn;
    }

    @Override
    public long readLong(String prompt) {
        long toReturn = Long.MIN_VALUE;

        boolean correctInput = false;
        while (!correctInput) {
            String input = readString(prompt);

            try {
                toReturn = Long.parseLong(input);

                correctInput = true;
            } catch (NumberFormatException ex) {
                //normally do something to log the exception
                //but this is a normal case because of user
                //stupidity
            }

        }

        return toReturn;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long toReturn = Long.MIN_VALUE;
        boolean correctInput = false;
        while (!correctInput) {
            toReturn = readLong(prompt);
            correctInput = toReturn >= min && toReturn <= max;
        }

        return toReturn;
    }

    @Override
    public BigDecimal readBigDecimal(String prompt) {
        BigDecimal bd = null;

        boolean isValid = false;
        while (!isValid) {
            String userInput = readString(prompt);
            try {
                bd = new BigDecimal(userInput, new MathContext(3)); // removing rounding so ex "2.25" won't be rounded to 2.3
                isValid = true;
            } catch (NumberFormatException ex) {
                //do nothing because of very normal user input failure
            }
        }

        return bd;
    }

    @Override
    public LocalDate readDate(String prompt) { // don't know if this is right
        LocalDate toReturn = null;
        boolean isValid = false;
        while (!isValid) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String userInput = readString(prompt);
            try {
                toReturn = LocalDate.parse(userInput, formatter); // we will try to parse the date
                isValid = true;
            } catch (DateTimeException ex) {

            }
        }
        return toReturn;
    }

    @Override
    public LocalDate readDate(String prompt, LocalDate min, LocalDate max) {
        LocalDate toReturn = null;
        boolean isValid = false;
        while (!isValid) {

            LocalDate fromUser = readDate(prompt);
            // ascending then it will be negative
            if (min.compareTo(fromUser) < 0 && fromUser.compareTo(max) < 0) {
                isValid = true;
                toReturn = fromUser;
            }
        }

        return toReturn;
    }

}
