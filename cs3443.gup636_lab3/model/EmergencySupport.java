package edu.utsa.cs3443.gup636_lab3.model;

/**
 * This interface defines the units capable of providing aid.
 * Specifies methods for deploying units and retrieving Emergency Readiness Report.
 * @author lauren
 */
public interface EmergencySupport {

    /**
     * Deploys aid.
     * Resets the number of responder units/supplies to 0.
     */
    void deployAid();

    /**
     * Retrieves the current emergency readiness status of the vehicle.
     *
     * @return A string that provides an overview of the vehicle's status and what is occurring.
     */
    String getEmergencyReadinessReport();
}
