package prescription.se4;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    private int prescID;
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float axis;
    private float cylinder;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = {"Client", "Optometrist"};
    private ArrayList<String> postRemarks = new ArrayList<>();

    public boolean addPrescription(String firstName, String lastName, String address, double sphere, double cylinder, int axis, String examDate, String optometrist) {
        // Condition 1: First Name length check
        if ((firstName.length() < 4 || firstName.length() > 15) || (lastName.length() < 4 || lastName.length() > 15)) {
            System.out.println("Fail: First Name or Last Name length check");
            return false;
        }
        // Condition 2: Address length check
        if (address.length() < 20) {
            //System.out.println("Fail: Address length check");
            return false;
        }
        // Condition 3: Sphere validation
        if (sphere < -20.00 || sphere > 20.00) {
            System.out.println("Fail: Sphere validation");
            return false;
        }
        //Condition 4: Cylinder and Axis validation
        if (cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            System.out.println("Fail: Cylinder or Axis validation");
            return false;
        }
        // Condition 5: Date format check
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(examDate);
            String reformattedDate = sdf.format(date);
            if (!examDate.equals(reformattedDate)) {
                //System.out.println("Fail: Date format check");
                return false;
            }
        } catch (ParseException e) {
            //System.out.println("Fail: Date parse exception");
            return false;
        }
        // Condition 6: Optometrist name length check
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            //System.out.println("Fail: Optometrist name length check");
            return false;
        }
        // If all conditions are met, save the prescription to TXT file
        String prescriptionDetails = String.format("%s %s, Address: %s, Sphere: %.2f, Cylinder: %.2f, Axis: %d, Date: %s, Optometrist: %s",
                firstName, lastName, address, sphere, cylinder, axis, examDate, optometrist);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("presc.txt", true))) {
            writer.write(prescriptionDetails);
            writer.newLine();
            System.out.println("Success: Prescription saved");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to add a remark
    public boolean addRemark(String remark, String category, int currentRemarkCount) {
        // Condition 1: Check remark word count < 6
        String[] words = remark.split("\\s+");
        if (words.length < 6) {
            System.out.println("Fail: Remark word count is less than 6");
            return false;
        }
        // Condition 2: Check remark word count > 20
        if (words.length > 20) {
            System.out.println("Fail: Remark word count is greater than 20");
            return false;
        }
        // Condition 3: Check remark capitalization
        if (!Character.isUpperCase(words[0].charAt(0))) {
            //System.out.println("Fail: Remark capitalization check");
            return false;
        }
        // Condition 4: Check if the category is valid
        if (!category.equals("client") && !category.equals("optometrist")) {
            //System.out.println("Fail: Remark category check");
            return false;
        }
        // Condition 5: Check if there are already 2 remarks
        if (currentRemarkCount >= 2) {
            //System.out.println("Fail: Too many remarks");
            return false;
        }
        // If all conditions are met, save the remark to TXT file
        String remarkDetails = String.format("Category: %s, Remark: %s", category, remark);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("remark.txt", true))) {
            writer.write(remarkDetails);
            writer.newLine();
            System.out.println("Success: Remark saved");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
