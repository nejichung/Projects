/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import DTOs.Dvd; // imported from DVD.java
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Software Guld
 */
public class FileDao implements DvdDao {

    String path; // constructor

    public FileDao(String path) {
        this.path = path;
    }

    @Override
    public Dvd addDvd(Dvd toAdd) {

        Dvd toReturn = toAdd;

        List<Dvd> allDvds = listDvds();

        int newId = genNewId(allDvds);

        toAdd.setId(newId);

        allDvds.add(toAdd);

        boolean success = writeFile(allDvds);

        if (!success) {
            toReturn = null;
        }

        return toReturn;

    }

    @Override
    public boolean removeDvd(int id) {

        List<Dvd> allDvds = listDvds();  // get all dvds
        int index = Integer.MIN_VALUE; // setting the index int to a value that's not possible

        for (int i = 0; i < allDvds.size(); i++) { // remove the matching dvd from the list
            Dvd toCheck = allDvds.get(i);  // doing a for loop to look for a matching id
            if (toCheck.getId() == id) {
                //we found a match
                //record the index
                //break the loop
                index = i;
                break;
            }
        }

        allDvds.remove(index);    //remove the id??
        boolean success = writeFile(allDvds);

        return success; //return success
    }

    @Override
    public List<Dvd> listDvds() {// nothing here since we want all dvds

        List<Dvd> dvdList = new ArrayList(); // our list dvdlist object

        try {
            File dvdFile = new File(path);

            if (!dvdFile.exists()) {
                dvdFile.createNewFile();
            }

            Scanner reader = new Scanner( // scanner called reader
                    new BufferedReader(
                            new FileReader(dvdFile)));

            while (reader.hasNextLine()) {   // no header row, start reading the data immediately

                String line = reader.nextLine();   //ID::Title::Director::Studio::Rating::Year::Note

                String[] gible = line.split("::");

                Dvd dvd2 = new Dvd();
                dvd2.setId(Integer.parseInt(gible[0])); // the first item in the array is gonnna transfer into an int an id
                dvd2.setTitle(gible[1]); // the 2nd item in the array is going to be title
                dvd2.setDirector(gible[2]);
                dvd2.setStudio(gible[3]);
                dvd2.setRating(gible[4]);
                dvd2.setReleaseYear(Integer.parseInt(gible[5]));
                dvd2.setNote(gible[6]);
                dvdList.add(dvd2); // addding dvd2 to our list
            }
        } catch (FileNotFoundException e) {
            dvdList = null;
        } catch (IOException e) {
            dvdList = null;

        }
        return dvdList;

    }
//
//    public Dvd getDvd(String title) {
//    }

    private int genNewId(List<Dvd> allDvds) {
        int genNum = Integer.MIN_VALUE; // this will give us an Id number that can't be used already

        if (allDvds.isEmpty()) {
            genNum = 0;
        } else {
            for (Dvd toInspect : allDvds) {
                if (toInspect.getId() > genNum) {
                    genNum = toInspect.getId();
                }
            }

            genNum++;
        }

        return genNum;
    }

    private boolean writeFile(List<Dvd> allDvds) {

        boolean success = false;

        try {  // try says to run these lines of code, if the catch if an exception is thrown
            PrintWriter pw = new PrintWriter(new FileWriter(path));

            for (Dvd toWrite : allDvds) {
                String line = dvdToLine(toWrite);
                pw.println(line);
            }

            pw.flush();
            pw.close();

            success = true;
        } catch (IOException e) { // on failure, leave success as false

        }

        return success;
    }

    private String dvdToLine(Dvd toWrite) {
        //ID::Title::Director::Studio::Rating::Year::Note

        String toReturn
                = toWrite.getId() + "::"
                + toWrite.getTitle() + "::"
                + toWrite.getDirector() + "::"
                + toWrite.getStudio() + "::"
                + toWrite.getRating() + "::"
                + toWrite.getReleaseYear() + "::"
                + toWrite.getNote();

        return toReturn;
    }

    @Override
    public Dvd getDvdById(int id) {
        Dvd toReturn = null;

        List<Dvd> allDvds = listDvds();

        for (Dvd toCheck : allDvds) {
            if (toCheck.getId() == id) {
                toReturn = toCheck;
                break;
            }
        }

        return toReturn;

    }

    @Override
    public Dvd editDvd(Dvd toEdit) {
        Dvd toReturn = null;

        List<Dvd> allDvds = listDvds();

        //2. remove the matching dvd from the list
        int index = Integer.MIN_VALUE;

        int id = toEdit.getId();

        for (int i = 0; i < allDvds.size(); i++) {
            Dvd toCheck = allDvds.get(i);
            if (toCheck.getId() == id) { // if the ids match
                //record the index
                //break the loop
                index = i;
                break;
            }
        }

        if (index >= 0 && index < allDvds.size()) {
            allDvds.set(index, toEdit);

            boolean success = writeFile(allDvds);
            if (success) {
                toReturn = toEdit;
            }
        }

        return toReturn;
    }
}
