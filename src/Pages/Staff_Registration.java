
package Pages;

import HotelService_DataStore.Staff_Data;
import Database.StaffJDBC;
import Regular_Expression.RegularExpression;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Staff_Registration implements ActionListener {

    JFrame frame;
    JPanel panel1, panel2;
    JLabel lbl_pic;
    JLabel lbl_heading, lbl_underscore1, lbl_underscore2;
    JLabel lbl_email, lbl_password;
    JLabel gap, gap1, gap2;
//panel2 registration lbls
    JLabel lbl_name, lbl_address, lbl_phone, lbl_credit, lbl_gender, lbl_agegroup;
    JTextField txt_name, txt_address, txt_phone, txt_credit;
    JRadioButton rd_genderMale, rd_genderFemale, rd_genderOther;
    JPasswordField password;
    JComboBox ageGroup;
    JButton btn_back, btn_register, btn_login;
    JTextField txt_email;

    JSplitPane splitPane;
    ImageIcon img;

    public void RegistrationForm() {
        frame = new JFrame();
        frame.setTitle("Luton Hotel - Staff Registration");
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

//Name
        lbl_name = new JLabel("Enter Name : ");
        lbl_name.setFont(fEmail);
        txt_name = new JTextField();
        txt_name.setFont(fEmail);
        txt_name.setPreferredSize(new Dimension(300, 35));

//Address
        lbl_address = new JLabel("Address      : ");
        lbl_address.setFont(fEmail);
        txt_address = new JTextField();
        txt_address.setFont(fEmail);
        txt_address.setPreferredSize(new Dimension(300, 35));
//email
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
//password
        lbl_password = new JLabel("Password   : ");
        lbl_password.setFont(fEmail);
        password = new JPasswordField();
        password.setFont(fEmail);
        password.setPreferredSize(new Dimension(300, 35));

//phone      
        lbl_phone = new JLabel("Contact No. : ");
        lbl_phone.setFont(fEmail);
        txt_phone = new JTextField();
        txt_phone.setFont(fEmail);
        txt_phone.setPreferredSize(new Dimension(300, 35));

//gender
        lbl_gender = new JLabel("Gender : ");
        lbl_gender.setFont(fEmail);
        rd_genderMale = new JRadioButton("Male");
        rd_genderMale.setFont(fEmail);
        rd_genderFemale = new JRadioButton("Female");
        rd_genderFemale.setFont(fEmail);
        rd_genderOther = new JRadioButton("Others");
        rd_genderOther.setFont(fEmail);
        ButtonGroup bg = new ButtonGroup();
        bg.add(rd_genderMale);
        bg.add(rd_genderFemale);
        bg.add(rd_genderOther);

        
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
        panel2.add(gap);
        panel2.add(lbl_name);
        panel2.add(txt_name);
        panel2.add(lbl_address);
        panel2.add(txt_address);
        panel2.add(lbl_phone);
        panel2.add(txt_phone);
        panel2.add(lbl_email);
        panel2.add(txt_email);
        panel2.add(lbl_password);
        panel2.add(password);
        panel2.add(gap1);

        panel2.add(btn_register);
        panel2.add(btn_back);

        splitPane = new JSplitPane(SwingConstants.VERTICAL, panel1, panel2);
        frame.setContentPane(splitPane);

        frame.setVisible(true);
    }

//adding functions to each button in this frame
    @Override
    public void actionPerformed(ActionEvent e) {
        RegularExpression re = new RegularExpression();

        if (e.getSource() == btn_register) {
            if (!re.isAlphabets(txt_name.getText())) {
                String message = "Invalid Name ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isInteger(txt_phone.getText()) || txt_phone.getText().length() != 10) {
                String message = "Invalid Phone ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            if (!re.isEmail(txt_email.getText())) {
                String message = " Email address incorrect ! ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.ERROR_MESSAGE);
            }
            
            String psd = String.valueOf(password.getPassword());
            if(re.isEmail(txt_email.getText()) == true && txt_address.getText().length() > 0 && re.isAlphabets(txt_name.getText()) == true && txt_name.getText().length() > 0 && re.isInteger(txt_phone.getText()) == true && txt_phone.getText().length() == 10 && psd.length() > 0 ){
                
                Staff_Data staff = new Staff_Data(txt_name.getText(), txt_address.getText(), Long.parseLong(txt_phone.getText()), txt_email.getText(), psd );
                System.out.println(staff);
                StaffJDBC obj = new StaffJDBC();
                obj.insert(staff);
                JOptionPane.showMessageDialog(null, " Registration Successfull ");
                Staff_Login log = new Staff_Login();
                log.Staff_LoginForm();
                frame.dispose();
            }
            else {
                String message = " !!! Registration Unsucessful !!! ";
                JOptionPane.showMessageDialog(null, message, "Invalid", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
