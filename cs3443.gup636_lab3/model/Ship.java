package edu.utsa.cs3443.gup636_lab3.model;

/**
 * Abstract class for representing ships.
 * Provides the common attributes and abstract methods for general ships.
 *
 * @author lauren
 * @see AidShip
 */
public abstract class Ship implements Navigable {
    private String name;
    private String registrationNumber;
    private double tonnage;
    private int crewSize;
    private double currentSpeed;
    private String currentPort;

    /**
     * Initializes all instance variables used in generalized versions of ships.
     *
     * @param name The name of the ship.
     * @param registrationNumber The ship's registration number.
     * @param tonnage The ships tonnage capacity.
     * @param crewSize The size of the crew on the ship.
     * @param currentSpeed The ship's current speed.
     * @param currentPort The current port that the ship is at.
     */
    public Ship(String name, String registrationNumber, double tonnage, int crewSize, double currentSpeed, String currentPort) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.tonnage = tonnage;
        this.crewSize = crewSize;
        this.currentSpeed = currentSpeed;
        this.currentPort = currentPort;
    }

    /**
     * Returns the name of the ship.
     *
     * @return the ship's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the registration number of the ship.
     *
     * @return the ship's registration number.
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * Returns the tonnage of the ship.
     *
     * @return the ship's tonnage.
     */
    public double getTonnage() {
        return tonnage;
    }

    /**
     * Returns the size of the crew for the ship.
     *
     * @return the ship's crews size.
     */
    public int getCrewSize() {
        return crewSize;
    }

    /**
     * Returns the current speed of the ship.
     *
     * @return the ship's current speed.
     */
    @Override
    public double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * Returns the current port that the ship is located at.
     *
     * @return the ship's current location (port).
     */
    public String getCurrentPort() {
        return currentPort;
    }

    /**
     * Sets the name of the ship.
     *
     * @param name the new name of the ship.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the registration number of the ship.
     *
     * @param registrationNumber the new registration number of the ship.
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * Sets the tonnage of the ship.
     *
     * @param tonnage the new tonnage of the ship.
     */
    public void setTonnage(double tonnage) {
        this.tonnage = tonnage;
    }

    /**
     * Sets the crew size of the ship.
     *
     * @param crewSize the new crew size of the ship.
     */
    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }

    /**
     * Sets the current speed of the ship.
     *
     * @param currentSpeed the new current speed of the ship.
     */
    @Override
    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    /**
     * Sets the current port the ship is at.
     *
     * @param currentPort the new port that the ship is at.
     */
    public void setCurrentPort(String currentPort) {
        this.currentPort = currentPort;
    }

    /**
     * Changes the ships current port to the new one and resets the current speed to 0 since it has stopped.
     *
     * @param currentPort the current port that the ship is located at.
     */
    public void dock(String currentPort) {
        setCurrentPort(currentPort);
        setCurrentSpeed(0.0);
    }

    /**
     * Returns a string of the Ship object's attributes.
     *
     * @return a string containing the ship's name, registration number, tonnage, crew size, current speed, and current port.
     */
    @Override
    public String toString() {
        return "Ship [name=" + getName() + ", registrationNumber=" + getRegistrationNumber() + ", tonnage=" +
                getTonnage() + ", crewSize=" + getCrewSize() + ", currentSpeed=" + getCurrentSpeed() + ", currentPort="
                + getCurrentPort();
    }

    /**
     * Navigates the ship to the next destination setting it as the new port.
     *
     * @param destination The location the vehicle is set to go.
     */
    @Override
    public void navigateTo(String destination) {
        System.out.println(getName() + " is navigating from " + getCurrentPort() + " to " + destination + ".");
        setCurrentSpeed(20); // Random speed
        setCurrentPort(destination);
    }
}
