package Inventory;

/**
 * The {@code DispensingService} interface defines methods for dispensing items in an inventory system.
 * 
 * <p>
 * This interface includes functionality for dispensing items based on appointment outcomes or by specifying
 * medicine details such as name and quantity. Implementations of this interface should manage inventory
 * adjustments, ensure accurate tracking of dispensed items, and maintain proper inventory levels.
 * </p>
 * 
 * <p>
 * Designed to integrate with inventory management systems, this interface ensures the dispensing process
 * is streamlined and supports effective stock monitoring.
 * </p>
 * 
 * @author Sia Yi Zhen
 * @version 1.0
 * @since 2024-11-5
 */

public interface DispensingService {
    /**
     * Dispenses an item based on an appointment outcome ID.
     * Implementing classes should update the record associated with the
     * appointment to indicate the item has been dispensed.
     * 
     * @param appointmentOutcomeID The unique identifier associated with the outcome of an appointment.
     *                             This ID is used to retrieve and dispense the corresponding item.
     */
    void dispenseItemByAppointmentID(String appointmentOutcomeID);
    /**
     * Dispenses a specified quantity of a medicine by its name.
     * Implementing classes should handle inventory updates to reflect
     * the dispensed quantity and check if the stock is low.
     * 
     * @param medicineName The name of the medicine to be dispensed.
     * @param quantity     The number of units of the specified medicine to be dispensed.
     * @return {@code true} if the specified quantity of medicine was successfully dispensed;
     *         {@code false} if the quantity is unavailable or if there is an error in dispensing.
     */
    boolean dispenseItemByMedicineName(String medicineName, int quantity);
    /**
     * Displays all appointments related to the inventory dispensing system.
     * 
     * <p>
     * This method lists all the appointments, including their associated details, to
     * facilitate tracking and management of the dispensing process.
     * </p>
     */
    void showAllAppointments();
}
