
package Pages;

import CustomerAndBooking_DataStore.Customer_Data;
import Database.JDBC_customer;
import Regular_Expression.RegularExpression;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author raaz4
 */
public class Registration1 implements ActionListener {

    JFrame frame;
    JPanel panel1, panel2;
    JLabel lbl_pic;
    JLabel lbl_heading, lbl_underscore1, lbl_underscore2;
    JLabel lbl_email, lbl_password, lbl2_password;
    JLabel gap, gap1, gap2;
//panel2 registration lbls
    JLabel lbl_customerType, lbl_name, lbl_address, lbl_phone, lbl_credit, lbl_gender, lbl_agegroup;
    JTextField txt_name, txt_address, txt_phone, txt_credit, txt_age;
    JRadioButton rd_genderMale, rd_genderFemale, rd_genderOther;
    JPasswordField password, password2;
    JComboBox customer_type;
    JButton btn_back, btn_register, btn_login;
    JTextField txt_email;
    ButtonGroup genderbtngrp;
    JSplitPane splitPane;
    ImageIcon img;

    public void RegistrationForm() {
        frame = new JFrame();
        frame.setTitle("Luton Hotel - Registration");
        frame.setSize(1500, 891);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.ORANGE);

        Font fTitle = new Font("Segoe UI Black", Font.TRUETYPE_FONT, 50);
        Font fEmail = new Font("Courier", Font.PLAIN, 20);

//picture of the hotel in panel1
        panel1 = new JPanel();
        img = new ImageIcon("src\\Image\\login1.jpg");
        lbl_pic = new JLabel(img);
        lbl_pic.setPreferredSize(new Dimension(1000, 891));
        panel1.add(lbl_pic);
        panel1.setBackground(Color.getHSBColor(100, 5, 80));

        panel2 = new JPanel();
        lbl_underscore1 = new JLabel("_________________________________");
        lbl_heading = new JLabel(" Registration Form ");
        lbl_underscore2 = new JLabel("______________________________________________________");
        lbl_heading.setFont(fTitle);
        img = new ImageIcon("src\\Image\\LoginR logo.jpg");
        lbl_pic = new JLabel(img);
        lbl_pic.setPreferredSize(new Dimension(250, 250));

        //customer Type 
        lbl_customerType = new JLabel("Customer Type : ");
        lbl_customerType.setFont(fEmail);
        Vector customerType = new Vector();
        customerType.add("     Non-Corporate          ");
        customerType.add("         Corporate          ");
        customer_type = new JComboBox(customerType);
        customer_type.setFont(fEmail);

        lbl_name = new JLabel("Enter Name : ");
        lbl_name.setFont(fEmail);
        txt_name = new JTextField();
        txt_name.setFont(fEmail);
        txt_name.setPreferredSize(new Dimension(300, 35));

        lbl_address = new JLabel("Address      : ");
        lbl_address.setFont(fEmail);
        txt_address = new JTextField();
        txt_address.setFont(fEmail);
        txt_address.setPreferredSize(new Dimension(300, 35));
//Enter email and password         
        lbl_email = new JLabel("Enter Email : ");
        lbl_email.setFont(fEmail);
        txt_email = new JTextField();
        txt_email.setFont(fEmail);
        txt_email.setPreferredSize(new Dimension(300, 35));

        gap = new JLabel("                                                      "
                + "                                                             ");
        gap1 = new JLabel("                                                                      "
                + "                                                             ");
        gap2 = new JLabel("                                                         "
                + "                                                                 ");
        lbl_password = new JLabel("Password   : ");
        lbl_password.setFont(fEmail);
        lbl2_password = new JLabel("Re-Password:");
        lbl2_password.setFont(fEmail);
        password = new JPasswordField();
        password.setFont(fEmail);
        password2 = new JPasswordField();
        password2.setFont(fEmail);
        password.setPreferredSize(new Dimension(300, 35));
        password2.setPreferredSize(new Dimension(300, 35));

        lbl_phone = new JLabel("Contact No. : ");
        lbl_phone.setFont(fEmail);
        txt_phone = new JTextField();
        txt_phone.setFont(fEmail);
        txt_phone.setPreferredSize(new Dimension(300, 35));

        lbl_credit = new JLabel("Credit Info  :  ");
        lbl_credit.setFont(fEmail);
        txt_credit = new JTextField();
        txt_credit.setFont(fEmail);
        txt_credit.setPreferredSize(new Dimension(300, 35));

        lbl_gender = new JLabel("Gender : ");
        lbl_gender.setFont(fEmail);
        rd_genderMale = new JRadioButton("Male");
        rd_genderMale.setFont(fEmail);
        rd_genderMale.setBackground(Color.getHSBColor(100, 5, 80));
        
        rd_genderFemale = new JRadioButton("Female");
        rd_genderFemale.setFont(fEmail);
        rd_genderFemale.setBackground(Color.getHSBColor(100, 5, 80));
        rd_genderOther = new JRadioButton("Others");
        rd_genderOther.setFont(fEmail);
        rd_genderOther.setBackground(Color.getHSBColor(100, 5, 80));
        genderbtngrp = new ButtonGroup();
        genderbtngrp.add(rd_genderMale);
        genderbtngrp.add(rd_genderFemale);
        genderbtngrp.add(rd_genderOther);

        lbl_agegroup = new JLabel();
        lbl_agegroup.setText("Age in Num :  ");
        lbl_agegroup.setFont(fEmail);
        txt_age = new JTextField();
        txt_age.setFont(fEmail);
        txt_age.setPreferredSize(new Dimension(300, 35));

//buttons
        btn_register = new JButton("Register");
        btn_register.setFont(fEmail);
        btn_register.addActionListener(this);
        btn_back = new JButton("Back");
        btn_back.addActionListener((ActionEvent e) -> {
            Home home = new Home();
            home.HomePage();
            frame.dispose();
        });
        btn_back.setFont(fEmail);

        panel2.setBackground(Color.getHSBColor(100, 5, 80));
        panel2.add(lbl_pic);
        panel2.add(lbl_underscore1);
        panel2.add(lbl_heading);
        panel2.add(lbl_underscore2);
        //panel2.add(gap);
        panel2.add(lbl_customerType);
        panel2.add(customer_type);
        panel2.add(lbl_name);
        panel2.add(txt_name);
        panel2.add(lbl_address);
        panel2.add(txt_address);
        panel2.add(lbl_email);
        panel2.add(txt_email);
        panel2.add(lbl_password);
        panel2.add(password);
        panel2.add(lbl2_password);
        panel2.add(password2);
        panel2.add(lbl_phone);
        panel2.add(txt_phone);
        panel2.add(lbl_credit);
        panel2.add(txt_credit);
        panel2.add(lbl_agegroup);
        panel2.add(txt_age);
        panel2.add(lbl_gender);
        panel2.add(rd_genderMale);
        panel2.add(rd_genderFemale);
        panel2.add(rd_genderOther);
        panel2.add(gap1);

        panel2.add(btn_register);
        panel2.add(btn_back);

        splitPane = new JSplitPane(SwingConstants.VERTICAL, panel1, panel2);
        //frame.setLayout(new FlowLayout());
        frame.setContentPane(splitPane);

        frame.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        RegularExpression re = new RegularExpression();
        if (e.getSource() == btn_register) {
            String gender = "None";
            if (rd_genderMale.isSelected()) {
                gender = "Male";
            } else if (rd_genderFemale.isSelected()) {
                gender = "Female";
            } else if (rd_genderOther.isSelected()) {
                gender = "Other";
            }

            if (!re.isAlphabets(txt_name.getText())) {
                String message = "Invalid Name ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isEmail(txt_email.getText())) {
                String message = " Email address incorrect ! \n Example : email@gmail.com";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            String psd1 = String.valueOf(password.getPassword());
            String psd2 = String.valueOf(password2.getPassword());
            if (!psd1.equals(psd2)) {
                System.out.println("Password Do not match");
                String message = " Password Do not match ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isInteger(txt_phone.getText()) || txt_phone.getText().length() != 10) {
                String message = "Invalid Phone ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isInteger(txt_credit.getText()) || txt_credit.getText().length() != 16) {
                String message = "Invalid Credit ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isInteger(txt_age.getText())) {
                String message = "Invalid Age ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!rd_genderMale.isSelected() && !rd_genderFemale.isSelected() && !rd_genderOther.isSelected()) {
                String message = "Select Gender ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (txt_name.getText().length() == 0 || txt_address.getText().length() == 0 || txt_email.getText().length() == 0 || psd1.length() == 0 || txt_phone.getText().length() == 0 || txt_credit.getText().length() == 0 || txt_age.getText().length() == 0) {
                String message = "Fill out all the fields ";
                JOptionPane.showMessageDialog(null, message, "Not Accepted", JOptionPane.ERROR_MESSAGE);
            }

            if (txt_name.getText().length() > 0 && txt_address.getText().length() > 0 && txt_email.getText().length() > 0 && psd1.length() > 0 && txt_phone.getText().length() > 0 && txt_phone.getText().length() == 10
                    && txt_credit.getText().length() > 0 && txt_credit.getText().length() == 16 && txt_age.getText().length() > 0 && re.isAlphabets(txt_name.getText()) == true && re.isEmail(txt_email.getText())
                    && re.isInteger(txt_phone.getText()) == true && re.isInteger(txt_credit.getText()) == true
                    && re.isInteger(txt_age.getText()) == true) {

                int discount = 0;
                int customerId = 0;
                Customer_Data person = new Customer_Data(customerId, customer_type.getSelectedItem().toString(), txt_name.getText(), txt_address.getText(), txt_email.getText(), String.valueOf(password.getPassword()), Long.parseLong(txt_phone.getText()), Long.parseLong(txt_credit.getText()), Integer.parseInt(txt_age.getText()), gender, discount);
                System.out.println(person);

                JDBC_customer obj = new JDBC_customer();
                boolean result = obj.Email_check(person);

                if (!result) {

                    obj.insert(person);
                    String message = " Registration Successfull ";
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    String message = " Email Address already Exist ";
                    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
