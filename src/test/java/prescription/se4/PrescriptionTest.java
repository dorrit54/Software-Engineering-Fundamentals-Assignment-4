package prescription.se4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrescriptionTest {
    private Prescription prescription; // 修正類名大小寫一致

    @BeforeEach
    void setUp() {
        prescription = new Prescription(); // 修正類名大小寫一致
    }

    @Test
    void testAddPrescription() {
        // Valid cases
        assertTrue(prescription.addPrescription("John", "Doee", "123 Main St, Suburb, 12345, Australia", -5.50, -2.00, 90, "12/05/23", "Dr. Smith"), "Valid prescription should return true");
        assertTrue(prescription.addPrescription("Emily", "Liao", "456 Elm St, Some City, 67890, Australia", 10.00, -1.00, 45, "01/01/24", "Dr. Amber"), "Valid prescription should return true");

        // Invalid cases
        assertFalse(prescription.addPrescription("Jo", "Li", "789 Oak St, AnyTown, 11223, Australia", 5.00, 2.00, 135, "15/07/22", "Dr. Wang"), "First Name or Last Name length should return false");
        assertFalse(prescription.addPrescription("Amy", "Wu", "789 Oak St, AnyTown, 11223, Australia", 5.00, 2.00, 135, "15/07/22", "Dr. Wang"), "First Name or Last Name length should return false");

        assertFalse(prescription.addPrescription("Jane", "Rogers", "Short Address, Town", -10.50, 1.50, 180, "10/10/21", "Dr. Andrews"), "address length out of range should return false");
        assertFalse(prescription.addPrescription("Jane", "Rogers", "Shorter Address", -10.50, 1.50, 180, "10/10/21", "Dr. Andrews"), "address length out of range should return false");

        assertFalse(prescription.addPrescription("Lisa", "Black", "321 Pine St, BigCity, 99887, Australia", 22.00, 0.00, 90, "05/03/22", "Dr. Brown"), "Cylinder and Axis out of range should return false");
        assertFalse(prescription.addPrescription("George", "Jones", "111 Maple St, NewTown, 44556, Australia", -8.00, -5.00, 200, "20/09/20", "Dr. Paterson"), "Cylinder and Axis out of range should return false");

        assertFalse(prescription.addPrescription("George", "Jones", "111 Maple St, NewTown, 44556, Australia", -8.00, -2.00, 170, "09/20/20", "Dr. Paterson "), "Date format should return false");
        assertFalse(prescription.addPrescription("George", "Jones", "111 Maple St, NewTown, 44556, Australia", -8.00, -2.00, 170, "06/15/20", "Dr. Paterson "), "Date format should return false");

        assertFalse(prescription.addPrescription("George", "Jones", "111 Maple St, NewTown, 44556, Australia", -8.00, -5.00, 200, "20/09/20", "Dr. Wu"), "Optometrist name length should return false");
        assertFalse(prescription.addPrescription("George", "Jones", "111 Maple St, NewTown, 44556, Australia", -8.00, -5.00, 200, "20/09/20", "Dr. Li"), "Optometrist name length should return false");

    }

    @Test
    void testaddRemark() {
        // Valid cases
        assertTrue(prescription.addRemark("This is a valid remark that more than 6 words", "client", 0));
        assertTrue(prescription.addRemark("Another Valid Remark that more than 6 words Here", "optometrist", 1));

        // Invalid cases
        assertFalse(prescription.addRemark("Too short remark", "client", 0));
        assertFalse(prescription.addRemark("Another too short remark", "client", 0));

        assertFalse(prescription.addRemark("This is a very long remark that exceeds the maximum word limit of twenty words I hate my glasses quality because it broke", "client", 0));
        assertFalse(prescription.addRemark("This is another very long remark that exceeds the maximum word limit of twenty words I hate my glasses quality because it broke", "client", 0));

        assertFalse(prescription.addRemark("This is a valid remark that more than 6 words", "not client", 0));
        assertFalse(prescription.addRemark("This is a valid remark that more than 6 words", "also not client", 0));

        assertFalse(prescription.addRemark("This is a valid remark that more than 6 words", "not optometrist", 0));
        assertFalse(prescription.addRemark("This is a valid remark that more than 6 words", "also not optometrist", 0));

        assertFalse(prescription.addRemark("not uppercase remark that more than 6 words", "optometrist", 0));
        assertFalse(prescription.addRemark("also not uppercase remark that more than 6 words", "optometrist", 0));

    }
}
