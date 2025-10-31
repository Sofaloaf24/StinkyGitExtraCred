package edu.utsa.cs3443.gup636_lab3;

import edu.utsa.cs3443.gup636_lab3.model.AidShip;
import edu.utsa.cs3443.gup636_lab3.model.AidShipManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

/**
 * Controller class for the main screen of the application.
 *
 * This class handles user interactions with the JavaFX UI components, including listing
 * for the users text input, finding aid ships, and deleting aid ships.
 *
 * @author lauren
 */
public class MainScreenController {

    /** Button to list all registered aid ships. */
    @FXML
    public Button list_Ship_Button;

    /** Text area for entering aid ship’s registration number. */
    @FXML
    public TextArea registration_Area;

    /** Button used to start finding or deleting a ship. */
    @FXML
    public Button go_Button;

    /** Apps organization label */
    @FXML
    public Label app_label;

    /** Ships photo */
    @FXML
    public ImageView logo;

    /** Copyright Label */
    @FXML
    public Label copyrightText;

    /** Radio button to select the "Delete" option. */
    @FXML
    private RadioButton findRadio, deleteRadio;

    /** Text area for displaying the results or messages to the user. */
    @FXML
    private TextArea screen;

    /** Boolean to see if registration text box has content. */
    @FXML
    private boolean registrationBox = false;

    /** Manages the collection of AidShip objects. */
    @FXML
    private AidShipManager coordinator = new AidShipManager();

    /**
     * Initializes the controller.
     *
     * Loads the aid ships.
     * Sets a listener for the registration input area.
     */
    @FXML
    public void initialize() {
        coordinator.loadAidShips();
        registration_Area.textProperty().addListener((observable, oldValue, newValue) -> {
            registrationBox = !newValue.trim().isEmpty(); // true if user typed something
        });
    }

    /**
     * Handles when the "List Ships" button is clicked.
     *
     * Displays info of all aid ships in data file.
     * If ships aren't available, displays message.
     *
     * @param event Triggered by clicking the button.
     */
    @FXML
    protected void listShipsClicked(ActionEvent event) {
        ArrayList<AidShip> aidShipList = coordinator.getAidShipList();

        StringBuilder sb = new StringBuilder();
        sb.append("\nAidShip List has ").append(aidShipList.size()).append(" responders");

        String divider = "_".repeat(151);
        sb.append("\n").append(divider).append("\n");

        sb.append(String.format("\n|%-15s", "Name"));
        sb.append(String.format("|%-20s", "Registration Number"));
        sb.append(String.format("|%-12s", "Tonnage"));
        sb.append(String.format("|%-12s", "Crew Size"));
        sb.append(String.format("|%-25s", "Current Port"));
        sb.append(String.format("|%-25s", "Aid Type"));
        sb.append(String.format("|%-20s", "Supplies on Board"));
        sb.append(String.format("|%-12s", "Helipad"));
        sb.append("\n").append(divider).append("\n");

        if (aidShipList.isEmpty()) {
            sb.append("\n\tNo responders available in the system!\n");
        } else {
            for (AidShip ship : aidShipList) {
                sb.append(String.format("|%-15s", ship.getName()));
                sb.append(String.format("|%-20s", ship.getRegistrationNumber()));
                sb.append(String.format("|%-12.2f", ship.getTonnage()));
                sb.append(String.format("|%-12d", ship.getCrewSize()));
                sb.append(String.format("|%-25s", ship.getCurrentPort()));
                sb.append(String.format("|%-25s", ship.getAidType()));
                sb.append(String.format("|%-20d", ship.getSuppliesOnBoard()));
                sb.append(String.format("|%-12s", ship.isHasHelipad() ? "Available" : "Not Available"));
                sb.append("\n");
            }
        }

        sb.append(divider);

        // Display in TextArea
        screen.setText(sb.toString());
    }

    /**
     * Handles when the "Go" button is clicked.
     *
     * Finds and displays details of an aid ship or,
     * Deletes an aid ship from the system.
     * Validates user input/option clicked.
     *
     * @param event Triggered by clicking the "Go" button.
     */
    @FXML
    protected void goClicked(ActionEvent event) {
        if (!registrationBox) {
            screen.setText("Enter a valid aid ship registration number.");
        } else if ((!findRadio.isSelected() && !deleteRadio.isSelected())) {
            screen.setText("Select an option.");
        }
        else if ((findRadio.isSelected() && !deleteRadio.isSelected())) {
            if (coordinator.isAidShipExists(registration_Area.getText())) {
                AidShip aidShip = coordinator.findAidShip(registration_Area.getText());
                StringBuilder sb = new StringBuilder("");
                sb.append("Aid Ship Card:\n");
                sb.append("_".repeat(50));
                sb.append(String.format("\nName:\t\t     %s", aidShip.getName()));
                sb.append(String.format("\nRegistration Number: %s", aidShip.getRegistrationNumber()));
                sb.append(String.format("\nTonnage: \t     %.2f", aidShip.getTonnage()));
                sb.append(String.format("\nCrew Size: \t     %d", aidShip.getCrewSize()));
                sb.append(String.format("\nCurrent Port: \t     %s", aidShip.getCurrentPort()));
                sb.append(String.format("\nAid Type: \t     %s", aidShip.getAidType()));
                sb.append(String.format("\nSupplies On Board:   %d", aidShip.getSuppliesOnBoard()));
                sb.append(String.format("\nHelipad:   \t     %s\n", aidShip.isHasHelipad() ? "Available" : "Not Available"));
                sb.append("_".repeat(50));
                screen.setText(sb.toString());
            }
            else {
                screen.setText("Registration number does not exist.");
            }

        }
        else if ((!findRadio.isSelected() && deleteRadio.isSelected())) {
            if (coordinator.isAidShipExists(registration_Area.getText())) {
                AidShip aidShip = coordinator.findAidShip(registration_Area.getText());
                coordinator.deleteAidShip(aidShip);
            }
        }
    }
}
