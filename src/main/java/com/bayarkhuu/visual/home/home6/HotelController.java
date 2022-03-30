package com.bayarkhuu.visual.home.home6;

import com.bayarkhuu.visual.home.home6.model.*;
import javafx.fxml.FXML;

/**
 * HotelController
 *
 * @author Баярхүү.Лув 2022.03.30 08:41
 */
public class HotelController {

    @FXML
    private void customer() {
        Window<Customer> window = new Window<>("Customers", new Editor("CustomerDesign.fxml", 310, 250), 500, 300);
        window.table.addColumn("Account #", Customer::getId, 70);
        window.table.addColumn("First Name", Customer::getFirstName, 65);
        window.table.addColumn("Last Name", Customer::getLastName, 65);
        window.table.addColumn("Phone #", Customer::getPhone, 80);
        window.table.addColumn("Emergency Name", Customer::getEmergencyName, 100);
        window.table.addColumn("Emergency Phone", Customer::getEmergencyPhone, 100);
    }

    @FXML
    private void occupancy() {
        Window<Occupancy> window = new Window<>("Occupancy", new Editor("OccupancyDesign.fxml", 360, 410), 840, 300);
        window.table.addColumn("Occupancy #", Occupancy::getId, 80);
        window.table.addColumn("Date Occupied", Occupancy::getDate, 150);
        window.table.addColumn("Processed By", Occupancy::getProcessedBy, 140);
        window.table.addColumn("Processed For", Occupancy::getProcessedFor, 140);
        window.table.addColumn("Room Occupied", Occupancy::getRoom, 180);
        window.table.addColumn("Rate Applied", Occupancy::getRateApplied, 80);
    }

    @FXML
    private void room() {
        Window<Room> window = new Window<>("Rooms", new Editor("RoomDesign.fxml", 308, 240), 390, 300);
        window.table.addColumn("Room #", Room::getId, 50);
        window.table.addColumn("Room Type", Room::getType, 100);
        window.table.addColumn("Bed Type", Room::getBedType, 80);
        window.table.addColumn("Rate", Room::getRate, 50);
        window.table.addColumn("Status", Room::getStatus, 65);
    }

    @FXML
    private void payment() {
        Window<Payment> window = new Window<>("Payment", new Editor("PaymentDesign.fxml", 493, 745), 1280, 300);
        window.table.addColumn("Receipt #", Payment::getId, 70);
        window.table.addColumn("Processed By", Payment::getProcessedBy, 140);
        window.table.addColumn("Payment Date", Payment::getDate, 130);
        window.table.addColumn("Processed For", Payment::getProcessedFor, 140);
        window.table.addColumn("First Day Occupied", Payment::getFirstDayOccupied, 140);
        window.table.addColumn("Last Day Occupied", Payment::getLastDayOccupied, 140);
        window.table.addColumn("Total Nights", Payment::getTotalNight, 70);
        window.table.addColumn("Phone Use", Payment::getPhoneUse, 75);
        window.table.addColumn("Amt Charged", Payment::getAmtCharged, 65);
        window.table.addColumn("Sub-Total", Payment::getSubTotal, 70);
        window.table.addColumn("Tax Rate", Payment::getTaxRate, 70);
        window.table.addColumn("Tax Amt", Payment::getTaxAmt, 100);
        window.table.addColumn("Amt Paid", Payment::getAmtPaid, 55);
    }

    @FXML
    private void employee() {
        Window<Employee> window = new Window<>("Employee", new Editor("EmployeeDesign.fxml", 311, 210), 365, 300);
        window.table.addColumn("Employee #", Employee::getId, 70);
        window.table.addColumn("First Name", Employee::getFirstName, 80);
        window.table.addColumn("Last Name", Employee::getLastName, 80);
        window.table.addColumn("Title", Employee::getTitle, 120);
    }

    @FXML
    private void close() {
        System.exit(0);
    }

}
