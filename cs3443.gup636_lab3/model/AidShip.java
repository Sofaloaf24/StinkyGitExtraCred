package edu.utsa.cs3443.gup636_lab3.model;

/**
 * Special type of ship providing emergency support.
 * Inherits the basic ship attributes and behaviors from the Ship class and adds fields.
 *
 * Implements the EmergencySupport interface.
 *
 * @author lauren
 */
public class AidShip extends Ship implements EmergencySupport {
    private String aidType;
    private int suppliesOnBoard;
    private boolean hasHelipad;

    /**
     * Initializes all instance variables that define an aid ship including the generalized variables.
     *
     * @param name The name of the ship.
     * @param registrationNumber The registration number of the ship.
     * @param tonnage The weight capacity of the ship.
     * @param crewSize The ship's crew size.
     * @param currentPort The port that the ship is currently at.
     * @param aidType The type of aid given.
     * @param suppliesOnBoard The number of supplies on board.
     * @param hasHelipad States if there is a helipad on the ship.
     */
    public AidShip(String name, String registrationNumber, double tonnage, int crewSize, String currentPort, String aidType, int suppliesOnBoard, boolean hasHelipad) {
        super(name, registrationNumber, tonnage, crewSize, 0, currentPort);
        this.aidType = aidType;
        this.suppliesOnBoard = suppliesOnBoard;
        this.hasHelipad = hasHelipad;
    }

    /**
     * Returns the type of aid the ship has on board.
     *
     * @return the type of aid the ship has.
     */
    public String getAidType() {
        return aidType;
    }

    /**
     * Returns the number of supplies on board the ship.
     *
     * @return the number of supplies the ship has.
     */
    public int getSuppliesOnBoard() {
        return suppliesOnBoard;
    }

    /**
     * Returns true if the ship has a helipad and false if it doesn't.
     *
     * @return true or false for if a helipad is on the ship
     */
    public boolean isHasHelipad() {
        return hasHelipad;
    }

    /**
     * Sets the type of aid the ship provides.
     *
     * @param aidType type of aid on the ship.
     */
    public void setAidType(String aidType) {
        this.aidType = aidType;
    }

    /**
     * Sets the amount of supplies that are onboard the ship.
     *
     * @param suppliesOnBoard the amount of supplies that the ship has.
     */
    public void setSuppliesOnBoard(int suppliesOnBoard) {
        this.suppliesOnBoard = suppliesOnBoard;
    }

    /**
     * Sets whether the ship has a helipad.
     *
     * @param hasHelipad if the ship has a helipad or not.
     */
    public void setHasHelipad(boolean hasHelipad) {
        this.hasHelipad = hasHelipad;
    }

    /**
     * Resets the number of supplies on board the ship to 0.
     */
    private void unloadSupplies() {
        this.suppliesOnBoard = 0;
    }

    /**
     * Docks the ship resetting the ships speed and sets the new current port.
     *
     * @param currentPort the current port that the ship is located at.
     */
    @Override
    public void dock(String currentPort) {
        super.dock(currentPort);
        unloadSupplies();
    }

    /**
     * Provides the last half of the Emergency Readiness Reports.
     * If supplies on board are equal to 0 the ship has already deployed and pulls the overview.
     * If it hasn't deployed its aid then have the deployment aid report.
     *
     * @return either the responder details or the deployment details based on the current status.
     */
    @Override
    public String toString() {
        return "Aid " + super.toString() + ", aidType=" + getAidType() + ", suppliesOnBoard=" + getSuppliesOnBoard() + ", hasHelipad=" + isHasHelipad() + " ]";
    }

    /**
     * Resets the number of supplies on board to 0.
     */
    @Override
    public void deployAid() {
        System.out.print("Aid Ship " + super.getName() + " is deploying "
                + getSuppliesOnBoard() + " units of " + getAidType() + " aid.\n");
        unloadSupplies();
    }

    /**
     * Returns details about the ship in a string form.
     *
     * @return a string containing the ship's details.
     */
    @Override
    public String getEmergencyReadinessReport() {
        String x = "Not Available";
        if (hasHelipad == true) {
            x = "Available";
        }
        return "Aid Ship Emergency Report:\n" +
                "Name: " + super.getName() +
                "\nAid Type: " +  getAidType() +
                "\nSupplies on Board: " +  getSuppliesOnBoard() +
                "\nHelipad: " + x + "\n=======================================";
    }
}
