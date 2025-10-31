package edu.utsa.cs3443.gup636_lab3.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Manages the AidShip class.
 * Helps with reading in the necessary data needed for the
 * AidShip class and helps with preventing errors.
 *
 * @author lauren
 */
public class AidShipManager {
    private ArrayList<AidShip> aidShipList;
    private final String aidShipDataFilename = "data/aid_ships.csv";

    /**
     * Creates an arrayList to carry the AidShip objects.
     */
    public AidShipManager() {
        this.aidShipList = new ArrayList<>();
    }

    /**
     * Checks to see if the arrayList is empty and if nothing exists then
     * it adds the aidShip to the ArrayList.
     *
     * @param aidShip the AidShip object.
     */
    public void addAidShip(AidShip aidShip) {
        if (aidShipList != null) {
            aidShipList.add(aidShip);
        }
    }

    /**
     * Loads in the data from the aid_ships.csv file.
     * Catches errors related to reading the input file.
     * Calls convertLineToAidShip to break apart the data read and
     * if the fields are not null then it calls addAidShip to add the
     * object to the aidShipList.
     */
    public void loadAidShips() {
        Scanner scanner = null;
        getAidShipList().clear();
        try {
            String line;
            AidShip aidShip;
            scanner = new Scanner(new File(aidShipDataFilename));
            if (scanner.next().equals("name")) {
                line = scanner.nextLine(); //Skips header
            }
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                aidShip = convertLineToAidShip(line, ",");
                if(aidShip != null){
                    addAidShip(aidShip);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading aid ship file: " + e.getMessage());
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Locates the aidShip in the aidShipList based off of the registration number.
     *
     * @param registrationNumber the registration number of the ship.
     * @return the aidShip object if it finds a match and if not returns null.
     */
    public AidShip findAidShip(String registrationNumber){
        for(AidShip aidShip : aidShipList) {
            if(aidShip.getRegistrationNumber().equals(registrationNumber)){
                return aidShip;
            }
        }
        return null;
    }

    /**
     * Finds out if the aid ship exists.
     *
     * @param registrationNumber the registration number of the ship.
     * @return true if the aid ship does exist and false if it does not.
     */
    public boolean isAidShipExists(String registrationNumber){
        return findAidShip(registrationNumber) != null;
    }

    /**
     * Gets the array list containing the aid ship objects.
     *
     * @return the array list of aid ships.
     */
    public ArrayList<AidShip> getAidShipList() {
        return aidShipList;
    }

    /**
     * Sets the array list containing the aid ship objects.
     */
    public void setAidShipList(ArrayList<AidShip> aidShipList) { this.aidShipList = aidShipList;}

    /**
     * Updates the aid ship by removing the old version of it and replacing
     * it with the current version.
     * Follows up by saving the new aid ship to the data file.
     *
     * @param aidShip the AidShip object.
     * @return true if the aid ship was able to be updated, and false
     * if not.
     */
    public boolean updateAidShip(AidShip aidShip) {
        for(AidShip a : aidShipList) {
            if(a.getRegistrationNumber().equals(aidShip.getRegistrationNumber())){
                aidShipList.remove(a);
                addAidShip(aidShip);
                saveDataToFile();
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes the aid ship.
     *
     * @param aidShip the AidShip object.
     * @return true if it deleted the aid ship, false if not.
     */
    public boolean deleteAidShip(AidShip aidShip) {
        try {
            aidShipList.remove(aidShip);
            saveDataToFile();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    /**
     * Saves the aid ship objects to the data file if possible.
     */
    private void saveDataToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(aidShipDataFilename));
            bw.write("name,registrationNumber,tonnage,crewSize,currentPort,aidType,suppliesOnBoard,hasHelipad");
            bw.newLine();
            for(AidShip aidShip : aidShipList){
                bw.write(convertAidShipToLine(aidShip, ","));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving aid ship data to file");
        }
    }

    /**
     * Converts the data file lines into different fields containing
     * the aid ships attributes.
     *
     * @param line the current line in the data file.
     * @param delimiter what separates the attributes.
     * @return null if the string does not have the necessary number of attributes
     * for the aid ship, or returns an aid ship object containing the attributes.
     */
    private AidShip convertLineToAidShip(String line, String delimiter) {
        String[] fields = line.split(delimiter);
        if(fields.length != 8){
            return null;
        }
        return new AidShip(fields[0], fields[1], Double.parseDouble(fields[2]), Integer.parseInt(fields[3]), fields[4], fields[5], Integer.parseInt((fields[6])), Boolean.parseBoolean((fields[7])));
    }

    /**
     * Converts the aid ship object into a line.
     *
     * @param aidShip the aid ship object.
     * @param delimiter what separates the attributes.
     * @return a single string representing the aid ships attributes.
     */
    private String convertAidShipToLine(AidShip aidShip, String delimiter) {
        return toString(aidShip, delimiter);
    }

    /**
     * Formats the aid ships data into a single string containing the
     * aid ships objects.
     *
     * @param aidShip the aid ship object.
     * @param delimiter what separates the attributes.
     * @return a formated string with the aid ships attributes.
     */
    public String toString(AidShip aidShip, String delimiter) {
        return aidShip.getName() + delimiter + aidShip.getRegistrationNumber() + delimiter + aidShip.getTonnage() + delimiter + aidShip.getCrewSize() + delimiter + aidShip.getCurrentPort() + delimiter + aidShip.getAidType() + delimiter + aidShip.getSuppliesOnBoard() + delimiter + aidShip.isHasHelipad();
    }
}