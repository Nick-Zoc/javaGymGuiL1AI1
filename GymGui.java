// importing UIManager to change the theme or also called Look and feel (L&F) of the java GUI
import javax.swing.UIManager;

// importing File handling requirements
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// Importing Array List
import java.util.ArrayList; // For the member list

//Importing Swing COmponents
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane; //for making tabs of registration form for PRemium and Regular member
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup; //for making group of radio buton
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane; // Needed for dialog popups
import javax.swing.BorderFactory; //for making Border on panels
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

// Importing AWT components
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener; // For event handling
import java.awt.event.ActionEvent; // For event handling

public class GymGui implements ActionListener {
    // Instance Variables for GUI Components
    private JFrame frame;

    // For Panels
    private JPanel panelRegistration, panelActions, panelMemberActions, panelGeneralActions;

    // Registration Tabbed Pane and Panels
    private JTabbedPane tabbedPaneRegistration;
    private JPanel panelRegularTab, panelPremiumTab;

    // Label for Common fields
    private JLabel labelTitle, labelId, labelName, labelLocation, labelPhone, labelEmail, labelGender, labelDob, labelStartDate;

    // Label for Action Category
    private JLabel labelRegActions, labelPremActions;

    // Text Feilds (common)
    private JTextField textFieldId, textFieldName, textFieldLocation, textFieldPhone, textFieldEmail;

    // TextFields (Regular Tab)
    private JTextField textFieldReferralSource, textFieldRegPlanPrice, textFieldRemovalReason;
    private JLabel labelReferralSource, labelRegPlanPrice, labelRemovalReason;

    // TextFields (Premium Tab)
    private JTextField textFieldPaidAmount, textFieldTrainerName, textFieldDiscountAmount, textFieldPremPlanCharge;
    private JLabel labelPaidAmount, labelTrainerName, labelDiscountAmount, labelPremPlanCharge, labelChoosePlan;

    // Radio Button and button group for Gender select
    private JRadioButton radioButtonMale, radioButtonFemale;
    private ButtonGroup buttonGroupGender;

    // Combo boxes
    private JComboBox<String> comboBoxPlan;
    private JComboBox<String> comboBoxDobDay, comboBoxDobMonth, comboBoxDobYear;
    private JComboBox<String> comboBoxStartDateDay, comboBoxStartDateMonth, comboBoxStartDateYear;

    // All Action buttons
    private JButton buttonAddRegular, buttonAddPremium, buttonRevertRegular, buttonRevertPremium, buttonUpgradePlan, buttonPayDueAmount;
    private JButton buttonActivate, buttonDeactivate, buttonMarkAttendancePremium, buttonMarkAttendanceRegular,buttonDisplay, buttonClear, buttonSaveFile, buttonReadFile, buttonCalculateDiscount;

    // Arrays
    // Array for Plan ComboBox items
    static String[] plans = { "Basic", "Standard", "Deluxe" };

    // Array for DOB
    String[] daysDob = new String[31];
    String[] monthsDob = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    String[] yearsDob = new String[76];

    // Array for Membership Start date
    String[] daysStart = new String[31];
    String[] monthsStart = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    String[] yearsStart = new String[11];

    // ArrayList to store GymMember objects
    private ArrayList<GymMember> gymMembers;

    // Constructor to build the GUI
    public GymGui() {
        // 1. Configuring the main frame
        frame = new JFrame("Gym Membership Management System");
        frame.getContentPane().setBackground(new Color(236, 240, 243, 255));

        // Initialize the ArrayList
        gymMembers = new ArrayList<>();

        // Creating Panels
        panelRegistration = new JPanel();
        panelActions = new JPanel();
        panelMemberActions = new JPanel();
        panelGeneralActions = new JPanel();

        // Panels for Tabs
        panelRegularTab = new JPanel();
        panelPremiumTab = new JPanel();

        // Tabbed Pane
        tabbedPaneRegistration = new JTabbedPane();

        // 3. Instantiating the Components, Setting Properties & Bounds
        // Main Title Label (on Frame)
        labelTitle = new JLabel("Gym Membership Management System");
        labelTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        labelTitle.setBounds(500, 15, 500, 30);

        // Registration Panel Components
        // Row 1
        labelId = new JLabel("ID:");
        labelId.setBounds(20, 40, 150, 25);
        textFieldId = new JTextField();
        textFieldId.setBounds(160, 40, 200, 25);

        labelName = new JLabel("Name:");
        labelName.setBounds(380, 40, 100, 25);
        textFieldName = new JTextField();
        textFieldName.setBounds(530, 40, 200, 25);

        // Row 2
        labelLocation = new JLabel("Location:");
        labelLocation.setBounds(20, 90, 150, 25);
        textFieldLocation = new JTextField();
        textFieldLocation.setBounds(160, 90, 200, 25);

        labelPhone = new JLabel("Phone:");
        labelPhone.setBounds(380, 90, 100, 25);
        textFieldPhone = new JTextField();
        textFieldPhone.setBounds(530, 95, 200, 25);

        // Row 3
        labelEmail = new JLabel("Email:");
        labelEmail.setBounds(20, 150, 150, 25);
        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(160, 150, 200, 25);

        labelGender = new JLabel("Gender:");
        labelGender.setBounds(380, 150, 100, 25);
        radioButtonMale = new JRadioButton("Male");
        radioButtonMale.setBounds(525, 150, 60, 25);
        radioButtonFemale = new JRadioButton("Female");
        radioButtonFemale.setBounds(600, 150, 80, 25);

        // Gender ButtonGroup
        buttonGroupGender = new ButtonGroup();
        buttonGroupGender.add(radioButtonMale);
        buttonGroupGender.add(radioButtonFemale);

        // Row 4 (DOB)
        labelDob = new JLabel("DOB:");
        labelDob.setBounds(20, 205, 150, 25);
        // Day ComboBox for DOB
        for (int i = 1; i <= 31; i++)
            daysDob[i - 1] = String.valueOf(i);
        comboBoxDobDay = new JComboBox<>(daysDob);
        comboBoxDobDay.setBounds(160, 205, 50, 25);
        // Month ComboBox for DOB
        comboBoxDobMonth = new JComboBox<>(monthsDob);
        comboBoxDobMonth.setBounds(215, 205, 60, 25);
        // Year ComboBox for DOB
        for (int i = 0; i < 76; i++){
            yearsDob[i] = String.valueOf(1950 + i);
        }
        comboBoxDobYear = new JComboBox<>(yearsDob);
        comboBoxDobYear.setBounds(280, 205, 75, 25);
        // Row 4 (Membership Start Date)
        labelStartDate = new JLabel("Membership Start Date:");
        labelStartDate.setBounds(380, 205, 150, 25);
        // Day ComboBox for Start Date
        for (int i = 1; i <= 31; i++){
            daysStart[i - 1] = String.valueOf(i);
        }

        comboBoxStartDateDay = new JComboBox<>(daysStart);
        comboBoxStartDateDay.setBounds(530, 205, 50, 25);
        // Month ComboBox for Start Date
        comboBoxStartDateMonth = new JComboBox<>(monthsStart);
        comboBoxStartDateMonth.setBounds(585, 205, 60, 25);
        // Year ComboBox for Start Date
        for (int i = 0; i < 11; i++){
            yearsStart[i] = String.valueOf(2015 + i);
        }
        comboBoxStartDateYear = new JComboBox<>(yearsStart);
        comboBoxStartDateYear.setBounds(650, 205, 75, 25);

        // Tabbed Pane Components
        // Regular Member Tab Components
        labelReferralSource = new JLabel("Referral Source:");
        labelReferralSource.setBounds(20, 30, 150, 25);
        textFieldReferralSource = new JTextField();
        textFieldReferralSource.setBounds(160, 30, 200, 25);

        labelChoosePlan = new JLabel("Choose Plan:");
        labelChoosePlan.setBounds(380, 30, 100, 30);
        comboBoxPlan = new JComboBox<>(plans);
        comboBoxPlan.setBounds(525, 30, 170, 30);

        labelRegPlanPrice = new JLabel("Regular plan price:");
        labelRegPlanPrice.setBounds(20, 90, 150, 25);
        textFieldRegPlanPrice = new JTextField("6500.0");
        textFieldRegPlanPrice.setBounds(160, 90, 200, 25);
        textFieldRegPlanPrice.setEditable(false);
        textFieldRegPlanPrice.setBackground(Color.LIGHT_GRAY);

        labelRemovalReason = new JLabel("Removal Reason:");
        labelRemovalReason.setBounds(380, 90, 150, 25);
        textFieldRemovalReason = new JTextField();
        textFieldRemovalReason.setBounds(525, 90, 170, 25);

        // Premium Member Tab Components
        labelPaidAmount = new JLabel("Paid Amount:");
        labelPaidAmount.setBounds(20, 30, 150, 25);
        textFieldPaidAmount = new JTextField();
        textFieldPaidAmount.setBounds(160, 30, 200, 25);

        labelTrainerName = new JLabel("Trainer's Name:");
        labelTrainerName.setBounds(380, 30, 150, 25);
        textFieldTrainerName = new JTextField();
        textFieldTrainerName.setBounds(530, 30, 170, 25);

        labelPremPlanCharge = new JLabel("Premium plan charge:");
        labelPremPlanCharge.setBounds(20, 90, 150, 25);
        textFieldPremPlanCharge = new JTextField("50000.0");
        textFieldPremPlanCharge.setBounds(160, 90, 200, 25);
        textFieldPremPlanCharge.setEditable(false);
        textFieldPremPlanCharge.setBackground(Color.LIGHT_GRAY);

        labelDiscountAmount = new JLabel("Discount amount:");
        labelDiscountAmount.setBounds(380, 90, 120, 25);
        textFieldDiscountAmount = new JTextField("0.0");
        textFieldDiscountAmount.setBounds(530, 90, 170, 25);
        textFieldDiscountAmount.setEditable(false);
        textFieldDiscountAmount.setBackground(Color.LIGHT_GRAY);

        // Actions Panel Components

        // Components for Nested panel MemberActions
        // Sub-section Titles
        labelRegActions = new JLabel("Regular member Actions");
        labelRegActions.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelRegActions.setBounds(20, 25, 200, 25);

        labelPremActions = new JLabel("Premium member Actions");
        labelPremActions.setFont(new Font("Segoe UI", Font.BOLD, 12));
        labelPremActions.setBounds(355, 25, 200, 25);

        // Buttons Row 1
        buttonAddRegular = new JButton("Add Regular Member");
        buttonAddRegular.setBounds(20, 60, 200, 30);

        buttonAddPremium = new JButton("Add Premium Member");
        buttonAddPremium.setBounds(355, 60, 200, 30);

        // Buttons Row 2
        buttonRevertRegular = new JButton("Revert Regular Member");
        buttonRevertRegular.setBounds(20, 110, 200, 30);

        buttonRevertPremium = new JButton("Revert Premium Member");
        buttonRevertPremium.setBounds(355, 110, 200, 30);

        // Buttons Row 3
        buttonMarkAttendanceRegular = new JButton("Mark RM Attendance");
        buttonMarkAttendanceRegular.setBounds(20, 160, 200, 30);

        buttonMarkAttendancePremium = new JButton("Mark PM Attendance");
        buttonMarkAttendancePremium.setBounds(355, 160, 200, 30);

        // Buttons Row 4
        buttonUpgradePlan = new JButton("Upgrade Plan");
        buttonUpgradePlan.setBounds(20, 210, 200, 30);

        buttonPayDueAmount = new JButton("Pay Due Amount");
        buttonPayDueAmount.setBounds(355, 210, 200, 30);

        // Buttons Row 5
        buttonCalculateDiscount = new JButton("Calculate Discount");
        buttonCalculateDiscount.setBounds(355, 260, 200, 30);

        // Components for Nested panel GeneralActions
        // Buttons Row 1
        buttonActivate = new JButton("Activate MemberShip");
        buttonActivate.setBounds(20, 40, 200, 30);

        buttonDeactivate = new JButton("Deactivate MemberShip");
        buttonDeactivate.setBounds(355, 40, 200, 30);

        // Buttons Row 2
        buttonSaveFile = new JButton("Save To File");
        buttonSaveFile.setBounds(20, 90, 200, 30);

        buttonReadFile = new JButton("Read From File");
        buttonReadFile.setBounds(355, 90, 200, 30);

        // Buttons Row 3
        buttonDisplay = new JButton("Display");
        buttonDisplay.setBounds(355, 140, 200, 30);

        buttonClear = new JButton("Clear");
        buttonClear.setBounds(20, 140, 200, 30);

        // 4. Adding Components to Panels/Frame
        // Adding Common Registration Fields to panelRegistration
        panelRegistration.add(labelId);
        panelRegistration.add(textFieldId);
        panelRegistration.add(labelName);
        panelRegistration.add(textFieldName);
        panelRegistration.add(labelLocation);
        panelRegistration.add(textFieldLocation);
        panelRegistration.add(labelPhone);
        panelRegistration.add(textFieldPhone);
        panelRegistration.add(labelEmail);
        panelRegistration.add(textFieldEmail);
        panelRegistration.add(labelGender);
        panelRegistration.add(radioButtonMale);
        panelRegistration.add(radioButtonFemale);
        panelRegistration.add(labelDob);
        panelRegistration.add(comboBoxDobDay);
        panelRegistration.add(comboBoxDobMonth);
        panelRegistration.add(comboBoxDobYear);
        panelRegistration.add(labelStartDate);
        panelRegistration.add(comboBoxStartDateDay);
        panelRegistration.add(comboBoxStartDateMonth);
        panelRegistration.add(comboBoxStartDateYear);

        // Adding Specific Fields to Regular Panel
        panelRegularTab.add(labelReferralSource);
        panelRegularTab.add(textFieldReferralSource);
        panelRegularTab.add(labelChoosePlan);
        panelRegularTab.add(comboBoxPlan);
        panelRegularTab.add(labelRegPlanPrice);
        panelRegularTab.add(textFieldRegPlanPrice);
        panelRegularTab.add(labelRemovalReason);
        panelRegularTab.add(textFieldRemovalReason);

        // Adding Specific Feilds to Premium Panel
        panelPremiumTab.add(labelPaidAmount);
        panelPremiumTab.add(textFieldPaidAmount);
        panelPremiumTab.add(labelTrainerName);
        panelPremiumTab.add(textFieldTrainerName);
        panelPremiumTab.add(labelPremPlanCharge);
        panelPremiumTab.add(textFieldPremPlanCharge);
        panelPremiumTab.add(labelDiscountAmount);
        panelPremiumTab.add(textFieldDiscountAmount);

        // Adding Tabs to Tabbed Pane
        tabbedPaneRegistration.addTab("Regular Member", panelRegularTab);
        tabbedPaneRegistration.addTab("Premium Member", panelPremiumTab);

        // Adding Tabbed Pane to panelRegistration
        panelRegistration.add(tabbedPaneRegistration);

        // Adding components to Nested panelMemberActions
        panelMemberActions.add(labelRegActions);
        panelMemberActions.add(labelPremActions);
        panelMemberActions.add(buttonAddRegular);
        panelMemberActions.add(buttonAddPremium);
        panelMemberActions.add(buttonRevertRegular);
        panelMemberActions.add(buttonRevertPremium);
        panelMemberActions.add(buttonMarkAttendanceRegular);
        panelMemberActions.add(buttonMarkAttendancePremium);
        panelMemberActions.add(buttonUpgradePlan);
        panelMemberActions.add(buttonPayDueAmount);
        panelMemberActions.add(buttonCalculateDiscount);

        // Adding components to Nested panelGeneralActions
        panelGeneralActions.add(buttonActivate);
        panelGeneralActions.add(buttonDeactivate);
        panelGeneralActions.add(buttonDisplay);
        panelGeneralActions.add(buttonClear);
        panelGeneralActions.add(buttonSaveFile);
        panelGeneralActions.add(buttonReadFile);

        // Adding Nested Panels to Main panelActions
        panelActions.add(panelMemberActions);
        panelActions.add(panelGeneralActions);

        // Adding Title Label and Main Panels to Frame
        frame.add(labelTitle);
        frame.add(panelRegistration);
        frame.add(panelActions);

        // Add Action Listeners
        buttonAddRegular.addActionListener(this);
        buttonAddPremium.addActionListener(this);
        buttonActivate.addActionListener(this);
        buttonDeactivate.addActionListener(this);
        buttonMarkAttendanceRegular.addActionListener(this);
        buttonMarkAttendancePremium.addActionListener(this);
        buttonRevertRegular.addActionListener(this);
        buttonRevertPremium.addActionListener(this);
        buttonDisplay.addActionListener(this);
        buttonClear.addActionListener(this);
        buttonUpgradePlan.addActionListener(this);
        buttonPayDueAmount.addActionListener(this);
        buttonCalculateDiscount.addActionListener(this);
        comboBoxPlan.addActionListener(this);
        // For file handling
        buttonSaveFile.addActionListener(this); 
        buttonReadFile.addActionListener(this);

        // 5. Configuring Panels
        panelRegistration.setLayout(null);
        panelRegistration.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Member Registration", TitledBorder.CENTER, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 16)));
        panelRegistration.setBounds(20, 60, 760, 570);

        panelActions.setLayout(null);
        panelActions.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Actions", TitledBorder.CENTER, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 16)));
        panelActions.setBounds(800, 60, 615, 570);

        panelMemberActions.setLayout(null);
        panelMemberActions.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Member Specific Actions", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 12)));
        panelMemberActions.setBounds(20, 25, 575, 315);

        panelGeneralActions.setLayout(null);
        panelGeneralActions.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "General Actions", TitledBorder.LEFT, TitledBorder.TOP, new Font("Segoe UI", Font.BOLD, 12)));
        panelGeneralActions.setBounds(20, 355, 575, 195);
        // Configuring and Positioning Tabbed Pane within panelRegistration
        panelPremiumTab.setLayout(null);
        panelRegularTab.setLayout(null);
        tabbedPaneRegistration.setBounds(20, 275, 720, 273);

        // 6. Final Frame Configuration
        frame.setLayout(null);
        frame.setSize(1450, 690); // Kept user's frame size
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true); // Make visible at the end
    }

    //Making a method for clearing the input feilds so that it can be resued in other places not just in the clear button
    private void clearInputFields() {
        // Clear TextFields
        textFieldId.setText("");
        textFieldName.setText("");
        textFieldLocation.setText("");
        textFieldPhone.setText("");
        textFieldEmail.setText("");
        textFieldReferralSource.setText(""); // In Regular tab
        textFieldPaidAmount.setText(""); // In Premium tab
        textFieldRemovalReason.setText(""); // In Regular tab
        textFieldTrainerName.setText(""); // In Premium tab

        // Reset ComboBoxes to the first item
        comboBoxDobDay.setSelectedIndex(0);
        comboBoxDobMonth.setSelectedIndex(0);
        comboBoxDobYear.setSelectedIndex(0);
        comboBoxStartDateDay.setSelectedIndex(0);
        comboBoxStartDateMonth.setSelectedIndex(0);
        comboBoxStartDateYear.setSelectedIndex(0);
        comboBoxPlan.setSelectedIndex(0); // In Regular tab

        // Reset RadioButtons
        buttonGroupGender.clearSelection();
    }

    //  Event Handling Method 
    @Override
    public void actionPerformed(ActionEvent ae) {
        //  Add Regular Member Button 
        if (ae.getSource() == buttonAddRegular) {
            //  Input Validation 
            if (textFieldId.getText().isEmpty() ||
            textFieldName.getText().isEmpty() ||
            textFieldLocation.getText().isEmpty() ||
            textFieldPhone.getText().isEmpty() ||
            textFieldEmail.getText().isEmpty() ||
            textFieldReferralSource.getText().isEmpty()
            )
            {

                JOptionPane.showMessageDialog(frame, "Please fill in all required fields for Regular Member:\nID, Name, Location, Phone, Email, Referral Source.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!radioButtonMale.isSelected() && !radioButtonFemale.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Please select a gender.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                //  Get Inputs INSIDE this block 
                int id = Integer.parseInt(textFieldId.getText()); // Attempt parsing
                String name = textFieldName.getText();
                String location = textFieldLocation.getText();
                String phone = textFieldPhone.getText();
                String email = textFieldEmail.getText();
                String gender = radioButtonMale.isSelected() ? "Male" : "Female";
                String dob = comboBoxDobDay.getSelectedItem().toString() + "-" + comboBoxDobMonth.getSelectedItem().toString() + "-" + comboBoxDobYear.getSelectedItem().toString();
                String startDate = comboBoxStartDateDay.getSelectedItem().toString() + "-" + comboBoxStartDateMonth.getSelectedItem().toString() + "-" + comboBoxStartDateYear.getSelectedItem().toString();
                String referralSource = textFieldReferralSource.getText();

                //  Duplicate ID Check 
                boolean duplicate = false;
                for (GymMember member : gymMembers) {
                    if (member.getId() == id) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    JOptionPane.showMessageDialog(frame, "Member ID " + id + " already exists.", "Duplicate ID Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Create and Add RegularMember Object
                    RegularMember newMember = new RegularMember(id, name, location, phone, email, gender, dob, startDate, referralSource);
                    gymMembers.add(newMember);
                    JOptionPane.showMessageDialog(frame, "Regular Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } 
            catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format. Please enter a whole number for the ID.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //  Add Premium Member Button 
        else if (ae.getSource() == buttonAddPremium) {
            //  Input Validation 
            if (textFieldId.getText().isEmpty() ||
            textFieldName.getText().isEmpty() ||
            textFieldLocation.getText().isEmpty() ||
            textFieldPhone.getText().isEmpty() ||
            textFieldEmail.getText().isEmpty() ||
            textFieldTrainerName.getText().isEmpty() ||
            textFieldPaidAmount.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(frame, "Please fill in all required fields for Premium Member:\nID, Name, Location, Phone, Email, Trainer's Name, Paid Amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                //  Get Inputs INSIDE this block 
                int id = Integer.parseInt(textFieldId.getText()); // Attempt parsing ID
                double paidAmount = Double.parseDouble(textFieldPaidAmount.getText()); // Attempt parsing Paid Amount

                String name = textFieldName.getText();
                String location = textFieldLocation.getText();
                String phone = textFieldPhone.getText();
                String email = textFieldEmail.getText();
                String gender = radioButtonMale.isSelected() ? "Male" : "Female";
                String dob = comboBoxDobDay.getSelectedItem().toString() + "-" +
                    comboBoxDobMonth.getSelectedItem().toString() + "-" +
                    comboBoxDobYear.getSelectedItem().toString();
                String startDate = comboBoxStartDateDay.getSelectedItem().toString() + "-" +
                    comboBoxStartDateMonth.getSelectedItem().toString() + "-" +
                    comboBoxStartDateYear.getSelectedItem().toString();
                String trainerName = textFieldTrainerName.getText();

                //  Duplicate ID Check 
                boolean duplicate = false;
                for (GymMember member : gymMembers) {
                    if (member.getId() == id) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    JOptionPane.showMessageDialog(frame, "Member ID " + id + " already exists.", "Duplicate ID Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Create and Add PremiumMember Object
                    PremiumMember newMember = new PremiumMember(id, name, location, phone, email, gender, dob, startDate, trainerName);
                    gymMembers.add(newMember);
                    JOptionPane.showMessageDialog(frame, "Premium Member added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid number format. Please enter a whole number for ID and a valid number for Paid Amount.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //  Activate Membership Button 
        else if (ae.getSource() == buttonActivate) {
            if (gymMembers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No members have been added yet.", "Empty List", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (textFieldId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter the Member ID to activate.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(textFieldId.getText());
                GymMember memberToActivate = null;

                for (GymMember member : gymMembers) {
                    if (member.getId() == id) {
                        memberToActivate = member;
                        break;
                    }
                }

                if (memberToActivate != null) {
                    memberToActivate.activateMembership();
                    JOptionPane.showMessageDialog(frame, "Membership for ID " + id + " activated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Member ID " + id + " not found.", "Search Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format. Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //  Deactivate Membership Button 
        else if (ae.getSource() == buttonDeactivate) {
            if (gymMembers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No members have been added yet.", "Empty List", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (textFieldId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter the Member ID to deactivate.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(textFieldId.getText());
                GymMember memberToDeactivate = null;

                for (GymMember member : gymMembers) {
                    if (member.getId() == id) {
                        memberToDeactivate = member;
                        break;
                    }
                }

                if (memberToDeactivate != null) {
                    memberToDeactivate.deactivateMembership();
                    JOptionPane.showMessageDialog(frame, "Membership for ID " + id + " deactivated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "Member ID " + id + " not found.", "Search Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format. Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //  Mark attendance for Regular Member 
        else if (ae.getSource() == buttonMarkAttendanceRegular) {
            if (gymMembers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No members have been added yet.", "Empty List", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (textFieldId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter the Member ID to mark attendance.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(textFieldId.getText());
                GymMember memberToMark = null;
                boolean memberfound = false;

                for (GymMember member : gymMembers) {
                    if (member.getId() == id) {
                        memberToMark = member;
                        memberfound = true;
                        break;
                    }
                }

                if (memberfound) {
                    if (memberToMark instanceof RegularMember) {
                        if (memberToMark.getActiveStatus()) {
                            memberToMark.markAttendance();
                            JOptionPane.showMessageDialog(frame, "Attendance marked for Regular Member ID " + id + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Cannot mark attendance. Membership for ID " + id + " is not active.", "Inactive Member", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Member ID " + id + " is not a Regular Member.", "Incorrect Member Type", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Member ID " + id + " not found.", "Search Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format. Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Mark attendance Premium Member 
        else if (ae.getSource() == buttonMarkAttendancePremium) {
            if (gymMembers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No members have been added yet.", "Empty List", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (textFieldId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter the Member ID to mark attendance.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(textFieldId.getText());
                GymMember memberToMark = null;
                boolean memberfound = false;

                for (GymMember member : gymMembers) {
                    if (member.getId() == id) {
                        memberToMark = member;
                        memberfound = true;
                        break;
                    }
                }

                if (memberfound) {
                    if (memberToMark instanceof PremiumMember) {
                        if (memberToMark.getActiveStatus()) {
                            memberToMark.markAttendance();
                            JOptionPane.showMessageDialog(frame, "Attendance marked for Premium Member ID " + id + ".", "Success", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(frame, "Cannot mark attendance. Membership for ID " + id + " is not active.", "Inactive Member", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Member ID " + id + " is not a Premium Member.", "Incorrect Member Type", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Member ID " + id + " not found.", "Search Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format. Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //  Revert Regular Member Button 
        else if (ae.getSource() == buttonRevertRegular) {
            if (gymMembers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No members have been added yet.", "Empty List", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (textFieldId.getText().isEmpty() || textFieldRemovalReason.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter the Member ID and Removal Reason to revert.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(textFieldId.getText());
                String removalReason = textFieldRemovalReason.getText();
                GymMember memberToRevert = null;
                boolean found = false;

                for (GymMember member : gymMembers) {
                    if (member.getId() == id) {
                        memberToRevert = member;
                        found = true;
                        break;
                    }
                }

                if (found) {
                    if (memberToRevert instanceof RegularMember) {
                        ((RegularMember) memberToRevert).revertRegularMember(removalReason);
                        JOptionPane.showMessageDialog(frame, "Regular Member ID " + id + " reverted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Member ID " + id + " is not a Regular Member.", "Incorrect Member Type", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Member ID " + id + " not found.", "Search Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format. Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        //  Revert Premium Member Button 
        else if (ae.getSource() == buttonRevertPremium) {
            if (gymMembers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No members have been added yet.", "Empty List", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (textFieldId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter the Member ID to revert.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(textFieldId.getText());
                GymMember memberToRevert = null;
                boolean found = false;

                for (GymMember member : gymMembers) {
                    if (member.getId() == id) {
                        memberToRevert = member;
                        found = true;
                        break;
                    }
                }

                if (found) {
                    if (memberToRevert instanceof PremiumMember) {
                        ((PremiumMember) memberToRevert).revertPremiumMember();
                        JOptionPane.showMessageDialog(frame, "Premium Member ID " + id + " reverted.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Member ID " + id + " is not a Premium Member.", "Incorrect Member Type", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Member ID " + id + " not found.", "Search Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format. Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Display Button
        else if (ae.getSource() == buttonDisplay) {
            if (gymMembers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No members to display.", "Empty List", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Create the new display window
                JFrame allMembersDisplayFrame = new JFrame("All Member Details");
                allMembersDisplayFrame.setSize(1200, 600); // set width, height of frames
                allMembersDisplayFrame.setLocationRelativeTo(frame);

                JTextArea allMembersTextArea = new JTextArea();
                allMembersTextArea.setEditable(false);
                allMembersTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16)); // Monospaced for alignment

                String fullDetailsText = ""; // Initialize empty string

                // Header for the JTextArea, using String.format from the file hint
                fullDetailsText += String.format("%-5s %-18s %-15s %-12s %-25s %-10s %-12s %-12s %-10s %-10s %-8s %-10s %-8s %-15s %-10s %-10s %-10s\n",
                    "ID", "Name", "Location", "Phone", "Email", "Gender", "DOB", "Start Dt", "Plan", "Price",
                    "Attend", "Loyalty", "Active", "Trainer", "Referral", "Paid Amt", "Discount");
                fullDetailsText += "===============================================================================================================================================================================================================================\n";

                for (GymMember member : gymMembers) {
                    // 1. Call the member's display() method (still prints to console, as per your existing setup)
                    System.out.println("\n--- Displaying Member ID: " + member.getId() + " (Console) ---");
                    member.display();
                    System.out.println("--------------------------------------------------");

                    // 2. Build the string for the JTextArea in the new frame using getters and String.format
                    String plan = "N/A";
                    double price = 0.0;
                    String trainer = "N/A";
                    String referral = "N/A";
                    double paidAmtValue = 0.0;
                    double discountAmtValue = 0.0;
                    String isFullPaymentStr = "N/A"; // For display, "true"/"false"

                    if (member instanceof RegularMember) {
                        RegularMember rm = (RegularMember) member;
                        plan = rm.getPlan() != null ? rm.getPlan() : "N/A";
                        price = rm.getPrice();
                        referral = rm.getReferralSource() != null ? rm.getReferralSource() : "N/A";
                        isFullPaymentStr = "N/A";
                    } else if (member instanceof PremiumMember) {
                        PremiumMember pm = (PremiumMember) member;
                        plan = "Premium";
                        price = pm.getPremiumCharge(); // Total charge for premium plan
                        trainer = pm.getPersonalTrainer() != null ? pm.getPersonalTrainer() : "N/A";
                        paidAmtValue = pm.getPaidAmount();
                        isFullPaymentStr = String.valueOf(pm.getIsFullPayment());
                        if (pm.getIsFullPayment()) {
                            // getting discount amount if full paid
                            discountAmtValue = pm.getDiscountAmount();
                        }
                    }
                    // Adjust the format specifiers and order to match your desired columns
                    fullDetailsText += String.format("%-5d %-18s %-15s %-12s %-25s %-10s %-12s %-12s %-10s %-10.2f %-8d %-10.1f %-8b %-15s %-10s %-10.2f %-10.2f\n",
                        member.getId(),
                        member.getName(),
                        member.getLocation(),
                        member.getPhone(),
                        member.getEmail(),
                        member.getGender(),
                        member.getDOB(),
                        member.getMembershipStartDate(),
                        plan,
                        price,
                        member.getAttendance(),
                        member.getLoyaltyPoints(),
                        member.getActiveStatus(),
                        trainer,
                        referral,
                        paidAmtValue,
                        discountAmtValue);
                }

                allMembersTextArea.setText(fullDetailsText);

                JScrollPane allMembersScrollPane = new JScrollPane(allMembersTextArea);
                allMembersDisplayFrame.add(allMembersScrollPane);
                allMembersDisplayFrame.setVisible(true);

            }
        }

        //  Clear Button 
        else if (ae.getSource() == buttonClear) {
            // No parsing needed here
            clearInputFields();
            JOptionPane.showMessageDialog(frame, "Input fields cleared.", "Clear", JOptionPane.INFORMATION_MESSAGE);
        }
        // Upgrade Plan Button (for Regular Members)
        else if (ae.getSource() == buttonUpgradePlan) {
            if (textFieldId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter the Member ID to upgrade the plan.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (gymMembers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No members in the system.", "Empty List", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // checking if memebr id valid or not and then availabe in arraylist or not
                int id = Integer.parseInt(textFieldId.getText());
                GymMember memberToUpgrade = null;
                boolean found = false;

                for (GymMember member : gymMembers) {
                    if (member.getId() == id) {
                        memberToUpgrade = member;
                        found = true;
                        break;
                    }
                }

                if (found) {
                    if (memberToUpgrade instanceof RegularMember) {
                        
                        RegularMember regMember = (RegularMember) memberToUpgrade;
                        if (regMember.getActiveStatus()) {
                            String selectedPlan = (String) comboBoxPlan.getSelectedItem(); // Get plan from combo box in Regular Tab
                            String result = regMember.upgradePlan(selectedPlan);
                            JOptionPane.showMessageDialog(frame, result, "Upgrade Plan", JOptionPane.INFORMATION_MESSAGE);
                            // Update the plan price text field if the upgrade was successful and not an error message
                            if (!result.toLowerCase().contains("error") && !result.toLowerCase().contains("invalid") && !result.toLowerCase().contains("same plan")) {
                                textFieldRegPlanPrice.setText(String.valueOf(regMember.getPrice())); // Update with new price
                            }
                        } else {
                            JOptionPane.showMessageDialog(frame, "Member ID " + id + " is not active. Cannot upgrade plan.", "Inactive Member", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(frame, "Member ID " + id + " is not a Regular Member. Cannot upgrade plan.", "Incorrect Member Type", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Member ID " + id + " not found.", "Search Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format. Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Calculate Discount Button (for Premium Members)
        else if (ae.getSource() == buttonCalculateDiscount) {
            if (textFieldId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter the Member ID to calculate discount.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (gymMembers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No members in the system.", "Empty List", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(textFieldId.getText());
                GymMember memberToCalc = null;
                boolean found = false;

                for (GymMember member : gymMembers) {
                    if (member.getId() == id) {
                        memberToCalc = member;
                        found = true;
                        break;
                    }
                }

                if (found) {
                    if (memberToCalc instanceof PremiumMember) {
                        PremiumMember premMember = (PremiumMember) memberToCalc;
                        premMember.calculateDiscount();
                        textFieldDiscountAmount.setText(String.valueOf(premMember.getDiscountAmount())); // Update the non-editable field
                        // JOptionPane.showMessageDialog(frame, resultMessage, "Calculate Discount", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Member ID " + id + " is not a Premium Member.", "Incorrect Member Type", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Member ID " + id + " not found.", "Search Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format. Please enter a whole number.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Pay Due Amount Button (for Premium Members)
        else if (ae.getSource() == buttonPayDueAmount) {
            if (textFieldId.getText().isEmpty() || textFieldPaidAmount.getText().isEmpty()) { // Assuming textFieldPaidAmount in Premium Tab is used for new payment
                JOptionPane.showMessageDialog(frame, "Please enter Member ID and the Amount to Pay (in Premium Member tab).", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (gymMembers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No members in the system.", "Empty List", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int id = Integer.parseInt(textFieldId.getText());
                double amountToPay = Double.parseDouble(textFieldPaidAmount.getText()); // Get from Premium tab's paid amount field

                if(amountToPay < 0){
                    JOptionPane.showMessageDialog(frame, "Payment amount must be positive.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if(amountToPay == 0){
                    JOptionPane.showMessageDialog(frame, "Payment amount must not be Zero.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                GymMember memberToPay = null;
                boolean found = false;

                for (GymMember member : gymMembers) {
                    if (member.getId() == id) {
                        memberToPay = member;
                        found = true;
                        break;
                    }
                }

                if (found) {
                    if (memberToPay instanceof PremiumMember) {
                        PremiumMember premMember = (PremiumMember) memberToPay;
                        String resultMessage = premMember.payDueAmount(amountToPay);
                        JOptionPane.showMessageDialog(frame, resultMessage, "Pay Due Amount", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Member ID " + id + " is not a Premium Member.", "Incorrect Member Type", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Member ID " + id + " not found.", "Search Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(frame, "Invalid ID or Amount format. Please enter valid numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Inside the actionPerformed(ActionEvent ae) method, after other else if blocks:

        // ComboBox Plan Selection Change (for Regular Member Price)
        else if (ae.getSource() == comboBoxPlan) {
            String comboSelectedPlan = (String) comboBoxPlan.getSelectedItem();
            double price = 0.0;
            if(comboSelectedPlan == "Basic"){
                textFieldRegPlanPrice.setText("6500.0");
            }
            else if(comboSelectedPlan == "Standard"){
                textFieldRegPlanPrice.setText("12500.0");
            }
            else if(comboSelectedPlan == "Deluxe") {
                textFieldRegPlanPrice.setText("18500.0");
            }

        }

        //Save to File Button
        else if (ae.getSource() == buttonSaveFile) {
            if (gymMembers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No member data to save.", "Empty List", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("MemberDetails.txt"))) {
                // Write header - adjust formatting 
                writer.write(String.format("%-5s %-20s %-15s %-15s %-25s %-15s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s %-15s %-15s %-15s\n",
                        "ID", "Name", "Location", "Phone", "Email", "Gender", "DOB", "Start Date", "Plan", "Price",
                        "Attendance", "Loyalty", "Active", "Trainer", "Referral", "Paid Amt", "Discount"));

                for (GymMember member : gymMembers) {
                    // Common details
                    String plan = "N/A";
                    double price = 0.0;
                    String trainer = "N/A";
                    String referral = "N/A";
                    double paidAmount = 0.0;
                    double discount = 0.0;

                    if (member instanceof RegularMember) {
                        RegularMember rm = (RegularMember) member;
                        plan = rm.getPlan();
                        price = rm.getPrice();
                        referral = rm.getReferralSource();
                    } else if (member instanceof PremiumMember) {
                        PremiumMember pm = (PremiumMember) member;
                        plan = "Premium"; // Or some indicator
                        price = pm.getPremiumCharge(); // Assuming this getter exists
                        trainer = pm.getPersonalTrainer();
                        paidAmount = pm.getPaidAmount();
                        discount = pm.getDiscountAmount();
                    }

                    writer.write(String.format("%-5d %-20s %-15s %-15s %-25s %-15s %-10s %-10s %-10s %-10.2f %-10d %-10.1f %-10b %-10s %-15s %-15.2f %-15.2f\n",
                            member.getId(),
                            member.getName(),
                            member.getLocation(),
                            member.getPhone(),
                            member.getEmail(),
                            member.getGender(),
                            member.getDOB(),
                            member.getMembershipStartDate(),
                            plan,
                            price,
                            member.getAttendance(),
                            member.getLoyaltyPoints(),
                            member.getActiveStatus(),
                            trainer,
                            referral,
                            paidAmount,
                            discount));
                }
                JOptionPane.showMessageDialog(frame, "Member details saved to MemberDetails.txt", "File Saved", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error saving file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        //Read from File Button
        else if (ae.getSource() == buttonReadFile) {
            String fileContent = "";
            try (BufferedReader reader = new BufferedReader(new FileReader("MemberDetails.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent += line + "\n";
                }

                if (fileContent.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "MemberDetails.txt is empty or not found.", "File Read Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                // Display in a new frame
                JFrame fileDisplayFrame = new JFrame("Member Details from File");
                fileDisplayFrame.setSize(1200, 600);
                fileDisplayFrame.setLocationRelativeTo(frame);

                JTextArea fileTextArea = new JTextArea(fileContent);
                fileTextArea.setEditable(false);
                fileTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));

                JScrollPane fileScrollPane = new JScrollPane(fileTextArea);
                fileDisplayFrame.add(fileScrollPane);
                fileDisplayFrame.setVisible(true);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(frame, "Error reading file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } // End of actionPerformed method

    public static void main(String[] args) {
        try {
            // the UIManager.getSystemLookAndFeelClassName() returns the string according to the system OS 
            // this program is running in windows so here it gives:
            // "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } 
        catch (Exception e) {
            System.err.println("System Look and Feel not set: " + e);
        }

        new GymGui(); // Create and show the GUI
    }
}