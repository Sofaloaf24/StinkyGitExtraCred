package edu.utsa.cs3443.gup636_lab3.model;

/**
 * This interface defines vehicles that can move/navigate.
 * Specifies methods for navigating and setting/getting the current speed of a vehicle.
 *
 * @author lauren
 */
public interface Navigable {

    /**
     * Navigates the vehicle to a specific destination.
     *
     * @param destination The location the vehicle is set to go.
     */
    void navigateTo(String destination);

    /**
     * Sets the speed of the vehicle.
     *
     * @param speed The new speed of the vehicle.
     */
    void setCurrentSpeed(double speed);

    /**
     * Returns the speed of the vehicle.
     *
     * @return The current speed of the vehicle.
     */
    double getCurrentSpeed();
}
